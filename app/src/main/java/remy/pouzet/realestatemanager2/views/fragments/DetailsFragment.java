package remy.pouzet.realestatemanager2.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import remy.pouzet.realestatemanager2.viewmodels.DetailsViewModel;

public class DetailsFragment extends Fragment {
	
	private DetailsViewModel                                                  mDetailsViewModel;
	private remy.pouzet.realestatemanager2.databinding.FragmentDetailsBinding mFragmentDetailsBinding;
	
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container,
	                         Bundle savedInstanceState) {
		mFragmentDetailsBinding = remy.pouzet.realestatemanager2.databinding.FragmentDetailsBinding.inflate(inflater, container, false);
		mDetailsViewModel       = ViewModelProviders
				.of(this)
				.get(DetailsViewModel.class);
		
		return mFragmentDetailsBinding.getRoot();
	}
}