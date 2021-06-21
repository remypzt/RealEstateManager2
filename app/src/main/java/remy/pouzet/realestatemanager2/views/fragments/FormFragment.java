package remy.pouzet.realestatemanager2.views.fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import remy.pouzet.realestatemanager2.views.fragments.AdaptersAndViewHolders.AlternatesPicturesAdapter;

import static android.app.Activity.RESULT_OK;

public class FormFragment extends BaseFragment implements AlternatesPicturesAdapter.ItemClickListener {
	///////////////////////////////////////////////////////////////////////////
	// VARIABLES
	///////////////////////////////////////////////////////////////////////////
	private static final int REQUEST_IMAGE_CAPTURE = 1;
	private static final int PICK_IMAGE            = 100;
	
	@SuppressLint("SimpleDateFormat") private final SimpleDateFormat formatterUIFormat = new SimpleDateFormat(
			"dd/MM/yyyy");
	private final                                   Calendar         c                 = Calendar.getInstance();
	private final                                   int              year              = c.get(
			Calendar.YEAR);
	private final                                   int              month             = c.get(
			Calendar.MONTH);
	private final                                   int              day               = c.get(
			Calendar.DAY_OF_MONTH);
	private final                                   boolean          isFromForm        = true;
	public                                          String           updateDateInRightFormat;
	public                                          String           selledDateInRightFormat;
	public                                          double           estateLat, estateLng;
	public  String                    adress;
	public  EstateRaw                 estateRaw;
	public  Long                      id;
	public  int                       autoIncremented = 0;
	public  int                       nullEquivalent  = 0;
	public  List<String>              uriStringList   = new ArrayList<>();
	public  AlternatesPicturesAdapter alternatesPicturesAdapter;
	public  boolean                   forMainPicture;
	public  ImageView                 mainPicture;
	public  RecyclerView              recyclerView;
	private String                    mainPhotoPath;
	private String                    galeryPhotoPath;
	private Estate                    estate;
	private Date                      dateBackEndFormat;
	private LatLng                    estateLocation  = new LatLng(-34.92873, 138.59995);
	///////////////////////////////////////////////////////////////////////////
	// BINDING
	///////////////////////////////////////////////////////////////////////////
	private ConstraintLayout          constraintLayout;
	private FormViewModel             formViewModel;
	private ImageButton               editEstateButton;
	private Button                    lastModificationDateButton;
	private Button                    selledDateButton;
	private CheckBox                  isItSellCheckBox;
	private TextView                  sellTitleTextView;
	private FragmentFormBinding       mFragmentFormBinding;
	private ImageButton               takeMainPhoto, takeMainAlternatePhoto, takeMediaPhoto, takeMediaAlternatePhoto;
	
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
	
