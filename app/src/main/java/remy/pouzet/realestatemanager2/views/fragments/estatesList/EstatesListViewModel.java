package remy.pouzet.realestatemanager2.views.fragments.estatesList;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.domain.usecase.estate.GetAllEstatesUseCase;

public class EstatesListViewModel extends AndroidViewModel {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private MutableLiveData<List<Estate>> estateLiveData = new MutableLiveData<>();

    ///////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    ///////////////////////////////////////////////////////////////////////////

    public EstatesListViewModel(@NonNull Application application) {
        super(application);
    }

    ///////////////////////////////////////////////////////////////////////////
    // METHODS
    ///////////////////////////////////////////////////////////////////////////

    public void getAllEstates() {
        estateLiveData.postValue(
                new GetAllEstatesUseCase().execute(this.getApplication())
        );
    }

    public LiveData<List<Estate>> observeAllEstates() {
        return estateLiveData;
    }
}




