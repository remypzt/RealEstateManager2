package remy.pouzet.realestatemanager2.views.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import remy.pouzet.realestatemanager2.R;
import remy.pouzet.realestatemanager2.databinding.FragmentDetailsBinding;
import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.datas.services.realapi.pojos.ResultsItem;
import remy.pouzet.realestatemanager2.utils.IOnBackPressed;
import remy.pouzet.realestatemanager2.utils.OnMapAndViewReadyListener;
import remy.pouzet.realestatemanager2.viewmodels.DetailsViewModel;
import remy.pouzet.realestatemanager2.views.bases.BaseFragment;
import remy.pouzet.realestatemanager2.views.fragments.AdaptersAndViewHolders.AlternatesPicturesAdapter;

public class DetailsFragment extends BaseFragment implements OnMapReadyCallback, IOnBackPressed {
	
	///////////////////////////////////////////////////////////////////////////
	// VARIABLES
	///////////////////////////////////////////////////////////////////////////
	private final List<String>     uriStringList  = new ArrayList<>();
	private final boolean          isFromForm     = false;
	private       LatLng           estateLocation = new LatLng(-34.92873, 138.59995);
	private       String           mainPictureURI;
	private       DetailsViewModel detailsViewModel;
	private       GoogleMap        map;
	
	public AlternatesPicturesAdapter alternatesPicturesAdapter;
	public long                      id;
	public double                    estateLat, estateLng;
	public String adress;
	public Bundle bundle = new Bundle();
	
	///////////////////////////////////////////////////////////////////////////
	// BINDING
	///////////////////////////////////////////////////////////////////////////
	
	public  RecyclerView           recyclerView;
	private TextView               typeValueTextView;
	private TextView               cityValueTextView;
	private FragmentDetailsBinding fragmentDetailsBinding;
	private TextView               priceValueTextView;
	private TextView               descriptionValueTextView;
	private TextView               surfaceValueTextView;
	private TextView               locationValueTextView;
	private TextView               roomsValueTextView;
	private TextView               contactValueTextView;
	private TextView               lastUpdateValueTextView;
	private TextView               sellDateValueTextView;
	private TextView               sellDateTitleTextView;
	private ImageView              mainPicture;
	
	public void viewBindingManagement() {
		typeValueTextView        = fragmentDetailsBinding.typeTitleFragmentDetails;
		cityValueTextView        = fragmentDetailsBinding.estateCityTitleFragmentDetails;
		priceValueTextView       = fragmentDetailsBinding.estatePriceFragmentDetails;
		descriptionValueTextView = fragmentDetailsBinding.contentDescriptionFragmentDetails;
		surfaceValueTextView     = fragmentDetailsBinding.surfaceValueFragmentDetails;
		locationValueTextView    = fragmentDetailsBinding.locationValueFragmentDetails;
		roomsValueTextView       = fragmentDetailsBinding.roomsValueFragmentDetails;
		contactValueTextView     = fragmentDetailsBinding.contactValueFragmentDetails;
		lastUpdateValueTextView  = fragmentDetailsBinding.updateDateValueFragmentDetails;
		sellDateTitleTextView    = fragmentDetailsBinding.sellDateFragmentDetails;
		sellDateValueTextView    = fragmentDetailsBinding.sellDateValueFragmentDetails;
		mainPicture              = fragmentDetailsBinding.chosenMainPictureDetailsFragment;
		recyclerView             = fragmentDetailsBinding.estatePicturesListFragmentDetails;
	}
	