	///////////////////////////////////////////////////////////////////////////
	// LIFECYCLE
	///////////////////////////////////////////////////////////////////////////
	
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container,
	                         Bundle savedInstanceState) {
		mFragmentFormBinding = FragmentFormBinding.inflate(inflater, container, false);
		viewBindingManagement();
		configureViewModel();
		return mFragmentFormBinding.getRoot();
	}
	
	public void viewBindingManagement() {
		lastModificationDateButton = mFragmentFormBinding.updateDateValueFragmentFormDatePickerButton;
		selledDateButton           = mFragmentFormBinding.sellDateValueFragmentFormDatePickerButton;
		isItSellCheckBox           = mFragmentFormBinding.isSellCheckbox;
		sellTitleTextView          = mFragmentFormBinding.sellTitleFragmentForm;
		constraintLayout           = mFragmentFormBinding.parentFragmentForm;
		takeMainPhoto              = mFragmentFormBinding.takeMainPhoto;
		takeMainAlternatePhoto     = mFragmentFormBinding.takeMainAltertanePhoto;
		takeMediaPhoto             = mFragmentFormBinding.takeMediaPhoto;
		takeMediaAlternatePhoto    = mFragmentFormBinding.takeMediaAlternatePhoto;
		mainPicture                = mFragmentFormBinding.chosenMainPictureFragmentForm;
		recyclerView               = mFragmentFormBinding.estatePicturesListFragmentForm;
	}
	
	///////////////////////////////////////////////////////////////////////////
	// CONFIGURATIONS
	///////////////////////////////////////////////////////////////////////////
	
	private void configureViewModel() {
		formViewModel = new ViewModelProvider(this).get(FormViewModel.class);
	}
	
	@Override
	public View provideYourFragmentView(LayoutInflater inflater,
	                                    ViewGroup parent,
	                                    Bundle savedInstanceState) {
		return null;
	}
	
	@Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		configureRecyclerView();
		setHideKeyboardOnTouch(requireContext(), getView());
		updateUIIfItsModification();
		isItSellCheckBox.setOnClickListener(v -> sellStatusManagement());
		photosManagement();
		datePickerButtonsManagement(lastModificationDateButton, selledDateButton);
		datePickerButtonsManagement(selledDateButton, lastModificationDateButton);
		validationButtonManagement();
	}
	
	///////////////////////////////////////////////////////////////////////////
	// FUNCTIONS
	///////////////////////////////////////////////////////////////////////////
	
	////////////////////////PHOTOS MANAGEMENT/////////////////////////////////
	
	public void photosManagement() {
		takeMainPhoto.setOnClickListener(v -> {
			forMainPicture = true;
			takePhoto();
		});
		takeMainAlternatePhoto.setOnClickListener(v -> {
			forMainPicture = true;
			takeAlternatePhoto();
		});
		takeMediaPhoto.setOnClickListener(v -> {
			forMainPicture = false;
			takePhoto();
		});
		takeMediaAlternatePhoto.setOnClickListener(v -> {
			forMainPicture = false;
			takeAlternatePhoto();
		});
		erasePhotoManagement();
	}
	
	public void takePhoto() {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// Ensure that there's a camera activity to handle the intent
		if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
			// Create the File where the photo should go
			File photoFile = null;
			try {
				photoFile = createImageFile();
			}
			catch (IOException ex) {
				// Error occurred while creating the File
			}
			// Continue only if the File was successfully created
			if (photoFile != null) {
				Uri photoURI = FileProvider.getUriForFile(requireContext(),
				                                          "com.example.android.fileprovider",
				                                          photoFile);
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
				startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
			}
		}
	}
	
	public void takeAlternatePhoto() {
		Intent gallery = new Intent(Intent.ACTION_OPEN_DOCUMENT,
		                            MediaStore.Images.Media.INTERNAL_CONTENT_URI);
		startActivityForResult(gallery, PICK_IMAGE);
	}
	
	private File createImageFile() throws
	                               IOException {
		// Create an image file name
		String timeStamp     = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String imageFileName = "JPEG_" + timeStamp + "_";
		File   storageDir    = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
		File image = File.createTempFile(imageFileName,  /* prefix */
		                                 ".jpg",         /* suffix */
		                                 storageDir      /* directory */);
		if (forMainPicture) {
			mainPhotoPath = image.getAbsolutePath();
		}
		// Save a file: path for use with ACTION_VIEW intents
		else {
			galeryPhotoPath = image.getAbsolutePath();
		}
		return image;
	}
	
	@Override public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		if (resultCode == RESULT_OK) {
			if (requestCode == REQUEST_IMAGE_CAPTURE) {
				if (forMainPicture) {
					mainPicture.setImageURI(Uri.parse(mainPhotoPath));
				} else {
					uriStringList.add(galeryPhotoPath);
					alternatesPicturesAdapter.notifyDataSetChanged();
				}
			}
			if (requestCode == PICK_IMAGE) {
				Uri uri = data.getData();
				if (forMainPicture) {
					mainPicture.setImageURI(uri);
				} else {
					uriStringList.add(uri.toString());
					alternatesPicturesAdapter.notifyDataSetChanged();
				}
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
	///////////////////////MODIFICATION MANAGEMENT///////////////////////
	
	private void erasePhotoManagement() {
	
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
	
	private void getEstate(long id) {
		formViewModel.observeEstate(id).observe(getViewLifecycleOwner(), this::receiveEstate);
	}
	
	public void configureRecyclerView() {
		this.alternatesPicturesAdapter = new AlternatesPicturesAdapter(uriStringList,
		                                                               requireContext(),
		                                                               isFromForm,
		                                                               this::onItemClickedListener);
		this.recyclerView.setAdapter(this.alternatesPicturesAdapter);
		this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
		                                                           RecyclerView.HORIZONTAL,
		                                                           false));
	}
	
	////////////////////////DATES MANAGEMENT//////////////////////////////////
	
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
		}
		catch (ParseException e) {
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
	
	@Override public void onItemClickedListener(View view, int position) {
		uriStringList.remove(position);
		alternatesPicturesAdapter.notifyDataSetChanged();
		
	}
	
	//////////////////////////////////OTHERS////////////////////////////////
	
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
				                                           }
				                                           catch (Exception ex) {
					                                           ex.printStackTrace();
				                                           }
			                                           },
			                                           year,
			                                           month,
			                                           day);
			dd.show();
		});
	}
	
	public boolean sellStatusManagement() {
		boolean checked = (isItSellCheckBox).isChecked();
		if (checked) {
			selledDateButton.setVisibility(View.VISIBLE);
			sellTitleTextView.setText(R.string.Is_it_sell);
			return true;
		} else {
			selledDateButton.setVisibility(View.INVISIBLE);
			sellTitleTextView.setText(R.string.Sell_date);
			return false;
		}
	}
	
	/////////////////////////VALIDATION MANAGEMENT///////////////////////////
	
	public void validationButtonManagement() {
		ImageButton localCreateNewEstateButton = mFragmentFormBinding.validateFormButton;
		localCreateNewEstateButton.setOnClickListener(v -> getEstateLocation());
	}
	
	public void getEstateLocation() {
		final Observer<List<ResultsItem>> observeResponse = resultsItems -> {
			if (resultsItems == null) {
				showIndefiniteSnackBar(mFragmentFormBinding.getRoot(), "adresse invalide");
			} else {
				estateLat      = resultsItems.get(0).getGeometry().getLocation().getLat();
				estateLng      = resultsItems.get(0).getGeometry().getLocation().getLng();
				estateLocation = new LatLng(estateLat, estateLng);
				estateRaw      = createEstateRaw();
				createNewEstateManagement();
			}
			
		};
		formViewModel.observeResponse(mFragmentFormBinding.locationValueFragmentForm.getText()
		                                                                            .toString())
		             .observe(requireActivity(), observeResponse);
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
	
	private EstateRaw createEstateRaw() {
		return new EstateRaw(sellStatusManagement(),
		                     mFragmentFormBinding.valueOfEstateTypeFragmentForm.getSelectedItem()
		                                                                       .toString(),
		                     mFragmentFormBinding.valueOfEstateCityFragmentForm.getText()
		                                                                       .toString(),
		                     mFragmentFormBinding.valueOfEstatePriceFragmentForm.getText()
		                                                                        .toString(),
		                     mainPhotoPath,
		                     uriStringList,
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
	
	private void receiveEstate(Estate estate) {
		if (estate.getMainPicture() != null) {
			mainPhotoPath = estate.getMainPicture();
			mainPicture.setImageURI(Uri.parse(mainPhotoPath));
		}
		mFragmentFormBinding.valueOfEstateTypeFragmentForm.setPrompt(estate.getType());
		mFragmentFormBinding.valueOfEstateCityFragmentForm.setText(estate.getCity());
		mFragmentFormBinding.valueOfEstatePriceFragmentForm.setText(Long.toString(estate.getPrice()));
		if (estate.getGaleryPictures() != null) {
			this.uriStringList.clear();
			this.uriStringList.addAll(estate.getGaleryPictures());
			this.alternatesPicturesAdapter.notifyDataSetChanged();
		}
		if (estate.getDescription() != null) {
			mFragmentFormBinding.contentDescriptionFragmentForm.setText(estate.getDescription());
		}
		mFragmentFormBinding.surfaceValueFragmentForm.setText(Long.toString(estate.getSurface()));
		if (estate.getAdress() != null) {
			mFragmentFormBinding.locationValueFragmentForm.setText(estate.getAdress());
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
}

