package remy.pouzet.realestatemanager2.views.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import remy.pouzet.realestatemanager2.R;

public class MapFragment extends Fragment {
	
	//--------------------------------------------------//
	// ------------------   Variables   --------------- //
	//--------------------------------------------------//
	
	private static final int              PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
	public               LocationCallback locationCallback                         = new LocationCallback() {
		@SuppressLint("CommitPrefEdits") @Override public void onLocationResult(LocationResult locationResult) {
			if (locationResult == null) {
				return;
			}

//			for (Location location : locationResult.getLocations()) {
//				if (location != null) {
//					Log.e("MapViewFragment", "latitude: " + location.getLatitude() + " - longitude: " + location.getLongitude());
//					latitude  = location.getLatitude();
//					longitude = location.getLongitude();
//					mMap.clear();
//				}
//		}
		}
	};
	FusedLocationProviderClient mFusedLocationClient;
	private double latitude, longitude;
	private GoogleMap mMap;
	
	//------------------------------------------------------//
	// ------------------   Callbacks   ------------------- //
	//------------------------------------------------------//
	private boolean            locationPermissionGranted;
	public  OnMapReadyCallback callback = new OnMapReadyCallback() {
		@Override public void onMapReady(GoogleMap googleMap) {
			//TODO move camera on user location
			LatLng sydney = new LatLng(-34, 151);
			googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
			mMap = googleMap;
			
			updateLocationUI();
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
				locationPermissionGranted = true;
				updateLocationUI();
				getLocationAndCheckPermission();
			}
		}
	}
	
	@Nullable @Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_map, container, false);
	}
	
	//------------------------------------------------------//
	// ------------------   Functions   ------------------- //
	//------------------------------------------------------//
	
	@Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(
				R.id.map);
		
		if (mapFragment != null) {
			mapFragment.getMapAsync(callback);
		}
		
		requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
		                                Manifest.permission.ACCESS_FINE_LOCATION},
		                   PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
		mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext());
	}
	
	private void updateLocationUI() {
		if (mMap == null) {
			return;
		}
		try {
			mMap.setMyLocationEnabled(locationPermissionGranted);
		}
		catch (SecurityException e) {
			Log.e("Exception: %s", e.getMessage());
		}
	}
	
	@SuppressLint("MissingPermission") public void getLocationAndCheckPermission() {
		
		LocationRequest locationRequest = LocationRequest.create()
		                                                 .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
		                                                 .setSmallestDisplacement(50)
		                                                 .setInterval(20 * 1000);
		getLocationPermission();
		mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
	}
	
	private void getLocationPermission() {
		
		if (ContextCompat.checkSelfPermission(this.requireContext(),
		                                      android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
			locationPermissionGranted = true;
			
		} else {
			ActivityCompat.requestPermissions(requireActivity(),
			                                  new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
			                                  PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
		}
	}
}