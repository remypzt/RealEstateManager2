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
    private TextView               mTextView;
    private DetailsViewModel       detailsViewModel;
    private Estate                 estate;
    private FragmentDetailsBinding fragmentDetailsBinding;
    
    //------------------------------------------------------//
    // ------------------   LifeCycle   ------------------- //
    //------------------------------------------------------//
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentDetailsBinding = FragmentDetailsBinding.inflate(inflater, container, false);
        mTextView              = fragmentDetailsBinding.contactValueFragmentDetails;
        configureViewModel();
        return fragmentDetailsBinding.getRoot();
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
    
    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        id = Long.parseLong(getArguments().get("id").toString());
        detailsViewModel.observeEstate(id)
                        .observe(getViewLifecycleOwner(), estate -> updateUI(estate));
    }
    
    //------------------------------------------------------//
    // ------------------   Functions   ------------------- //
    //------------------------------------------------------//
    
    private void updateUI(Estate estate) {
        if (estate != null) {
            mTextView.setText(estate.getType());
            //TODO if (mFragmentFormBinding.contentDescriptionFragmentForm.getText().length() <1){
            //			mFragmentFormBinding.contentDescriptionFragmentForm.setText("Aucune description n'a été renseignée pour le moment");
            //		}
        }
    }
    
    private Estate getEstate(long id) {
    
        estate = detailsViewModel.observeEstate(id).getValue();
        return estate;
    }
    
    //------------------------------------------------------//
// ----------------- Navigation, Menu, UI ------------- //
//------------------------------------------------------//

}