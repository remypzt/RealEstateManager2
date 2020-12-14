package remy.pouzet.realestatemanager2.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.domain.usecases.estate.GetEstateUseCase;

public class DetailsViewModel extends AndroidViewModel {
    
    public DetailsViewModel(@NonNull Application application) {
        super(application);
    }
    
    public LiveData<Estate> observeEstate(long id) {
        return new GetEstateUseCase().execute(this.getApplication(), id);
    }
}