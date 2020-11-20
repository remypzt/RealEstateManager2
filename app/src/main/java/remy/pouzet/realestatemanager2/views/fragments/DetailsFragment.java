package remy.pouzet.realestatemanager2.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import remy.pouzet.realestatemanager2.databinding.FragmentDetailsBinding;
import remy.pouzet.realestatemanager2.injections.Injection;
import remy.pouzet.realestatemanager2.injections.ViewModelsFactory;
import remy.pouzet.realestatemanager2.viewmodels.DetailsViewModel;

public class DetailsFragment extends Fragment {
	
	//------------------------------------------------------//
	// ------------------   Variables   ------------------- //
	// ------------------------------------------------------//
	
	private DetailsViewModel       detailsViewModel;
	private FragmentDetailsBinding mFragmentDetailsBinding;
	
	//------------------------------------------------------//
	// ------------------   LifeCycle   ------------------- //
	//------------------------------------------------------//
	
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container,
	                         Bundle savedInstanceState) {
		mFragmentDetailsBinding = remy.pouzet.realestatemanager2.databinding.FragmentDetailsBinding.inflate(inflater, container, false);
		return mFragmentDetailsBinding.getRoot();
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
//	// Get estate
//	private void getEstate(int id) {
//		this.searchViewModel
//				.getEstate(id)
//				.observe(this, th);
//	}
	
	//------------------------------------------------------//
// ----------------- Navigation, Menu, UI ------------- //
//------------------------------------------------------//
	
	private void configureViewModel() {
		ViewModelsFactory mViewModelFactory = Injection.provideViewModelFactory(requireContext());
		this.detailsViewModel = ViewModelProviders
				.of(this, mViewModelFactory)
				.get(DetailsViewModel.class);
	}
}