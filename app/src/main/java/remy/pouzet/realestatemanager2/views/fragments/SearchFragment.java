package remy.pouzet.realestatemanager2.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import remy.pouzet.realestatemanager2.R;
import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.injections.Injection;
import remy.pouzet.realestatemanager2.injections.ViewModelsFactory;
import remy.pouzet.realestatemanager2.viewmodels.SearchViewModel;
import remy.pouzet.realestatemanager2.views.bases.BaseFragment;
import remy.pouzet.realestatemanager2.views.fragments.estateslist.EstatesListAdapter;

public class SearchFragment extends BaseFragment {
	
	//------------------------------------------------------//
	// ------------------   Variables   ------------------- //
	// ------------------------------------------------------//
	
	private SearchViewModel    searchViewModel;
	private List<Estate>       estatesList;
	private EstatesListAdapter estatesListAdapter;
	
	//------------------------------------------------------//
	// ------------------   LifeCycle   ------------------- //
	//------------------------------------------------------//
	
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_search, container, false);
	}
	
	@Override
	public View provideYourFragmentView(LayoutInflater inflater,
	                                    ViewGroup parent,
	                                    Bundle savedInstanceState) {
		return null;
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.configureViewModel();
		
	}
	//------------------------------------------------------//
	// ------------------   Functions   ------------------- //
	//------------------------------------------------------//
//	private void getAllEstates() {
//		this.searchViewModel
//				.getAllEstates()
//				.observe(getViewLifecycleOwner(), this::updateList);
//	}

//	// Get estate
//	private void getEstate(int id) {
//		this.searchViewModel
//				.getEstate(id)
//				.observe(this, th);
//	}
	
	// UPDATE UI
//	//updateListOfArticles still in Fragments cause I must call the adapter and I cannot do it in viewmodel
//	public void updateList(List<Estate> estatesList) {
//		this.estatesList.clear();
//		if (estatesList != null) {
//			this.estatesList.addAll(estatesList);
//			estatesListAdapter.notifyDataSetChanged();
//		}
//	}
	
	//------------------------------------------------------//
// ----------------- Navigation, Menu, UI ------------- //
//------------------------------------------------------//
	
	private void configureViewModel() {
		ViewModelsFactory mViewModelFactory = Injection.provideViewModelFactory(requireContext());
		this.searchViewModel = ViewModelProviders
				.of(this, mViewModelFactory)
				.get(SearchViewModel.class);
	}
	
}