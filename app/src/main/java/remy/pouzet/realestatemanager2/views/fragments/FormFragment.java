package remy.pouzet.realestatemanager2.views.fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.model.LatLng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import remy.pouzet.realestatemanager2.R;
import remy.pouzet.realestatemanager2.databinding.FragmentFormBinding;
import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.datas.models.EstateRaw;
import remy.pouzet.realestatemanager2.datas.services.realapi.pojos.ResultsItem;
import remy.pouzet.realestatemanager2.utils.Utils;
import remy.pouzet.realestatemanager2.viewmodels.FormViewModel;
import remy.pouzet.realestatemanager2.views.bases.BaseFragment;

public class FormFragment extends BaseFragment {
    
    //------------------------------------------------------//
    // ------------------   Variables   ------------------- //
    // ------------------------------------------------------//
    
    //-- Dates variables --//
    @SuppressLint("SimpleDateFormat") SimpleDateFormat formatterUIFormat = new SimpleDateFormat(
            "dd/MM/yyyy");
    Calendar c    = Calendar.getInstance();
    int      year = c.get(Calendar.YEAR), month = c.get(Calendar.MONTH), day = c.get(Calendar.DAY_OF_MONTH);
    String updateDateInRightFormat;
    String selledDateInRightFormat;
    public double estateLat, estateLng;
    public String adress;
    String[] estateLocationStringArray;
    public  EstateRaw           estateRaw;
    public  Long                id;
    private Estate              estate;
    private ConstraintLayout    constraintLayout;
    private FormViewModel       formViewModel;
    private ImageButton         editEstateButton;
    private Button              lastModificationDateButton;
    private Button              selledDateButton;
    private Date                dateBackEndFormat;
    private CheckBox            isItSellCheckBox;
    private TextView            sellTitleTextView;
    private FragmentFormBinding mFragmentFormBinding;
    public  int                 autoIncremented = 0;
    public  int                 nullEquivalent  = 0;
    private LatLng              estateLocation  = new LatLng(-34.92873, 138.59995);
    
