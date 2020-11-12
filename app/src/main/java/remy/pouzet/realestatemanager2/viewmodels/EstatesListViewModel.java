package remy.pouzet.realestatemanager2.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import remy.pouzet.realestatemanager2.datas.models.Estate;

public class EstatesListViewModel extends ViewModel {
	
	private final MutableLiveData<List<Estate>> mEstateList;
	
	public EstatesListViewModel() {
		mEstateList = new MutableLiveData<>();
		
	}
	
	public LiveData<List<Estate>> getEstate() {
		
		return mEstateList;
	}
}