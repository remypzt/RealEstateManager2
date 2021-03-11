package remy.pouzet.realestatemanager2.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LoanSimulatorViewModel extends AndroidViewModel {
	
	private final MutableLiveData<String> mText;
	
	public LoanSimulatorViewModel(@NonNull Application application) {
		super(application);
		mText = new MutableLiveData<>();
		mText.setValue("This is loan simulator fragment");
	}
	
	public LiveData<String> getText() {
		return mText;
	}
}