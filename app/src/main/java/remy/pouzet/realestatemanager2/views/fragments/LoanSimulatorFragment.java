package remy.pouzet.realestatemanager2.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import remy.pouzet.realestatemanager2.databinding.FragmentLoanSimulatorBinding;
import remy.pouzet.realestatemanager2.viewmodels.LoanSimulatorViewModel;
import remy.pouzet.realestatemanager2.views.bases.BaseFragment;

public class LoanSimulatorFragment extends BaseFragment {

    //------------------------------------------------------//
    // ------------------   Variables   ------------------- //
    // ------------------------------------------------------//

    private LoanSimulatorViewModel mLoanSimulatorViewModel;

    //------------------------------------------------------//
    // ------------------   LifeCycle   ------------------- //
    //------------------------------------------------------//
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        remy.pouzet.realestatemanager2.databinding.FragmentLoanSimulatorBinding localFragmentLoanSimulatorBinding = FragmentLoanSimulatorBinding
                .inflate(inflater, container, false);
        this.configureViewModel();
        final TextView textView = localFragmentLoanSimulatorBinding.textLoanSimulator;
        mLoanSimulatorViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return localFragmentLoanSimulatorBinding.getRoot();
    }

    @Override
    public View provideYourFragmentView(LayoutInflater inflater,
                                        ViewGroup parent,
                                        Bundle savedInstanceState) {
        return null;
    }

    //------------------------------------------------------//
// ----------------- Navigation, Menu, UI ------------- //
//------------------------------------------------------//

    public void configureViewModel() {
        mLoanSimulatorViewModel = new ViewModelProvider(this).get(LoanSimulatorViewModel.class);
    }
}
