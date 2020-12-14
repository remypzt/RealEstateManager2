package remy.pouzet.realestatemanager2.views.fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import remy.pouzet.realestatemanager2.R;
import remy.pouzet.realestatemanager2.databinding.FragmentFormBinding;
import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.utils.Utils;
import remy.pouzet.realestatemanager2.viewmodels.FormViewModel;
import remy.pouzet.realestatemanager2.views.bases.BaseFragment;

public class FormFragment extends BaseFragment {
    
    //-- Dates variables --//
    @SuppressLint("SimpleDateFormat") SimpleDateFormat formatterUIFormat = new SimpleDateFormat(
            "dd/MM/yyyy");
    Calendar c    = Calendar.getInstance();
    int      year = c.get(Calendar.YEAR), month = c.get(Calendar.MONTH), day = c.get(Calendar.DAY_OF_MONTH);
    String updateDateInRightFormat;
    String selledDateInRightFormat;
    
    long id;
    private Estate estate;
    
    private FormViewModel       formViewModel;
    private ImageButton         editEstateButton;
    private Button              lastModificationDateButton;
    private Button              selledDateButton;
    private Date                dateBackEndFormat;
    private CheckBox            isItSellCheckBox;
    private TextView            sellTitleTextView;
    private FragmentFormBinding mFragmentFormBinding;
    
    //------------------------------------------------------//
// ------------------   LifeCycle   ------------------- //
//------------------------------------------------------//

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mFragmentFormBinding = FragmentFormBinding.inflate(inflater, container, false);
        viewBindingManagement();
        configureViewModel();
        return mFragmentFormBinding.getRoot();
    }

    @Override
    public View provideYourFragmentView(LayoutInflater inflater,
                                        ViewGroup parent,
                                        Bundle savedInstanceState) {
        return null;
    }
    
    public void viewBindingManagement() {
        lastModificationDateButton = mFragmentFormBinding.updateDateValueFragmentFormDatePickerButton;
        selledDateButton           = mFragmentFormBinding.sellDateValueFragmentFormDatePickerButton;
        isItSellCheckBox           = mFragmentFormBinding.isSellCheckbox;
        sellTitleTextView          = mFragmentFormBinding.sellTitleFragmentForm;
    }
    
    private void configureViewModel() {
        formViewModel = new ViewModelProvider(this).get(FormViewModel.class);
    }
    
    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        isItCreationOrModification();
        isItSellCheckBox.setOnClickListener(v -> sellStatusManagement());
        datePickerButtonsManagement(lastModificationDateButton, selledDateButton);
        datePickerButtonsManagement(selledDateButton, lastModificationDateButton);
        validationButtonManagement();
    }
    
    //------------------------------------------------------//
// ------------------   Functions   ------------------- //
//------------------------------------------------------//
    private void isItCreationOrModification() {
        if (getArguments() != null)  // then it's a modification
        {
            id = Long.parseLong(getArguments().get("id").toString());
            updateUI(getEstate(id));
        }
    }
    
    //------------------------------------------------------//
