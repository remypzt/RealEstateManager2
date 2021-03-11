package remy.pouzet.realestatemanager2.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import remy.pouzet.realestatemanager2.databinding.FragmentDetailsBinding;
import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.viewmodels.DetailsViewModel;
import remy.pouzet.realestatemanager2.views.bases.BaseFragment;

public class DetailsFragment extends BaseFragment {
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
    private FragmentDetailsBinding fragmentDetailsBinding;
    
    //------------------------------------------------------//
    // ------------------   LifeCycle   ------------------- //
    //------------------------------------------------------//
    
    @Override public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentDetailsBinding = FragmentDetailsBinding.inflate(inflater, container, false);
        viewBindingManagement();
        configureViewModel();
        return fragmentDetailsBinding.getRoot();
    }
    
    @Override
    public View provideYourFragmentView(LayoutInflater inflater,
                                        ViewGroup parent,
                                        Bundle savedInstanceState) {
        return null;
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
            }
            // TODO locationValueTextView.setText(estate);
        }
    }
    
    private Estate getEstate(long id) {
    
        estate = detailsViewModel.observeEstate(id).getValue();
        return estate;
    }
    

}