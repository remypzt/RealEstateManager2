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
import androidx.lifecycle.ViewModelProviders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import remy.pouzet.realestatemanager2.databinding.FragmentFormBinding;
import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.injections.Injection;
import remy.pouzet.realestatemanager2.injections.ViewModelsFactory;
import remy.pouzet.realestatemanager2.utils.Utils;
import remy.pouzet.realestatemanager2.viewmodels.FormViewModel;
import remy.pouzet.realestatemanager2.views.Bases.BaseFragment;

public class FormFragment extends BaseFragment {
	//------------------------------------------------------//
// ------------------   Variables   ------------------- //
//------------------------------------------------------//
	public                            ImageButton      createNewEstateButton;
	private                           FormViewModel    formViewModel;
	public                            ImageButton      editEstateButton;
	public                            Button           lastModificationDateButton;
	public                            Button           selledDateButton;
	public                            Date             dateBackEndFormat;
	public                            CheckBox         isItSellCheckBox;
	public                            TextView         sellTitleTextView;
	//-- Dates variables --//
	@SuppressLint("SimpleDateFormat") SimpleDateFormat formatterUIFormat = new SimpleDateFormat("dd/MM/yyyy");
	Calendar c    = Calendar.getInstance();
	int      year = c.get(Calendar.YEAR), month = c.get(Calendar.MONTH), day = c.get(Calendar.DAY_OF_MONTH);
	String updateDateInRightFormat;
	String selledDateInRightFormat;
	
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
		return mFragmentFormBinding.getRoot();
	}
	
	@Override
	public View provideYourFragmentView(LayoutInflater inflater,
	                                    ViewGroup parent,
	                                    Bundle savedInstanceState) {
		return null;
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.configureViewModel();
		isItSellCheckBox.setOnClickListener(v -> sellStatusManagement());
		datePickerButtonsManagement(lastModificationDateButton, selledDateButton);
		datePickerButtonsManagement(selledDateButton, lastModificationDateButton);
		validationButtonManagement();
	}
	
	private void configureViewModel() {
		ViewModelsFactory mViewModelFactory = Injection.provideViewModelFactory(requireContext());
		this.formViewModel = ViewModelProviders
				.of(this, mViewModelFactory)
				.get(FormViewModel.class);
	}
	
	//------------------------------------------------------//
