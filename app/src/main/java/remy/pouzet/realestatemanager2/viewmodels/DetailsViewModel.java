package remy.pouzet.realestatemanager2.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DetailsViewModel extends ViewModel {
	
	private MutableLiveData<String> mText;
	
	public DetailsViewModel() {
		mText = new MutableLiveData<>();
		mText.setValue("This is gallery fragment");
	}
	
	public LiveData<String> getText() {
		return mText;
	}
}