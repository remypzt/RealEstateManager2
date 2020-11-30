package remy.pouzet.realestatemanager2.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.Executor;

import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.repositories.EstateRepository;

public class FormViewModel extends ViewModel {
	
	// REPOSITORIES
	private final EstateRepository estateDataSource;
	private final Executor         executor;
	
	public FormViewModel(EstateRepository estateDataSource,
	                     Executor executor) {
		this.estateDataSource = estateDataSource;
		this.executor         = executor;
	}
	
	public LiveData<Estate> getEstate(long id) {
		return estateDataSource.getEstate(id);
	}
	
	public void createEstate(Estate estate) {
		executor.execute(() -> estateDataSource.createEstate(estate));
	}
	
	public void deleteEstate(long estateId) {
		executor.execute(() -> estateDataSource.deleteEstate(estateId));
	}
	
	public void updateEstate(Estate estate) {
		executor.execute(() -> estateDataSource.updateEstate(estate));
	}
}
	
	
