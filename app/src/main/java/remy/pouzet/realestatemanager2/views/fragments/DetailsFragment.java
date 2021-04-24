package remy.pouzet.realestatemanager2.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import remy.pouzet.realestatemanager2.R;
import remy.pouzet.realestatemanager2.databinding.FragmentDetailsBinding;
import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.datas.services.realapi.pojos.ResultsItem;
import remy.pouzet.realestatemanager2.utils.OnMapAndViewReadyListener;
import remy.pouzet.realestatemanager2.viewmodels.DetailsViewModel;
import remy.pouzet.realestatemanager2.views.bases.BaseFragment;

public class DetailsFragment extends BaseFragment implements OnMapReadyCallback {
	//------------------------------------------------------//
	// ------------------   Variables   ------------------- //
	// ------------------------------------------------------//
	
	public long   id;
	public double estateLat, estateLng;
	public        String                 adress;
	private       LatLng                 estateLocation = new LatLng(-34.92873, 138.59995);
	private       TextView               typeValueTextView;
	private       TextView               cityValueTextView;
	private       TextView               priceValueTextView;
	private final boolean                tabletMode     = false;
	private       TextView               descriptionValueTextView;
	private       TextView               surfaceValueTextView;
	private       TextView               locationValueTextView;
	private       TextView               roomsValueTextView;
	private       TextView               contactValueTextView;
	private       TextView               lastUpdateValueTextView;
	private       TextView               sellDateValueTextView;
	private       TextView               sellDateTitleTextView;
	private       DetailsViewModel       detailsViewModel;
	private       Estate                 estate;
	private       GoogleMap              map;
	private       FragmentDetailsBinding fragmentDetailsBinding;
	
	//------------------------------------------------------//
	// ------------------   LifeCycle   ------------------- //
	//------------------------------------------------------//
	
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
		id     = Long.parseLong(getArguments().get("id").toString());
		detailsViewModel.observeEstate(id)
		                .observe(getViewLifecycleOwner(), estate -> updateUI(estate));
		
	}
	
	@Override
	public View provideYourFragmentView(LayoutInflater inflater,
	                                    ViewGroup parent,
	                                    Bundle savedInstanceState) {
		return null;
	}
	
	@Override public void onMapReady(GoogleMap googleMap) {
		map = googleMap;
		
	}
	
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
	}
	
	private void configureViewModel() {
		detailsViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
	}

//    @Override
//    protected void onResumeFragments() {
//        super.onResumeFragments();
//        if (permissionDenied) {
//            // Permission was not granted, display error dialog.
//            showMissingPermissionError();
//            permissionDenied = false;
//        }
//    }
	
	//------------------------------------------------------//
	// ------------------   Functions   ------------------- //
	//------------------------------------------------------//
	
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
			
		}
	}
	
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