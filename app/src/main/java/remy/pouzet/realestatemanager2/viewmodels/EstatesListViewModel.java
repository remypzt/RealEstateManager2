package remy.pouzet.realestatemanager2.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.domain.usecases.estate.GetAllEstatesUseCase;

public class EstatesListViewModel extends AndroidViewModel {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private MutableLiveData<LiveData<List<Estate>>> estateLiveData;

    ///////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    ///////////////////////////////////////////////////////////////////////////

    public EstatesListViewModel(@NonNull Application application) {
        super(application);
    }

    ///////////////////////////////////////////////////////////////////////////
    // METHODS
    ///////////////////////////////////////////////////////////////////////////

    public MutableLiveData<LiveData<List<Estate>>> getAllEstates() {
        if (estateLiveData == null) {
            estateLiveData = new MutableLiveData<>();
            estateLiveData.postValue(new GetAllEstatesUseCase().execute(this.getApplication()));
        }
        return estateLiveData;
    }
}


//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.ViewModel;
//
//import java.util.List;
//import java.util.concurrent.Executor;
//
//import remy.pouzet.realestatemanager2.datas.models.Estate;
//import remy.pouzet.realestatemanager2.repositories.EstateRepository;
//
//public class EstatesListViewModel extends ViewModel {
//
//	// REPOSITORIES
//	private final EstateRepository estateDataSource;
//	private final Executor         executor;
//
//	public EstatesListViewModel(EstateRepository estateDataSource, Executor executor) {
//		this.estateDataSource = estateDataSource;
//		this.executor         = executor;
//	}
//
//	public LiveData<List<Estate>> getAllEstates() {
//		return estateDataSource.getAllEstates();
//	}
//}




