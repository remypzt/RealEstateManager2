package remy.pouzet.realestatemanager2.views.fragments.estateslist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import remy.pouzet.realestatemanager2.databinding.FragmentEstatesListBinding;
import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.injections.Injection;
import remy.pouzet.realestatemanager2.injections.ViewModelsFactory;
import remy.pouzet.realestatemanager2.viewmodels.EstatesListViewModel;
import remy.pouzet.realestatemanager2.views.bases.BaseFragment;

//------------------------------------------------------//
// ------------------    Binding    ------------------- //
//------------------------------------------------------//
// ------------------   Variables   ------------------- //
//------------------------------------------------------//
// ------------------   LifeCycle   ------------------- //
//------------------------------------------------------//
// ------------------   Functions   ------------------- //
//------------------------------------------------------//
// ------------------     Intent    ------------------- //
//------------------------------------------------------//
// ------------------   Callbacks   ------------------- //
//------------------------------------------------------//
// ------------------     Data      ------------------- //
//------------------------------------------------------//
// ------------------    Adapter    ------------------- //
//------------------------------------------------------//
// ------------------ Miscellaneous ------------------- //
//------------------------------------------------------//
// ----------------- Navigation, Menu, UI ------------- //
//------------------------------------------------------//

public class EstatesListFragment extends BaseFragment {
	//------------------------------------------------------//
	// ------------------    Binding    ------------------- //
	//------------------------------------------------------//
	
	//------------------------------------------------------//
	// ------------------   Variables   ------------------- //
	// ------------------------------------------------------//
	
	RecyclerView mRecyclerView;
	private EstatesListViewModel estatesListViewModel;
	private EstatesListAdapter   estatesListAdapter;
	private List<Estate>         estatesList;
	//------------------------------------------------------//
	// ------------------   LifeCycle   ------------------- //
	//------------------------------------------------------//
	
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container,
	                         Bundle savedInstanceState) {
		remy.pouzet.realestatemanager2.databinding.FragmentEstatesListBinding localFragmentEstatesListBinding = FragmentEstatesListBinding.inflate(inflater, container, false);
		mRecyclerView = localFragmentEstatesListBinding.fragmentMainRecyclerView;
		this.configureViewModel();
		this.configureRecyclerView();
		getAllEstates();
		
		return localFragmentEstatesListBinding.getRoot();
	}
	
	@Override
	public View provideYourFragmentView(LayoutInflater inflater,
	                                    ViewGroup parent,
	                                    Bundle savedInstanceState) {
		return null;
	}
	
	
	//------------------------------------------------------//
	// ------------------   Functions   ------------------- //
	//------------------------------------------------------//
	private void getAllEstates() {
		this.estatesListViewModel
				.getAllEstates()
				.observe(getViewLifecycleOwner(), this::updateList);
	}
	
	// UPDATE UI
	//updateListOfArticles still in Fragments cause I must call the adapter and I cannot do it in viewmodel
	public void updateList(List<Estate> estatesList) {
		this.estatesList.clear();
		if (estatesList != null) {
			this.estatesList.addAll(estatesList);
			estatesListAdapter.notifyDataSetChanged();
		}
	}
//------------------------------------------------------//
// ----------------- Navigation, Menu, UI ------------- //
//------------------------------------------------------//
	
	//  - Configure RecyclerView, Adapter, LayoutManager & glue it together
	private void configureRecyclerView() {
		//  - Reset list
		this.estatesList = new ArrayList<>();
		//  - Create adapter passing the list of users
		this.estatesListAdapter = new EstatesListAdapter(this.estatesList);
		//  - Attach the adapter to the recyclerview to populate estates
		this.mRecyclerView.setAdapter(this.estatesListAdapter);
		// - Set layout manager to position the estates
		this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
	}
	
	private void configureViewModel() {
		ViewModelsFactory mViewModelFactory = Injection.provideViewModelFactory(requireContext());
		this.estatesListViewModel = ViewModelProviders
				.of(this, mViewModelFactory)
				.get(EstatesListViewModel.class);
	}
	
}