	///////////////////////////////////////////////////////////////////////////
	// LIFECYCLE
	///////////////////////////////////////////////////////////////////////////
	
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container,
	                         Bundle savedInstanceState) {
		fragmentDetailsBinding = FragmentDetailsBinding.inflate(inflater, container, false);
		viewBindingManagement();
		configureViewModel();
		SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(
				R.id.lite_map_details_fragment);
		new OnMapAndViewReadyListener(mapFragment, this::onMapReady);
		mapFragment.getMapAsync(this);
		return fragmentDetailsBinding.getRoot();
	}
	
	@Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		configureRecyclerView();
		id = Long.parseLong(getArguments().get("id").toString());
		detailsViewModel.observeEstate(id)
		                .observe(getViewLifecycleOwner(), estate -> updateUI(estate));
	}
	
	@Override public void onMapReady(GoogleMap googleMap) {
		map = googleMap;
	}
	
	///////////////////////////////////////////////////////////////////////////
	// CONFIGURATIONS
	///////////////////////////////////////////////////////////////////////////
	
	public void configureRecyclerView() {
		this.alternatesPicturesAdapter = new AlternatesPicturesAdapter(uriStringList,
		                                                               requireContext(),
		                                                               isFromForm,
		                                                               null);
		this.recyclerView.setAdapter(this.alternatesPicturesAdapter);
		this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
		                                                           RecyclerView.HORIZONTAL,
		                                                           false));
	}
	
	@Override
	public View provideYourFragmentView(LayoutInflater inflater,
	                                    ViewGroup parent,
	                                    Bundle savedInstanceState) {
		return null;
	}
	
	private void configureViewModel() {
		detailsViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
	}
	
	///////////////////////////////////////////////////////////////////////////
	// FUNCTIONS
	///////////////////////////////////////////////////////////////////////////
	
	private void updateUI(Estate estate) {
		if (estate != null) {
			adress = estate.getAdress();
			typeValueTextView.setText(estate.getType());
			cityValueTextView.setText(estate.getCity());
			priceValueTextView.setText(estate.getPrice() + "€");
			if (estate.getDescription().isEmpty()) {
				descriptionValueTextView.setText(
						"Aucune description n'a été renseignée pour le moment");
			} else {
				descriptionValueTextView.setText(estate.getDescription());
			}
			surfaceValueTextView.setText(String.valueOf(estate.getSurface()));
			roomsValueTextView.setText(String.valueOf(estate.getRooms()));
			contactValueTextView.setText(estate.getAgent());
			lastUpdateValueTextView.setText(estate.getUpdateDate());
			if (estate.getSellDate().isEmpty()) {
				sellDateValueTextView.setVisibility(View.INVISIBLE);
				sellDateTitleTextView.setVisibility(View.INVISIBLE);
			} else {
				sellDateValueTextView.setText(estate.getSellDate());
			}
			if (!estate.getAdress().contentEquals("location value")) {
				locationValueTextView.setText(estate.getAdress());
				configureLiteMap();
			}
			if (estate.getMainPicture() != null) {
				mainPictureURI = estate.getMainPicture();
				setPic(mainPicture);
			}
			if (estate.getGaleryPictures() != null) {
				this.uriStringList.clear();
				this.uriStringList.addAll(estate.getGaleryPictures());
				this.alternatesPicturesAdapter.notifyDataSetChanged();
			}
		}
	}
	
	
	private void setPic(ImageView imageView) {
		// Get the dimensions of the View
		int targetW = 150;
		int targetH = 150;
		if (imageView.getWidth() != 0) {
			targetW = imageView.getWidth();
			targetH = imageView.getHeight();
		}
		
		// Get the dimensions of the bitmap
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inJustDecodeBounds = true;
		
		BitmapFactory.decodeFile(mainPictureURI, bmOptions);
		
		int photoW = bmOptions.outWidth;
		int photoH = bmOptions.outHeight;
		
		// Determine how much to scale down the image
		int scaleFactor = Math.max(1, Math.min(photoW / targetW, photoH / targetH));
		
		// Decode the image file into a Bitmap sized to fill the View
		bmOptions.inJustDecodeBounds = false;
		bmOptions.inSampleSize       = scaleFactor;
		bmOptions.inPurgeable        = true;
		
		Bitmap bitmap = BitmapFactory.decodeFile(mainPictureURI, bmOptions);
		imageView.setImageBitmap(bitmap);
	}
	
	@Override public boolean onBackPressed() {
		
		if (getArguments().getBoolean("isStartedFromMap") && detailsViewModel.isTablet()) {
			MapFragment mapFragment = new MapFragment();
			bundle.putBoolean("isStartedFromMap", false);
			mapFragment.setArguments(bundle);
			getActivity().getSupportFragmentManager()
			             .beginTransaction()
			             .replace(R.id.second_frame_fragment, mapFragment, "VISIBLE_FRAGMENT")
			             .commit();
			return true;
		} else {
			return false;
		}
	}
	
	//////////////////////////  LiteMap  /////////////////////////////////
	
	private void configureLiteMap() {
		showEstateLocation();
	}
	
	public void showEstateLocation() {
		if (map == null) {
			return;
		}
		getEstateLocation();
	}
	
	public void getEstateLocation() {
		final Observer<List<ResultsItem>> observeResponse = resultsItems -> {
			if (resultsItems.get(0) != null) {
				estateLat      = resultsItems.get(0).getGeometry().getLocation().getLat();
				estateLng      = resultsItems.get(0).getGeometry().getLocation().getLng();
				estateLocation = new LatLng(estateLat, estateLng);
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(estateLocation, 10));
			} else {
				showLongSnackBar(requireView(), "adress error");
			}
			
		};
		detailsViewModel.observeResponse(adress).observe(requireActivity(), observeResponse);
	}
	
}