// ----------------- Navigation, Menu, UI ------------- //
//------------------------------------------------------//
    private void updateUI(Estate estate) {
        // Set estate data inside form
        
    }
    
    public boolean sellStatusManagement() {
        boolean checked = (isItSellCheckBox).isChecked();
        if (checked) {
            selledDateButton.setVisibility(View.VISIBLE);
            sellTitleTextView.setText("Sell date");
            return true;
        } else {
            selledDateButton.setVisibility(View.INVISIBLE);
            sellTitleTextView.setText("Is it Sell ?");
            return false;
        }
    }

    public void datePickerButtonsManagement(Button button1, Button button2) {
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

                            if (dayOfMonth > 9) {
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
                            if (button1
                                    .getText()
                                    .toString()
                                    .length() > 0 && button2
                                    .getText()
                                    .toString()
                                    .length() > 0) {
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

                                updateDateInRightFormat = formatterBackEndFormat
                                        .format(dateBackEndFormat);
                            } else {
                                Date dateOfEnding = formatterUIFormat
                                        .parse(dateInStringUIFormat);
                                checkDateWithToday(dateOfEnding,
                                        button1);
                                selledDateInRightFormat = formatterBackEndFormat
                                        .format(dateBackEndFormat);
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    },
                    year,
                    month,
                    day);
            dd.show();
        });
    }

    public void validationButtonManagement() {
        //TODO condition create or edit
        ImageButton localCreateNewEstateButton = mFragmentFormBinding.validateFormButton;
        localCreateNewEstateButton.setOnClickListener(v -> createNewEstateManagement());
    }

    public void checkDateBetweenThem(Button button1, Button button2) {
        Date dateOfBeginning = null;
        try {
            dateOfBeginning = formatterUIFormat.parse((String) button1.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date dateOfEnding = null;
        try {
            dateOfEnding = formatterUIFormat.parse((String) button2.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int comparison = dateOfBeginning.compareTo(dateOfEnding);
        if (comparison > 0 && button1 == lastModificationDateButton) {
            Toast
                    .makeText(requireContext(),
                            "la date de début doit être antérieure à celle de fin",
                            Toast.LENGTH_LONG)
                    .show();
            button1.setText(null);
        } else if (comparison < 0 && button2 != selledDateButton) {
            Toast
                    .makeText(requireContext(),
                            "la date fin doit être ultérieure à celle de début",
                            Toast.LENGTH_LONG)
                    .show();
            button1.setText(null);

        }
    }

    // check dates cannot be place in the future
    public void checkDateWithToday(Date date, Button button) {
        Date today = new Date();
        String resultOfToday = formatterUIFormat.format(today);
        Date dateOfToday = null;
        try {
            dateOfToday = formatterUIFormat.parse(resultOfToday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int todayCompareWithDate = dateOfToday.compareTo(date);
        if (todayCompareWithDate < 0) {
            Toast.makeText(requireContext(),
                           "la date sélectionnée ne peut être ultérieur à celle d'aujourd'hui",
                           Toast.LENGTH_LONG).show();
            button.setText(null);
        }
    }
    
    private Estate getEstate(long id) {
        estate = formViewModel.observeEstate(id).getValue();
        return estate;
    }
    
    public boolean checkFormData() {
        int localTrue         = 1;
        int localFalse        = 0;
        int nullEquivalent    = 1;
        int minimalWordLenght = 3;
        int localFakeboolean  = localTrue;
        
        if (sellStatusManagement() && selledDateButton.getText().length() < nullEquivalent) {
            showIndefiniteSnackBar(mFragmentFormBinding.getRoot(),
                    getString(R.string.sell_date_cannot_be_null));
            localFakeboolean = localFalse;
        }
        if (mFragmentFormBinding.valueOfEstateCityFragmentForm
                .getText()
                .length() < minimalWordLenght) {
            showIndefiniteSnackBar(mFragmentFormBinding.getRoot(),
                    getString(R.string.minimal_words_lenght));
            localFakeboolean = localFalse;
        }
        if (mFragmentFormBinding.valueOfEstatePriceFragmentForm
                .getText()
                .length() < nullEquivalent) {
            showIndefiniteSnackBar(mFragmentFormBinding.getRoot(),
                    getString(R.string.estate_price_cannot_be_null));
            localFakeboolean = localFalse;
        }
        if (mFragmentFormBinding.surfaceValueFragmentForm.getText().length() < nullEquivalent) {
            showIndefiniteSnackBar(mFragmentFormBinding.getRoot(),
                    getString(R.string.surface_value_cannot_be_null));
            localFakeboolean = localFalse;
        }
        if (mFragmentFormBinding.roomsValueFragmentForm.getText().length() < nullEquivalent) {
            showIndefiniteSnackBar(mFragmentFormBinding.getRoot(),
                    getString(R.string.rooms_value_cannot_be_null));
            localFakeboolean = localFalse;
        }
        if (mFragmentFormBinding.contactValueFragmentForm.getText().length() < nullEquivalent) {
            showIndefiniteSnackBar(mFragmentFormBinding.getRoot(),
                                   getString(R.string.contact_value_cannot_be_null));
            localFakeboolean = localFalse;
        }
        return localFakeboolean != localFalse;
//		String mainPicture, ?
//		String adress, ?
//		String agent, notnull
//	              List<String> pOI, ?
    }
    
    private void createNewEstateManagement() {
        if (checkFormData()) {
            Estate estate = new Estate(mFragmentFormBinding.valueOfEstateTypeFragmentForm.getSelectedItem()
                                                                                         .toString(),
                                       mFragmentFormBinding.valueOfEstateCityFragmentForm.getText()
                                                                                         .toString(),
                                       Integer.parseInt(mFragmentFormBinding.valueOfEstatePriceFragmentForm
                                                                .getText()
                                                                .toString()),
                                       "todo mainpicture",
                                       0,
                                       mFragmentFormBinding.contentDescriptionFragmentForm.getText()
                                                                                          .toString(),
                                       Integer.parseInt(mFragmentFormBinding.surfaceValueFragmentForm
                                                                .getText()
                                                                .toString()),
                                       Integer.parseInt(mFragmentFormBinding.roomsValueFragmentForm.getText()
                                                                                                   .toString()),
                                       mFragmentFormBinding.locationValueFragmentForm.getText()
                                                                                     .toString(),
                                       mFragmentFormBinding.contactValueFragmentForm.getText()
                                                                                    .toString(),
                                       mFragmentFormBinding.updateDateValueFragmentFormDatePickerButton
                                               .getText()
                                               .toString(),
                                       mFragmentFormBinding.isSellCheckbox.isChecked()
                                       ? mFragmentFormBinding.sellDateValueFragmentFormDatePickerButton
                                               .getText()
                                               .toString()
                                       : null);
            //TODO managecreateEstate and pass by update function if it's modification
            formViewModel.createEstate(estate);
//            this.formViewModel.createEstate(estate);
            showLongSnackBar(mFragmentFormBinding.getRoot(), "Success");
        }
    }
}