// ------------------   Functions   ------------------- //
//------------------------------------------------------//
	
	public boolean sellStatusManagement() {
		boolean checked = ((isItSellCheckBox)).isChecked();
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
	
	public void viewBindingManagement() {
		lastModificationDateButton = mFragmentFormBinding.updateDateValueFragmentFormDatePickerButton;
		selledDateButton           = mFragmentFormBinding.sellDateValueFragmentFormDatePickerButton;
		isItSellCheckBox           = mFragmentFormBinding.isSellCheckbox;
		sellTitleTextView          = mFragmentFormBinding.sellTitleFragmentForm;
	}
	
	public void datePickerButtonsManagement(Button button1,
	                                        Button button2) {
		@SuppressLint("SimpleDateFormat") SimpleDateFormat formatterBackEndFormat = new SimpleDateFormat("yyyyMMdd");
		button1.setOnClickListener(v -> {
			DatePickerDialog dd = new DatePickerDialog(requireContext(), (view, year, monthOfYear, dayOfMonth) -> {
				try {
					String dateInStringUIFormat = Utils.UIformat(dayOfMonth, monthOfYear, year);
					
					Date dateUIFormat = formatterUIFormat.parse(dateInStringUIFormat);
					
					if (dayOfMonth > 9) {
						String dateInStringBEformat = Utils.BEformat(dayOfMonth, monthOfYear, year);
						dateBackEndFormat = formatterBackEndFormat.parse(dateInStringBEformat);
					} else {
						String dateInStringBEformatException = Utils.BEformatException(dayOfMonth, monthOfYear, year);
						dateBackEndFormat = formatterBackEndFormat.parse(dateInStringBEformatException);
					}
					
					button1.setText(formatterUIFormat.format(dateUIFormat));
					
					// check that dates cannot be paradoxal between them
					if (button1
							    .getText()
							    .toString()
							    .length() > 0 && button2
									                     .getText()
									                     .toString()
									                     .length() > 0) {
						checkDateBetweenThem(button1, button2);
					}
					
					// check dates cannot be place in the future
					if (button1 == lastModificationDateButton) {
						Date dateOfBeginning = formatterUIFormat.parse(dateInStringUIFormat);
						checkDateWithToday(dateOfBeginning, button1);
						
						updateDateInRightFormat = formatterBackEndFormat.format(dateBackEndFormat);
					} else {
						Date dateOfEnding = formatterUIFormat.parse(dateInStringUIFormat);
						checkDateWithToday(dateOfEnding, button1);
						selledDateInRightFormat = formatterBackEndFormat.format(dateBackEndFormat);
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}, year, month, day);
			dd.show();
		});
	}
	
	public void validationButtonManagement() {
		//TODO condition create or edit
		createNewEstateButton = mFragmentFormBinding.validateFormButton;
		createNewEstateButton.setOnClickListener(v -> createNewEstateManagement());
	}
	
	public void checkDateBetweenThem(Button button1,
	                                 Button button2) {
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
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		int comparison = dateOfBeginning.compareTo(dateOfEnding);
		if (comparison > 0 && button1 == lastModificationDateButton) {
			Toast
					.makeText(requireContext(), "la date de début doit être antérieure à celle de fin", Toast.LENGTH_LONG)
					.show();
			button1.setText(null);
		} else if (comparison < 0 && button2 != selledDateButton) {
			Toast
					.makeText(requireContext(), "la date fin doit être ultérieure à celle de début", Toast.LENGTH_LONG)
					.show();
			button1.setText(null);
			
		}
	}
	
	// check dates cannot be place in the future
	public void checkDateWithToday(Date date,
	                               Button button) {
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
		if (todayCompareWithDate < 0) {
			Toast
					.makeText(requireContext(), "la date sélectionnée ne peut être ultérieur à celle d'aujourd'hui", Toast.LENGTH_LONG)
					.show();
			button.setText(null);
		}
	}
	
	private void createNewEstateManagement() {
		if (checkFormData()) {
			//TODO ternaire surface and value could be null
			Estate estate = new Estate("todo type", mFragmentFormBinding.valueOfEstateCityFragmentForm
					.getText()
					.toString(), Integer.parseInt(mFragmentFormBinding.valueOfEstatePriceFragmentForm
							                              .getText()
							                              .toString()), "todo mainpicture", 0, mFragmentFormBinding.contentDescriptionFragmentForm
					                           .getText()
					                           .toString(), Integer.parseInt(mFragmentFormBinding.surfaceValueFragmentForm
							                                                         .getText()
							                                                         .toString()), Integer.parseInt(mFragmentFormBinding.roomsValueFragmentForm
									                                                                                        .getText()
									                                                                                        .toString()), "todo adress", "todo status", "todo contact"
//				mFragmentFormBinding.contactValueFragmentForm.getText().toString()
			);
			this.formViewModel.createEstate(estate);
		}
	}
	
	public boolean checkFormData() {
		if (sellStatusManagement()) {
			if (selledDateButton
					    .getText()
					    .length() < 1) {
				ShowSnackBar(mFragmentFormBinding.getRoot(), "sell date cannot be null");
				return false;
			}
		}
//		else if (){}
//		String type, notnull
//		String city, notnull
//		int price, notnull
//		String mainPicture, ?
//		String description, ifnull
//		String adress, ?
//		String status, ?
//		String agent, notnull
//	              List<String> pOI, ?
		//TODO check form
		return true;
	}
	
	private void getEstate(long id) {
//		this.detailsViewModel.getEstate(id).observe(getViewLifecycleOwner(), this::updateUI);
	}
	
	private void updateEstate(Estate estate) {
		//TODO
//		estate.setSelected(!estate.getSelected());
		this.formViewModel.updateEstate(estate);
	}
	
}