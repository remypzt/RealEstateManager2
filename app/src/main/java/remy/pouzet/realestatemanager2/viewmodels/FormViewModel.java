package remy.pouzet.realestatemanager2.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import remy.pouzet.realestatemanager2.datas.database.EstateDatabase;
import remy.pouzet.realestatemanager2.datas.database.dao.EstateDao;
import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.domain.usecases.estate.CreateEstateUseCase;
import remy.pouzet.realestatemanager2.domain.usecases.estate.GetEstateUseCase;

public class FormViewModel extends AndroidViewModel {
    
    // REPOSITORIES
//    private final EstateRepository estateDataSource;
//    private final Executor         executor;
    private final EstateDao       estateDao;
    private final ExecutorService executorService;
    
    public FormViewModel(@NonNull Application application
//                        ,EstateRepository estateDataSource, Executor executor
                        ) {
        super(application);
        estateDao       = EstateDatabase.getInstance(application).estateDao();
        executorService = Executors.newSingleThreadExecutor();
//           this.estateDataSource = estateDataSource;
//        this.executor         = executor;
    }
    
    public LiveData<Estate> observeEstate(long id) {
        return new GetEstateUseCase().execute(this.getApplication(), id);
    }
    
    public void createEstate(Estate estate) {
        executorService.execute(() -> {
            new CreateEstateUseCase().execute(this.getApplication(), estate);
//            (new CreateEstateUseCase().execute(this.getApplication(), estate));
        });
    }

//    public void createEstate(Estate estate) {
//        executor.execute(() -> estateDataSource.createEstate(estate));
//    }
//
//    public void deleteEstate(long estateId) {
//        executor.execute(() -> estateDataSource.deleteEs-tate(estateId));
//    }
//
//    public void updateEstate(Estate estate) {
//        executor.execute(() -> estateDataSource.updateEstate(estate));
//    }
}
	
	
