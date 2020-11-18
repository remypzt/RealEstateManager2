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
	private int                  position;
	//------------------------------------------------------//
	// ------------------   LifeCycle   ------------------- //
	//------------------------------------------------------//
	
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container,
	                         Bundle savedInstanceState) {
		mFragmentEstatesListBinding = FragmentEstatesListBinding.inflate(inflater, container, false);
		mRecyclerView               = mFragmentEstatesListBinding.fragmentMainRecyclerView;
		
		this.configureRecyclerView(); // -  Call during UI creation
		this.configureViewModel();
		
		estatesListViewModel = ViewModelProviders
				.of(this)
				.get(EstatesListViewModel.class);
		
		return mFragmentEstatesListBinding.getRoot();
	}
//
//	@Override
//	public void onViewCreated(@NonNull View view,
//	                          @Nullable Bundle savedInstanceState) {
//		super.onViewCreated(view, savedInstanceState);
//		estatesListViewModel = new EstatesListViewModel();
//
//
//	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	//------------------------------------------------------//
	// ------------------   Functions   ------------------- //
	//------------------------------------------------------//
	
	//  - Configure RecyclerView, Adapter, LayoutManager & glue it together
	private void configureRecyclerView() {
		//  - Reset list
		this.estatesList = new ArrayList<>();
		estatesList.add(new Estate("test", "test", 1, null, 1, "test", 2, 2, "test", null, null));
		
		//  - Create adapter passing the list of users
		this.estatesListAdapter = new EstatesListAdapter(this.estatesList);
		//  - Attach the adapter to the recyclerview to populate estates
		this.mRecyclerView.setAdapter(this.estatesListAdapter);
		// - Set layout manager to position the estates
		this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
	}
	
	// 2 - Configuring ViewModel
	private void configureViewModel() {
		ViewModelsFactory mViewModelFactory = Injection.provideViewModelFactory(requireContext());
		this.estatesListViewModel = ViewModelProviders
				.of(this, mViewModelFactory)
				.get(EstatesListViewModel.class);
	}
	
	// 3 - Get all estates
	private void getAllEstates(int userId) {
//		this.estatesListViewModel.getAllEstates(userId).observe(this, updateList(List<Estate>););
	}
	
	// Get estate
	private void getEstate(int id) {
		this.estatesListViewModel
				.getEstate(id)
				.observe(this, this::updateEstate);
	}
	
	// 3 - Update an estate (selected or not)
	private void updateEstate(Estate estate) {

//		estate.setSelected(!estate.getSelected());
		
		this.estatesListViewModel.updateEstate(estate);
	}
	
	// 3 - Create a new estate
	private void createEstate() {

//		Estate estate = new Estate(this.editText.getText().toString(), this.spinner.getSelectedEstatePosition(), USER_ID);
//		this.estateViewModel.createEstate(estate);
	}
	
	// 3 - Delete an estate
	private void deleteEstate(Estate estate) {
		this.estatesListViewModel.deleteEstate(estate.getId());
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
}