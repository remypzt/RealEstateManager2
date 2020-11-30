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
import remy.pouzet.realestatemanager2.injections.Injection;
import remy.pouzet.realestatemanager2.injections.ViewModelsFactory;
import remy.pouzet.realestatemanager2.viewmodels.DetailsViewModel;
import remy.pouzet.realestatemanager2.views.bases.BaseFragment;


public class DetailsFragment extends BaseFragment {
	//------------------------------------------------------//
	// ------------------   Variables   ------------------- //
	// ------------------------------------------------------//
	
	public  long     id;
	private TextView mTextView;
	
	//------------------------------------------------------//
	// ------------------   LifeCycle   ------------------- //
	//------------------------------------------------------//
	
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container,
	                         Bundle savedInstanceState) {
		remy.pouzet.realestatemanager2.databinding.FragmentDetailsBinding localFragmentDetailsBinding = remy.pouzet.realestatemanager2.databinding.FragmentDetailsBinding.inflate(inflater, container, false);
		mTextView = localFragmentDetailsBinding.surfaceTitleFragmentDetails;
		id        = Long.parseLong(getArguments()
				                           .get("id")
				                           .toString());
		getEstate(id);
		return localFragmentDetailsBinding.getRoot();
	}
	
	@Override
	public View provideYourFragmentView(LayoutInflater inflater,
	                                    ViewGroup parent,
	                                    Bundle savedInstanceState) {
		return null;
	}
	
	@Override
	public void onViewCreated(@NonNull View view,
	                          @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		this.configureViewModel();
		
	}
	
	//------------------------------------------------------//
	// ------------------   Functions   ------------------- //
	//------------------------------------------------------//
	
	private void getEstate(long id) {
//		this.detailsViewModel.getEstate(id).observe(getViewLifecycleOwner(), this::updateUI);
	}
	
	private void updateUI(Estate estate) {
		//TODO if (mFragmentFormBinding.contentDescriptionFragmentForm.getText().length() <1){
		//			mFragmentFormBinding.contentDescriptionFragmentForm.setText("Aucune description n'a été renseignée pour le moment");
		//		}
		mTextView.setText(estate.getType());
	}
	
	//------------------------------------------------------//
// ----------------- Navigation, Menu, UI ------------- //
//------------------------------------------------------//
	
	private void configureViewModel() {
		ViewModelsFactory mViewModelFactory = Injection.provideViewModelFactory(requireContext());
		new ViewModelProvider(this, mViewModelFactory).get(DetailsViewModel.class);
	}
}