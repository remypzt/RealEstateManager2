package remy.pouzet.realestatemanager2.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoanSimulatorViewModel extends ViewModel {
	
	private final MutableLiveData<String> mText;
	
	public LoanSimulatorViewModel() {
		mText = new MutableLiveData<>();
		mText.setValue("This is loan simulator fragment");
	}
	
	public LiveData<String> getText() {
		return mText;
	}
}