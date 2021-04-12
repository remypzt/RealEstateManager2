package remy.pouzet.realestatemanager2.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.datas.services.realapi.pojos.Response;
import remy.pouzet.realestatemanager2.datas.services.realapi.pojos.ResultsItem;
import remy.pouzet.realestatemanager2.domain.usecases.estate.GetEstateUC;
import remy.pouzet.realestatemanager2.domain.usecases.map.GetLocationFromGeoCodingUC;

public class DetailsViewModel extends AndroidViewModel {
    
    private MutableLiveData<List<Response>> response;
    
    public DetailsViewModel(@NonNull Application application) {
        super(application);
    }
    
    public LiveData<Estate> observeEstate(long id) {
        return new GetEstateUC().execute(this.getApplication(), id);
    }
    
    public LiveData<List<ResultsItem>> observeResponse(String address) {
        return new GetLocationFromGeoCodingUC().execute(this.getApplication(), address);
    }
    
}