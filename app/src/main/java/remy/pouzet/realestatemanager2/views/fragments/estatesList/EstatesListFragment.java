package remy.pouzet.realestatemanager2.views.fragments.estatesList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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

public class EstatesListFragment extends Fragment {
	//------------------------------------------------------//
	// ------------------    Binding    ------------------- //
	//------------------------------------------------------//
	
	private FragmentEstatesListBinding mFragmentEstatesListBinding;
	
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
	
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container,
	                         Bundle savedInstanceState) {
		mFragmentEstatesListBinding = FragmentEstatesListBinding.inflate(inflater, container, false);
		mRecyclerView               = mFragmentEstatesListBinding.fragmentMainRecyclerView;
		
		this.configureViewModel();
		this.configureRecyclerView();
		getAllEstates();
		
		return mFragmentEstatesListBinding.getRoot();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
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