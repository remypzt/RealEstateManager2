package remy.pouzet.realestatemanager2.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.datas.models.EstateRaw;
import remy.pouzet.realestatemanager2.domain.usecases.estate.CreateEstateUC;
import remy.pouzet.realestatemanager2.domain.usecases.estate.GetEstateUC;
import remy.pouzet.realestatemanager2.domain.usecases.estate.UpdateEstateUC;
import remy.pouzet.realestatemanager2.domain.usecases.formfragment.CheckFormDataUC;
import remy.pouzet.realestatemanager2.domain.usecases.formfragment.IsNewEstateUC;

public class FormViewModel extends AndroidViewModel {
	

	
	public FormViewModel(@NonNull Application application) {
		super(application);
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


	
	
