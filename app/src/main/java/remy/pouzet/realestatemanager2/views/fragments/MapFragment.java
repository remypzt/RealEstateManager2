package remy.pouzet.realestatemanager2.views.fragments;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import remy.pouzet.realestatemanager2.R;
import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.viewmodels.MapViewModel;

public class MapFragment extends Fragment {
	
	///////////////////////////////////////////////////////////////////////////
	// VARIABLES
	///////////////////////////////////////////////////////////////////////////
	
	private static final int          PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
	private final        List<Estate> estatesList                              = new ArrayList<>();
	public               double       estateLat, estateLng;
	public  MapViewModel mapViewModel;
	public  String       adress;
	public  int          position = -1;
	public  Bundle       bundle   = new Bundle();
	private double       userLatitude, userLongitude;
	private FusedLocationProviderClient mFusedLocationClient;
	private GoogleMap                   map;
	private boolean                     locationPermissionGranted;
	///////////////////////////////////////////////////////////////////////////
	// CALLBACK
	///////////////////////////////////////////////////////////////////////////
	public  OnMapReadyCallback          callback       = new OnMapReadyCallback() {
		@Override public void onMapReady(GoogleMap googleMap) {
			map = googleMap;
			checkLocationPermissionAndUpdateLocation();
			manageEstatesMarker();
		}
	};
	private LatLng                      estateLocation = new LatLng(-34.92873, 138.59995);
	
	///////////////////////////////////////////////////////////////////////////
	// LIFECYCLE
	///////////////////////////////////////////////////////////////////////////
	
	public void manageEstatesMarker() {
		mapViewModel.observeAllEstates()
		            .observe(getViewLifecycleOwner(), this::updateListAndShowEstatesLocation);
	}
	
	private void updateListAndShowEstatesLocation(List<Estate> estatesList) {
		if (estatesList != null) {
			setData(estatesList);
			getAndShowEstateLocation();
		}
	}

	
	public Bundle saveEstateId(long id) {
		
		bundle.putLong("id", id);
		return bundle;
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode,
	                                       String[] permissions,
	                                       int[] grantResults) {
		if (requestCode == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {// If request is cancelled, the result arrays are empty.
			if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				
				// permission was granted, proceed to the normal flow.
				checkLocationPermissionAndUpdateLocation();
				locationPermissionGranted = true;
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	// FUNCTIONS
	///////////////////////////////////////////////////////////////////////////
	
	public void setData(List<Estate> estates) {
		this.estatesList.clear();
		this.estatesList.addAll(estates);
	}
	
	public void getAndShowEstateLocation() {
		if (map == null) {
			return;
		}
		position = -1;
		for (Estate localEstate : estatesList) {
			position = position + 1;
			getAndConvertStringToLatLng();
			showEstateLocation(localEstate);
		}
		map.setOnInfoWindowClickListener(marker -> {
			View         view;
			CharSequence charSequence;
			if (mapViewModel.isTablet()) {
				DetailsFragment detailsFragment = new DetailsFragment();
				bundle.putBoolean("isStartedFromMap", true);
				detailsFragment.setArguments(saveEstateId((Long) marker.getTag()));
				
				getActivity().getSupportFragmentManager()
				             .beginTransaction()
				             .hide(this)
				
				             .add(R.id.second_frame_fragment, detailsFragment, "VISIBLE_FRAGMENT")
				             .addToBackStack(null)
				             .commit();
			} else {
				Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
				          .navigate(R.id.action_nav_map_fragment_to_nav_details,
				                    saveEstateId((Long) marker.getTag()));
			}
			
		});
	}
	
	public void getAndConvertStringToLatLng() {
		if (estatesList.get(position) != null) {
			estateLat      = estatesList.get(position).getLat();
			estateLng      = estatesList.get(position).getLng();
			estateLocation = new LatLng(estateLat, estateLng);
		} else {
			Snackbar.make(requireView(),
			              "Estate wich id is" + position + "as an invalid adress",
			              10000);
		}
	}
	
	public void showEstateLocation(Estate estate) {
		MarkerOptions localMarkerOptions = new MarkerOptions();
		String        type               = estate.getType();
		localMarkerOptions.position(estateLocation).title(type);
		Marker marker = map.addMarker(localMarkerOptions);
		marker.setTag(estate.getId());
	}
	
	@Nullable @Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_map, container, false);
		
	}
	
	@Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(
				R.id.map);
		configureViewModel();
		
		if (mapFragment != null) {
			mapFragment.getMapAsync(callback);
		}
		checkLocationPermissionAndUpdateLocation();
	}
	
	///////////////////////////////////////////////////////////////////////////
	// CONFIGURATIONS
	///////////////////////////////////////////////////////////////////////////
	private void configureViewModel() {
		mapViewModel = new ViewModelProvider(this).get(MapViewModel.class);
	}
	
	private void checkLocationPermissionAndUpdateLocation() {
		if (ContextCompat.checkSelfPermission(this.requireContext(),
		                                      android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
			mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext());
			
			updateLocationUI(map);
			locationPermissionGranted = true;
			
		} else {
			requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
			                   PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
		}
	}
	
	private void updateLocationUI(GoogleMap googleMap) {
		if (map == null) {
			return;
		}
		try {
			map.setMyLocationEnabled(locationPermissionGranted);
			moveCameraOnUserLocation(googleMap);
		}
		catch (SecurityException e) {
			Log.e("Exception: %s", e.getMessage());
		}
	}
	
	@SuppressLint("MissingPermission") private void moveCameraOnUserLocation(GoogleMap googleMap) {
		mFusedLocationClient.getLastLocation()
		                    .addOnCompleteListener(new OnCompleteListener<Location>() {
			                    @Override public void onComplete(@NonNull Task<Location> task) {
				                    Location location = task.getResult();
				                    if (location != null) {
					                    userLatitude  = location.getLatitude();
					                    userLongitude = location.getLongitude();
					                    LatLng userPosition = new LatLng(userLatitude,
					                                                     userLongitude);
					                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(
							                    userPosition));
					
					                    map.animateCamera(CameraUpdateFactory.zoomTo(10));
				                    }
			                    }
		                    });
	}
}