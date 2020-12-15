package remy.pouzet.realestatemanager2.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.viewmodels.DetailsViewModel;
import remy.pouzet.realestatemanager2.views.bases.BaseFragment;

public class DetailsFragment extends BaseFragment {
    //------------------------------------------------------//
    // ------------------   Variables   ------------------- //
    // ------------------------------------------------------//
    
    public  long             id;
    private TextView         mTextView;
    private DetailsViewModel detailsViewModel;
    private Estate           estate;
    
    //------------------------------------------------------//
    // ------------------   LifeCycle   ------------------- //
    //------------------------------------------------------//
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        remy.pouzet.realestatemanager2.databinding.FragmentDetailsBinding localFragmentDetailsBinding = remy.pouzet.realestatemanager2.databinding.FragmentDetailsBinding
                .inflate(inflater, container, false);
        
        mTextView = localFragmentDetailsBinding.surfaceTitleFragmentDetails;
        id        = Long.parseLong(getArguments().get("id").toString());
        updateUI(getEstate(id));
    
        return localFragmentDetailsBinding.getRoot();
    }
    
    @Override
    public View provideYourFragmentView(LayoutInflater inflater,
                                        ViewGroup parent,
                                        Bundle savedInstanceState) {
        return null;
    }
    
    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.configureViewModel();
        
    }
    
    //------------------------------------------------------//
    // ------------------   Functions   ------------------- //
    //------------------------------------------------------//
    
    private void updateUI(Estate estate) {
        
        //TODO if (mFragmentFormBinding.contentDescriptionFragmentForm.getText().length() <1){
        //			mFragmentFormBinding.contentDescriptionFragmentForm.setText("Aucune description n'a été renseignée pour le moment");
        //		}
        mTextView.setText(estate.getType());
    }
    
    private Estate getEstate(long id) {
        estate = detailsViewModel.observeEstate(id).getValue();
        return estate;
    }
    
    //------------------------------------------------------//
// ----------------- Navigation, Menu, UI ------------- //
//------------------------------------------------------//
    
    private void configureViewModel() {
        detailsViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
    }
}