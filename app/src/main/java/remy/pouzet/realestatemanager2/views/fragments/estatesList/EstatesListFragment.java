package remy.pouzet.realestatemanager2.views.fragments.estatesList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import remy.pouzet.realestatemanager2.databinding.FragmentEstatesListBinding;
import remy.pouzet.realestatemanager2.viewmodels.HomeViewModel;

public class EstatesListFragment extends Fragment {
	
	private HomeViewModel              homeViewModel;
	private FragmentEstatesListBinding mFragmentEstatesListBinding;
	
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container,
	                         Bundle savedInstanceState) {
		mFragmentEstatesListBinding = FragmentEstatesListBinding.inflate(inflater, container, false);
		
		homeViewModel = ViewModelProviders
				.of(this)
				.get(HomeViewModel.class);
		
		return mFragmentEstatesListBinding.getRoot();
	}
}