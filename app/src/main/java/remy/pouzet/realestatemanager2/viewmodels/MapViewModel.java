package remy.pouzet.realestatemanager2.viewmodels;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.datas.services.realapi.pojos.ResultsItem;
import remy.pouzet.realestatemanager2.domain.usecases.estate.GetAllEstatesUC;
import remy.pouzet.realestatemanager2.domain.usecases.estate.GetEstateUC;
import remy.pouzet.realestatemanager2.domain.usecases.map.GetLocationFromGeoCodingUC;

/**
 * Created by Remy Pouzet on 15/04/2021.
 */
public class MapViewModel extends AndroidViewModel {
	
	private MutableLiveData<List<ResultsItem>> observeResponse;
	
	public MapViewModel(@NonNull Application application) {
		super(application);
	}
	
	public MutableLiveData<List<ResultsItem>> observeResponse(String address) {
		if (observeResponse == null) {
			observeResponse = (MutableLiveData<List<ResultsItem>>) new GetLocationFromGeoCodingUC().execute(
					this.getApplication(),
					address);
		}
		return observeResponse;
	}
	
	public LiveData<Estate> observeEstate(long id) {
		return new GetEstateUC().execute(this.getApplication(), id);
	}
	
	public LiveData<List<Estate>> observeAllEstates() {
		return new GetAllEstatesUC().execute(this.getApplication());
	}
	
}