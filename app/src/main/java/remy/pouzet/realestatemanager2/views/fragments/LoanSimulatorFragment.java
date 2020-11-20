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

import remy.pouzet.realestatemanager2.databinding.FragmentLoanSimulatorBinding;
import remy.pouzet.realestatemanager2.viewmodels.LoanSimulatorViewModel;

public class LoanSimulatorFragment extends Fragment {
	
	//------------------------------------------------------//
	// ------------------   Variables   ------------------- //
	// ------------------------------------------------------//
	
	private LoanSimulatorViewModel       mLoanSimulatorViewModel;
	private FragmentLoanSimulatorBinding mFragmentLoanSimulatorBinding;
	
	//------------------------------------------------------//
	// ------------------   LifeCycle   ------------------- //
	//------------------------------------------------------//
	
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container,
	                         Bundle savedInstanceState) {
		mFragmentLoanSimulatorBinding = FragmentLoanSimulatorBinding.inflate(inflater, container, false);
		this.configureViewModel();
		final TextView textView = mFragmentLoanSimulatorBinding.textLoanSimulator;
		mLoanSimulatorViewModel
				.getText()
				.observe(getViewLifecycleOwner(), new Observer<String>() {
					@Override
					public void onChanged(@Nullable String s) {
						textView.setText(s);
					}
				});
		
		return mFragmentLoanSimulatorBinding.getRoot();
	}
	
	//------------------------------------------------------//
// ----------------- Navigation, Menu, UI ------------- //
//------------------------------------------------------//
	
	public void configureViewModel() {
		mLoanSimulatorViewModel = ViewModelProviders
				.of(this)
				.get(LoanSimulatorViewModel.class);
	}
}
