package remy.pouzet.realestatemanager2.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import remy.pouzet.realestatemanager2.R;
import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.injections.Injection;
import remy.pouzet.realestatemanager2.injections.ViewModelsFactory;
import remy.pouzet.realestatemanager2.viewmodels.FormViewModel;

public class FormFragment extends Fragment {
	
	//------------------------------------------------------//
// ------------------   Variables   ------------------- //
//------------------------------------------------------//
	
	private FormViewModel formViewModel;
	
	//------------------------------------------------------//
// ------------------   LifeCycle   ------------------- //
//------------------------------------------------------//
	
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_form, container, false);
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.configureViewModel();
	}
	
	private void configureViewModel() {
		ViewModelsFactory mViewModelFactory = Injection.provideViewModelFactory(requireContext());
		this.formViewModel = ViewModelProviders
				.of(this, mViewModelFactory)
				.get(FormViewModel.class);
	}
	
	//------------------------------------------------------//
// ------------------   Functions   ------------------- //
//------------------------------------------------------//
	private void updateEstate(Estate estate) {
		//TODO
//		estate.setSelected(!estate.getSelected());
		this.formViewModel.updateEstate(estate);
	}
	
	private void createEstate() {
//		Estate estate = new Estate(
//				//TODO get edit info
//		);
//		this.formViewModel.createEstate(estate);
	}
	
	private void deleteEstate(Estate estate) {
		this.formViewModel.deleteEstate(estate.getId());
	}
	
}