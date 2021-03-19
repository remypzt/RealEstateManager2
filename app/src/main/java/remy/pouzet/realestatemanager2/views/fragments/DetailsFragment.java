package remy.pouzet.realestatemanager2.views.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import remy.pouzet.realestatemanager2.R;
import remy.pouzet.realestatemanager2.databinding.FragmentDetailsBinding;
import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.utils.OnMapAndViewReadyListener;
import remy.pouzet.realestatemanager2.viewmodels.DetailsViewModel;
import remy.pouzet.realestatemanager2.views.bases.BaseFragment;

public class DetailsFragment extends BaseFragment implements GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener, OnMapReadyCallback {
    //------------------------------------------------------//
    // ------------------   Variables   ------------------- //
    // ------------------------------------------------------//
    
    public  long                   id;
    private TextView               typeValueTextView;
    private TextView               cityValueTextView;
    private TextView               priceValueTextView;
    private TextView               descriptionValueTextView;
    private TextView               surfaceValueTextView;
    private TextView               locationValueTextView;
    private TextView               roomsValueTextView;
    private TextView               contactValueTextView;
    private TextView               lastUpdateValueTextView;
    private TextView               sellDateValueTextView;
    private TextView               sellDateTitleTextView;
    private DetailsViewModel       detailsViewModel;
    private Estate                 estate;
    private GoogleMap              map;
    private FragmentDetailsBinding fragmentDetailsBinding;
    
    private static final LatLng ADELAIDE = new LatLng(-34.92873, 138.59995);
    
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
//        mapFragment.getMapAsync(this);
        
        return fragmentDetailsBinding.getRoot();
    }
    
    @Override public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        
        if (ActivityCompat.checkSelfPermission(requireContext(),
                                               Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat
                                                                                                                                         .checkSelfPermission(
                                                                                                                                                 requireContext(),
                                                                                                                                                 Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
//        map.setMyLocationEnabled(true);
        showAdelaide(null);

//        map.setOnMyLocationButtonClickListener(this);
//        map.setOnMyLocationClickListener(this);
//                enableMyLocation();
    
    }
    
    public void showAdelaide(View v) {
        // Wait until map is ready
        if (map == null) {
            return;
        }
        
        // Center camera on Adelaide marker
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ADELAIDE, 10f));
    }
    
    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
//    private void showMissingPermissionError() {
//        PermissionUtils.PermissionDeniedDialog
//                .newInstance(true).show(getSupportFragmentManager(), "dialog");
//    }
    @Override public View provideYourFragmentView(LayoutInflater inflater,
                                                  ViewGroup parent,
                                                  Bundle savedInstanceState) {
        return null;
    }
    
    private void enableMyLocation() {
        // [START maps_check_location_permission]
        if (ContextCompat.checkSelfPermission(requireContext(),
                                              Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (map != null) {
                map.setMyLocationEnabled(true);
            }
        } else {
            // Permission to access the location is missing. Show rationale and request permission
//            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
//                                              Manifest.permission.ACCESS_FINE_LOCATION, true);
        }
        // [END maps_check_location_permission]
    }
    
    @Override public boolean onMyLocationButtonClick() {

//        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
//            return;
//        }
//
//        if (PermissionUtils.isPermissionGranted(permissions, grantResults, Manifest.permission.ACCESS_FINE_LOCATION)) {
//            // Enable the my location layer if the permission has been granted.
//            enableMyLocation();
//        } else {
//            // Permission was denied. Display an error message
//            // Display the missing permission error dialog when the fragments resume.
//            permissionDenied = true;
//        }
//    }

//    @Override
//    protected void onResumeFragments() {
//        super.onResumeFragments();
//        if (permissionDenied) {
//            // Permission was not granted, display error dialog.
//            showMissingPermissionError();
//            permissionDenied = false;
//        }
//    }
    
    @Override public void onMyLocationClick(@NonNull Location location) {
//        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }
    
    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        id = Long.parseLong(getArguments().get("id").toString());
        detailsViewModel.observeEstate(id)
                        .observe(getViewLifecycleOwner(), estate -> updateUI(estate));
    }
    
    //------------------------------------------------------//
    // ------------------   Functions   ------------------- //
    //------------------------------------------------------//
    
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
    
    private void updateUI(Estate estate) {
        if (estate != null) {
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
                configureLiteMap();
            }
    
            if (!estate.getAdress().contentEquals("location value")) {
                locationValueTextView.setText(estate.getAdress());
                // location picture
            }
    
        }
    }
    
    private void configureLiteMap() {
    
    }
    
    private Estate getEstate(long id) {
        
        estate = detailsViewModel.observeEstate(id).getValue();
        return estate;
    }
    
}