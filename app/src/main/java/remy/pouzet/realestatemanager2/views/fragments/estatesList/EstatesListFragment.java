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
		
		estatesList = new ArrayList<>();
		estatesList.add(new Estate("test", "test", 1, null, 1, "test", 2, 2, "test", null, null));
		
		this.configureRecyclerView(); // -  Call during UI creation
		
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
		//  - Create adapter passing the list of users
		this.estatesListAdapter = new EstatesListAdapter(this.estatesList);
		//  - Attach the adapter to the recyclerview to populate items
		this.mRecyclerView.setAdapter(this.estatesListAdapter);
		// - Set layout manager to position the items
		this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
	}
	
	// UPDATE UI
	//updateListOfArticles still in Fragments cause I must call the adapter and I cannot do it in viewmodel
	
	public void updateList(List<Estate> estatesList) {
		estatesList.clear();
		if (estatesList != null) {
			estatesList.addAll(estatesList);
			estatesListAdapter.notifyDataSetChanged();
		}
	}
	
}