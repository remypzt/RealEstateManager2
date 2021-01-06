package remy.pouzet.realestatemanager2.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.domain.usecases.estate.GetEstateUC;

public class SearchViewModel extends AndroidViewModel {
    
    public SearchViewModel(@NonNull Application application) {
        super(application);
    }
    
    public LiveData<Estate> observeEstate(long id) {
        return new GetEstateUC().execute(this.getApplication(), id);
    }
}