    //------------------------------------------------------//
    // ------------------   LifeCycle   ------------------- //
    //------------------------------------------------------//
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentFormBinding = FragmentFormBinding.inflate(inflater, container, false);
        viewBindingManagement();
        configureViewModel();
        return mFragmentFormBinding.getRoot();
    }
    

    
    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHideKeyboardOnTouch(requireContext(), getView());
        updateUIIfItsModification();
        isItSellCheckBox.setOnClickListener(v -> sellStatusManagement());
        datePickerButtonsManagement(lastModificationDateButton, selledDateButton);
        datePickerButtonsManagement(selledDateButton, lastModificationDateButton);
        validationButtonManagement();
    }
    
    //------------------------------------------------------//
    // ------------------   Functions   ------------------- //
    //------------------------------------------------------//
    
    public static void setHideKeyboardOnTouch(final Context context, View view) {
        //Set up touch listener for non-text box views to hide keyboard.
        try {
            //Set up touch listener for non-text box views to hide keyboard.
            if (!(view instanceof EditText || view instanceof ScrollView)) {
                view.setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View v, MotionEvent event) {
                        InputMethodManager in = (InputMethodManager) context.getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                        in.hideSoftInputFromWindow(v.getWindowToken(),
                                                   InputMethodManager.HIDE_NOT_ALWAYS);
                        return false;
                    }
                });
            }
            //If a layout container, iterate over children and seed recursion.
            if (view instanceof ViewGroup) {
                for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                    View innerView = ((ViewGroup) view).getChildAt(i);
                    setHideKeyboardOnTouch(context, innerView);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void viewBindingManagement() {
        lastModificationDateButton = mFragmentFormBinding.updateDateValueFragmentFormDatePickerButton;
        selledDateButton           = mFragmentFormBinding.sellDateValueFragmentFormDatePickerButton;
        isItSellCheckBox           = mFragmentFormBinding.isSellCheckbox;
        sellTitleTextView          = mFragmentFormBinding.sellTitleFragmentForm;
        constraintLayout           = mFragmentFormBinding.parentFragmentForm;
    }
    
    private void configureViewModel() {
        formViewModel = new ViewModelProvider(this).get(FormViewModel.class);
    }
    
    @Override
    public View provideYourFragmentView(LayoutInflater inflater,
                                        ViewGroup parent,
                                        Bundle savedInstanceState) {
        return null;
    }
    
    public void updateUIIfItsModification() {
        if (itsAModification()) {
            getEstate(id);
        }
    }
    
    public boolean itsAModification() {
        if (getArguments() != null) {
            id = Long.parseLong(getArguments().get("id").toString());
            return formViewModel.isNewEstateUC(id);
        } else {
            return false;
        }
    }
    
    private void createNewEstateManagement() {
        switch (formViewModel.checkFormData(estateRaw)) {
            //could be better not imbricate conditions but will not be longer a issue with kotlin
            case IS_VALID:
                if (itsAModification()) {
                    formViewModel.updateEstate(estateRaw);
                    showLongSnackBar(mFragmentFormBinding.getRoot(),
                                     getString(R.string.Itemspdate));
                } else if (!itsAModification()) {
                    formViewModel.createEstate(estateRaw);
                    showLongSnackBar(mFragmentFormBinding.getRoot(), getString((R.string.Success)));
                } else {
                    showIndefiniteSnackBar(mFragmentFormBinding.getRoot(), "unknow error");
                }
                break;
    
            case ERROR_SELL_DATE:
                showIndefiniteSnackBar(mFragmentFormBinding.getRoot(),
                                       getString((R.string.sell_date_cannot_be_null)));
                break;
    
            case ERROR_MINIMAL_WORD_LENGTH:
                showIndefiniteSnackBar(mFragmentFormBinding.getRoot(),
                                       getString(R.string.minimal_words_lenght));
                break;
            case ERROR_PRICE_VALUE:
                showIndefiniteSnackBar(mFragmentFormBinding.getRoot(),
                                       getString(R.string.estate_price_cannot_be_null));
                break;
            case ERROR_SURFACE_VALUE:
                showIndefiniteSnackBar(mFragmentFormBinding.getRoot(),
                                       getString(R.string.surface_value_cannot_be_null));
                break;
            case ERROR_ROOMS_VALUE:
                showIndefiniteSnackBar(mFragmentFormBinding.getRoot(),
                                       getString(R.string.rooms_value_cannot_be_null));
                break;
            case ERROR_CONTACT_VALUE:
                showIndefiniteSnackBar(mFragmentFormBinding.getRoot(),
                                       getString(R.string.contact_value_cannot_be_null));
                break;
    
            case ERROR_UPDATE_DATE_VALUE:
                showIndefiniteSnackBar(mFragmentFormBinding.getRoot(),
                                       getString(R.string.update_date_cannot_be_null));
                break;
        }
    }
    
    public void datePickerButtonsManagement(Button button1, Button button2) {
        int tens = 9;
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatterBackEndFormat = new SimpleDateFormat("yyyyMMdd");
        button1.setOnClickListener(v -> {
            DatePickerDialog dd = new DatePickerDialog(requireContext(),
                                                       (view, year, monthOfYear, dayOfMonth) -> {
                                                           try {
                                                               String dateInStringUIFormat = Utils.uIformat(
                                                                       dayOfMonth,
                                                                       monthOfYear,
                                    year);

                            Date dateUIFormat = formatterUIFormat
                                    .parse(dateInStringUIFormat);
    
                                                               if (dayOfMonth > tens) {
                                                                   String dateInStringBEformat = Utils
                                                                           .bEformat(dayOfMonth,
                                                                                     monthOfYear,
                                                                                     year);
                                                                   dateBackEndFormat = formatterBackEndFormat
                                                                           .parse(dateInStringBEformat);
                                                               } else {
                                                                   String dateInStringBEformatException = Utils
                                                                           .bEformatException(
                                                                                   dayOfMonth,
                                                monthOfYear,
                                                year);
                                dateBackEndFormat = formatterBackEndFormat
                                        .parse(dateInStringBEformatException);
                            }

                            button1.setText(formatterUIFormat.format(
                                    dateUIFormat));

                            // check that dates cannot be paradoxal between them
                                                               if (button1.getText()
                                                                          .toString()
                                                                          .length() > nullEquivalent && button2.getText()
                                                                                                               .toString()
                                                                                                               .length() > nullEquivalent) {
                                                                   checkDateBetweenThem(button1,
                                                                                        button2);
                                                               }

                            // check dates cannot be place in the future
                            if (button1 == lastModificationDateButton) {
                                Date dateOfBeginning = formatterUIFormat
                                        .parse(dateInStringUIFormat);
                                checkDateWithToday(
                                        dateOfBeginning,
                                        button1);

                                updateDateInRightFormat = formatterBackEndFormat.format(
                                        dateBackEndFormat);
                            } else {
                                Date dateOfEnding = formatterUIFormat.parse(dateInStringUIFormat);
                                checkDateWithToday(dateOfEnding, button1);
                                selledDateInRightFormat = formatterBackEndFormat.format(
                                        dateBackEndFormat);
                            }
                                                           }
                                                           catch (Exception ex) {
                                                               ex.printStackTrace();
                                                           }
                                                       }, year, month, day);
            dd.show();
        });
    }
    
    
    public void checkDateBetweenThem(Button button1, Button button2) {
        Date dateOfBeginning = null;
        try {
            dateOfBeginning = formatterUIFormat.parse((String) button1.getText());
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        Date dateOfEnding = null;
        try {
            dateOfEnding = formatterUIFormat.parse((String) button2.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int comparison = dateOfBeginning.compareTo(dateOfEnding);
        if (comparison > nullEquivalent && button1 == lastModificationDateButton) {
            Toast.makeText(requireContext(),
                           "la date de début doit être antérieure à celle de fin",
                           Toast.LENGTH_LONG).show();
            button1.setText(null);
        } else if (comparison < nullEquivalent && button2 != selledDateButton) {
            Toast.makeText(requireContext(),
                           "la date fin doit être ultérieure à celle de début",
                           Toast.LENGTH_LONG).show();
            button1.setText(null);
        
        }
    }
    
    public void checkDateWithToday(Date date, Button button) {
        Date   today         = new Date();
        String resultOfToday = formatterUIFormat.format(today);
        Date   dateOfToday   = null;
        try {
            dateOfToday = formatterUIFormat.parse(resultOfToday);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        int todayCompareWithDate = dateOfToday.compareTo(date);
        if (todayCompareWithDate < nullEquivalent) {
            Toast.makeText(requireContext(),
                           "la date sélectionnée ne peut être ultérieur à celle d'aujourd'hui",
                           Toast.LENGTH_LONG).show();
            button.setText(null);
        }
    }
    
    public void validationButtonManagement() {
        ImageButton localCreateNewEstateButton = mFragmentFormBinding.validateFormButton;
        localCreateNewEstateButton.setOnClickListener(v -> getEstateLocation());
    }
    
    private void getEstate(long id) {
        formViewModel.observeEstate(id).observe(getViewLifecycleOwner(), this::receiveEstate);
    }
    
    public String updateDateManagement() {
        Date   today         = new Date();
        String resultOfToday = formatterUIFormat.format(today);
        Date   dateOfToday   = null;
        
        if (lastModificationDateButton.getText() != null) {
            return mFragmentFormBinding.updateDateValueFragmentFormDatePickerButton.getText()
                                                                                   .toString();
        } else {
            try {
                dateOfToday = formatterUIFormat.parse(resultOfToday);
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
            return dateOfToday.toString();
        }
    }
    
    public void getEstateLocation() {
        final Observer<List<ResultsItem>> observeResponse = resultsItems -> {
            estateLat      = resultsItems.get(0).getGeometry().getLocation().getLat();
            estateLng      = resultsItems.get(0).getGeometry().getLocation().getLng();
            estateLocation = new LatLng(estateLat, estateLng);
            estateRaw      = createEstateRaw();
            createNewEstateManagement();
        };
        formViewModel.observeResponse(mFragmentFormBinding.locationValueFragmentForm.getText()
                                                                                    .toString())
                     .observe(requireActivity(), observeResponse);
    
    }
    
    public boolean sellStatusManagement() {
        boolean checked = (isItSellCheckBox).isChecked();
        if (checked) {
            selledDateButton.setVisibility(View.VISIBLE);
            sellTitleTextView.setText(R.string.Is_it_sell);
            return true
//                    selledDateButton.getText().length() > nullEquivalent
                    ;
        } else {
            selledDateButton.setVisibility(View.INVISIBLE);
            sellTitleTextView.setText(R.string.Sell_date);
            // TODO sell status
            return false;
        }
    }
    
    private void receiveEstate(Estate estate) {
        //TODO main picture
        mFragmentFormBinding.valueOfEstateTypeFragmentForm.setPrompt(estate.getType());
        mFragmentFormBinding.valueOfEstateCityFragmentForm.setText(estate.getCity());
        mFragmentFormBinding.valueOfEstatePriceFragmentForm.setText(Long.toString(estate.getPrice()));
        //TODO picture list
        if (estate.getDescription() != null) {
            mFragmentFormBinding.contentDescriptionFragmentForm.setText(estate.getDescription());
        }
        mFragmentFormBinding.surfaceValueFragmentForm.setText(Long.toString(estate.getSurface()));
        if (estate.getAdress() != null) {
            mFragmentFormBinding.locationValueFragmentForm.setText(estate.getAdress());
            //TODO adress picture
        }
        mFragmentFormBinding.roomsValueFragmentForm.setText(Long.toString(estate.getRooms()));
        mFragmentFormBinding.contactValueFragmentForm.setText(estate.getAgent());
        mFragmentFormBinding.updateDateValueFragmentFormDatePickerButton.setText(estate.getUpdateDate());
        if (estate.getSellDate() != null) {
            mFragmentFormBinding.isSellCheckbox.setChecked(false);
            mFragmentFormBinding.updateDateValueFragmentFormDatePickerButton.setText(estate.getSellDate());
        } else {
            mFragmentFormBinding.isSellCheckbox.setChecked(true);
        }
        autoIncremented = (int) estate.getId();
    }
    
    private EstateRaw createEstateRaw() {
        return new EstateRaw(sellStatusManagement(),
                             mFragmentFormBinding.valueOfEstateTypeFragmentForm.getSelectedItem()
                                                                               .toString(),
                             mFragmentFormBinding.valueOfEstateCityFragmentForm.getText()
                                                                               .toString(),
                             mFragmentFormBinding.valueOfEstatePriceFragmentForm.getText()
                                                                                .toString(),
                             //TODO
                             "mainpicturevalue",
                             autoIncremented,
                             mFragmentFormBinding.contentDescriptionFragmentForm.getText()
                                                                                .toString(),
                             mFragmentFormBinding.surfaceValueFragmentForm.getText().toString(),
                             mFragmentFormBinding.roomsValueFragmentForm.getText().toString(),
                             mFragmentFormBinding.locationValueFragmentForm.getText().toString(),
                             mFragmentFormBinding.contactValueFragmentForm.getText().toString(),
                             updateDateManagement(),
                             mFragmentFormBinding.sellDateValueFragmentFormDatePickerButton.getText()
                                                                                           .toString(),
                             estateLat,
                             estateLng);
    
    }
}

