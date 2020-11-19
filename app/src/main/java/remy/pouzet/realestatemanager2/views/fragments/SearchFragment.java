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
import remy.pouzet.realestatemanager2.viewmodels.SearchViewModel;

public class SearchFragment extends Fragment {
	
	private SearchViewModel mViewModel;
	
	public static SearchFragment newInstance() {
		return new SearchFragment();
	}
	
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_search, container, false);
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mViewModel = ViewModelProviders
				.of(this)
				.get(SearchViewModel.class);
		// TODO: Use the ViewModel
	}
	
	// Get estate
	private void getEstate(int id) {
//		this.estatesListViewModel
//				.getEstate(id)
//				.observe(this, this::);
	}
	
}