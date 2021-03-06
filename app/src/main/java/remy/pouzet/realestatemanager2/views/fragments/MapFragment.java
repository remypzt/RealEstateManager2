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

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import remy.pouzet.realestatemanager2.R;

public class MapFragment extends Fragment {
	
	//--------------------------------------------------//
	// ------------------   Variables   --------------- //
	//--------------------------------------------------//
	
	private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
	FusedLocationProviderClient mFusedLocationClient;
	private double latitude, longitude;
	private GoogleMap        mMap;

	
	//------------------------------------------------------//
	// ------------------   Callbacks   ------------------- //
	//------------------------------------------------------//
	private boolean            locationPermissionGranted;
	public  OnMapReadyCallback callback = new OnMapReadyCallback() {
		@Override public void onMapReady(GoogleMap googleMap) {
			mMap = googleMap;
			checkLocationPermissionAndUpdateLocation();
		}
	};
	
	//--------------------------------------------------//
	// ------------------ LifeCycle ------------------- //
	//--------------------------------------------------//
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
		
		if (mapFragment != null) {
			mapFragment.getMapAsync(callback);
		}
		checkLocationPermissionAndUpdateLocation();
	}
	
	//------------------------------------------------------//
	// ------------------   Functions   ------------------- //
	//------------------------------------------------------//
	
	private void checkLocationPermissionAndUpdateLocation() {
		if (ContextCompat.checkSelfPermission(this.requireContext(),
		                                      android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
			mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext());
			
			updateLocationUI(mMap);
			locationPermissionGranted = true;
			
		} else {
			requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
			                   PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
		}
	}
	
	private void updateLocationUI(GoogleMap googleMap) {
		if (mMap == null) {
			return;
		}
		try {
			mMap.setMyLocationEnabled(locationPermissionGranted);
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
					                    latitude  = location.getLatitude();
					                    longitude = location.getLongitude();
					                    LatLng userPosition = new LatLng(latitude, longitude);
					                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(
							                    userPosition));
					                    mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
				                    }
			                    }
		                    });
	}
	
}