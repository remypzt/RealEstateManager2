package remy.pouzet.realestatemanager2.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.datas.models.EstateRaw;
import remy.pouzet.realestatemanager2.datas.services.realapi.pojos.ResultsItem;
import remy.pouzet.realestatemanager2.domain.usecases.estate.CreateEstateUC;
import remy.pouzet.realestatemanager2.domain.usecases.estate.GetEstateUC;
import remy.pouzet.realestatemanager2.domain.usecases.estate.UpdateEstateUC;
import remy.pouzet.realestatemanager2.domain.usecases.formfragment.CheckFormDataUC;
import remy.pouzet.realestatemanager2.domain.usecases.formfragment.IsNewEstateUC;
import remy.pouzet.realestatemanager2.domain.usecases.map.GetLocationFromGeoCodingUC;

public class FormViewModel extends AndroidViewModel {
	
	private MutableLiveData<List<ResultsItem>> observeResponse;
	
	public FormViewModel(@NonNull Application application) {
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
	
	public void createEstate(EstateRaw estateRaw) {
		new CreateEstateUC().execute(this.getApplication(), estateRaw);
	}
	
	public boolean isNewEstateUC(long id) {
		return new IsNewEstateUC().execute(this.getApplication(), id);
	}
	
	public CheckFormDataUC.EstateFormState checkFormData(EstateRaw estateRaw) {
		return new CheckFormDataUC().execute(estateRaw);
	}
	
	public void updateEstate(EstateRaw estateRaw) {
		new UpdateEstateUC().execute(this.getApplication(), estateRaw);
	}
}


	
	
