package remy.pouzet.realestatemanager2.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EstatesListViewModel extends ViewModel {
	
	private final MutableLiveData<String> mText;
	
	public EstatesListViewModel() {
		mText = new MutableLiveData<>();
		mText.setValue("This is home fragment");
	}
	
	public LiveData<String> getText() {
		return mText;
	}
}