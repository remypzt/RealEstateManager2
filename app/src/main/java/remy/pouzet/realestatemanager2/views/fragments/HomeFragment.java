package remy.pouzet.realestatemanager2.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import remy.pouzet.realestatemanager2.databinding.FragmentHomeBinding;
import remy.pouzet.realestatemanager2.viewmodels.HomeViewModel;

public class HomeFragment extends Fragment {
	
	private HomeViewModel       homeViewModel;
	private FragmentHomeBinding mFragmentHomeBinding;
	
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container,
	                         Bundle savedInstanceState) {
		mFragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
		
		homeViewModel = ViewModelProviders
				.of(this)
				.get(HomeViewModel.class);
		
		final TextView textView = mFragmentHomeBinding.textHome;
		homeViewModel
				.getText()
				.observe(getViewLifecycleOwner(), new Observer<String>() {
					@Override
					public void onChanged(@Nullable String s) {
						textView.setText(s);
					}
				});
		
		return mFragmentHomeBinding.getRoot();
	}
}