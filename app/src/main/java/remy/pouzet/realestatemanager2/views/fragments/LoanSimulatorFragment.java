package remy.pouzet.realestatemanager2.views.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import java.text.DecimalFormat;

import remy.pouzet.realestatemanager2.R;
import remy.pouzet.realestatemanager2.databinding.FragmentLoanSimulatorBinding;
import remy.pouzet.realestatemanager2.viewmodels.LoanSimulatorViewModel;
import remy.pouzet.realestatemanager2.views.bases.BaseFragment;

public class LoanSimulatorFragment extends BaseFragment {
	///////////////////////////////////////////////////////////////////////////
	// VARIABLES
	///////////////////////////////////////////////////////////////////////////
	public double loanRate;
	public double amountOfLoanMensuality;
	public double contributionAmount, loanAmount, loanDuration, loanDurationInMonth;
	private              LoanSimulatorViewModel mLoanSimulatorViewModel;
	private static final DecimalFormat          df = new DecimalFormat("0.00");
	
	///////////////////////////////////////////////////////////////////////////
	// BINDING
	///////////////////////////////////////////////////////////////////////////
	public FragmentLoanSimulatorBinding fragmentLoanSimulatorBinding;
	public EditText                     loanAmountEditText, loanRateEditText, loanDurationEditText, contributionAmountEditText;
	public TextView loanMensualityTextView;
	
	public void bindingManagement() {
		loanAmountEditText         = fragmentLoanSimulatorBinding.loanAmountEditText;
		loanDurationEditText       = fragmentLoanSimulatorBinding.loanDurationEditText;
		loanRateEditText           = fragmentLoanSimulatorBinding.loanRateEditText;
		loanMensualityTextView     = fragmentLoanSimulatorBinding.loanMensualityTextView;
		contributionAmountEditText = fragmentLoanSimulatorBinding.contributionAmountEditText;
	}
	
	///////////////////////////////////////////////////////////////////////////
	// LIFECYCLE
	///////////////////////////////////////////////////////////////////////////
	@Override public View onCreateView(@NonNull LayoutInflater inflater,
	                                   ViewGroup container,
	                                   Bundle savedInstanceState) {
		
		fragmentLoanSimulatorBinding = FragmentLoanSimulatorBinding.inflate(inflater,
		                                                                    container,
		                                                                    false);
		bindingManagement();
		manageLoanSimulaor();
		this.configureViewModel();
		
		return fragmentLoanSimulatorBinding.getRoot();
	}
	
	///////////////////////////////////////////////////////////////////////////
	// CONFIGURATIONS
	///////////////////////////////////////////////////////////////////////////
	@Override public View provideYourFragmentView(LayoutInflater inflater,
	                                              ViewGroup parent,
	                                              Bundle savedInstanceState) {
		return null;
		
	}
	
	public void configureViewModel() {
		mLoanSimulatorViewModel = new ViewModelProvider(this).get(LoanSimulatorViewModel.class);
	}
	
	///////////////////////////////////////////////////////////////////////////
	// FUNCTIONS
	///////////////////////////////////////////////////////////////////////////
	
	public void getAndSetLoanSimulatorResult() {
		if (loanAmountEditText.getText()
		                      .length() > 0 && Double.parseDouble(loanAmountEditText.getText()
		                                                                            .toString()) > 0 && contributionAmountEditText
				                                                                                                .getText()
				                                                                                                .length() > 0 && Double.parseDouble(
				contributionAmountEditText.getText()
				                          .toString()) > 0 && loanDurationEditText.getText()
		                                                                          .length() > 0 && Double.parseDouble(
				loanDurationEditText.getText().toString()) > 0 && loanRateEditText.getText()
		                                                                          .length() > 0 && Double.parseDouble(
				loanRateEditText.getText().toString()) > 0) {
			
			contributionAmount     = Double.parseDouble(contributionAmountEditText.getText()
			                                                                      .toString());
			loanAmount             = Double.parseDouble(loanAmountEditText.getText()
			                                                              .toString()) - contributionAmount;
			loanDuration           = Double.parseDouble(loanDurationEditText.getText().toString());
			loanDurationInMonth    = loanDuration * 12;
			loanRate               = Double.parseDouble(loanRateEditText.getText().toString());
			amountOfLoanMensuality = ((loanAmount * loanRate / 100) / 12) / (1 - (Math.pow((1 + (loanRate / 100 / 12)),
			                                                                               -loanDurationInMonth)));
			df.format(amountOfLoanMensuality);
			loanMensualityTextView.setText(getResources().getText(R.string.your_ammount_mensuality_will_be) + " " + df
					.format(amountOfLoanMensuality) + " €");
		} else {
			showIndefiniteSnackBar(requireView(), "remplir tous les champs");
		}
	}
	
	public void manageLoanSimulaor() {
		loanAmountEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			
			@Override public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			public void afterTextChanged(Editable s) {
				getAndSetLoanSimulatorResult();
			}
			
		});
		
		loanDurationEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			
			@Override public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			public void afterTextChanged(Editable s) {
				getAndSetLoanSimulatorResult();
			}
			
		});
		
		loanRateEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			
			@Override public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			public void afterTextChanged(Editable s) {
				getAndSetLoanSimulatorResult();
			}
		});
		
		contributionAmountEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			
			@Override public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			public void afterTextChanged(Editable s) {
				getAndSetLoanSimulatorResult();
			}
		});
	}
	
}
