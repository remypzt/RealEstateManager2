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

import remy.pouzet.realestatemanager2.databinding.FragmentLoanSimulatorBinding;
import remy.pouzet.realestatemanager2.viewmodels.LoanSimulatorViewModel;
import remy.pouzet.realestatemanager2.views.bases.BaseFragment;

public class LoanSimulatorFragment extends BaseFragment {
    
    //------------------------------------------------------//
// ------------------    Binding    ------------------- //
//------------------------------------------------------//
    
    public FragmentLoanSimulatorBinding fragmentLoanSimulatorBinding;
    public int                          loanAmount, loanDuration, loanDurationInMonth;
    
    //------------------------------------------------------//
    // ------------------   Variables   ------------------- //
    // ------------------------------------------------------//
    
    private LoanSimulatorViewModel mLoanSimulatorViewModel;
    public  double                 loanRate;
    public  long                   amountOfLoanMensuality;
    EditText loanAmountEditText, loanRateEditText, loanDurationEditText;
    TextView loanMensualityTextView;
    
    //------------------------------------------------------//
    // ------------------   LifeCycle   ------------------- //
    //------------------------------------------------------//
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
    
    @Override
    public View provideYourFragmentView(LayoutInflater inflater,
                                        ViewGroup parent,
                                        Bundle savedInstanceState) {
        return null;
        
    }
    
    //------------------------------------------------------//
// ------------------   Functions   ------------------- //
//------------------------------------------------------//
    
    public void bindingManagement() {
        loanAmountEditText     = fragmentLoanSimulatorBinding.loanAmountEditText;
        loanDurationEditText   = fragmentLoanSimulatorBinding.loanDurationEditText;
        loanRateEditText       = fragmentLoanSimulatorBinding.loanRateEditText;
        loanMensualityTextView = fragmentLoanSimulatorBinding.loanMensualityTextView;
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
    }
    
    public void getAndSetLoanSimulatorResult() {
        if (loanAmountEditText.getText()
                              .length() > 0 && Integer.parseInt(loanAmountEditText.getText()
                                                                                  .toString()) > 0 && loanDurationEditText
                                                                                                              .getText()
                                                                                                              .length() > 0 && Integer.parseInt(
                loanDurationEditText.getText().toString()) > 0 && loanRateEditText.getText()
                                                                                  .length() > 0 && Double.parseDouble(
                loanRateEditText.getText().toString()) > 0) {
            loanAmount             = Integer.parseInt(loanAmountEditText.getText().toString());
            loanDuration           = Integer.parseInt(loanDurationEditText.getText().toString());
            loanDurationInMonth    = loanDuration * 12;
            loanRate               = Double.parseDouble(loanRateEditText.getText().toString());
            amountOfLoanMensuality = (long) (((loanAmount * loanRate) / 12) / Math.pow((1 - (1 + (loanRate / 12))),
                                                                                       -loanDurationInMonth));
            loanMensualityTextView.setText(
                    //TODO R.string
                    " " + amountOfLoanMensuality + " €");
        }
    }
    
    //------------------------------------------------------//
// ----------------- Navigation, Menu, UI ------------- //
//------------------------------------------------------//
    
    public void configureViewModel() {
        mLoanSimulatorViewModel = new ViewModelProvider(this).get(LoanSimulatorViewModel.class);
    }
}
