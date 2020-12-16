package remy.pouzet.realestatemanager2.viewmodels;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import remy.pouzet.realestatemanager2.datas.database.EstateDatabase;
import remy.pouzet.realestatemanager2.datas.database.dao.EstateDao;
import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.datas.models.EstateRaw;
import remy.pouzet.realestatemanager2.domain.usecases.estate.CreateEstateUC;
import remy.pouzet.realestatemanager2.domain.usecases.estate.GetEstateUC;
import remy.pouzet.realestatemanager2.domain.usecases.formfragment.CheckFormDataUC;
import remy.pouzet.realestatemanager2.domain.usecases.formfragment.IsItCreationOrModificationUC;

public class FormViewModel extends AndroidViewModel {
    
    private final EstateDao       estateDao;
    private final ExecutorService executorService;
    
    public FormViewModel(@NonNull Application application) {
        super(application);
        estateDao       = EstateDatabase.getInstance(application).estateDao();
        executorService = Executors.newSingleThreadExecutor();
    }
    
    public LiveData<Estate> observeEstate(long id) {
        return new GetEstateUC().execute(this.getApplication(), id);
    }
    
    public void createEstate(EstateRaw estateRaw) {
        new CreateEstateUC().execute(this.getApplication(), estateRaw);
    }
    
    public void isItCreationOrModification(Bundle bundle) {
        new IsItCreationOrModificationUC(bundle);
    }
    
    public CheckFormDataUC.EstateFormState checkFormData(EstateRaw estateRaw) {
        return new CheckFormDataUC().execute(estateRaw);
    }
}


	
	
