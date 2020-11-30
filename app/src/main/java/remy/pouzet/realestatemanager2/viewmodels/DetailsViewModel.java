package remy.pouzet.realestatemanager2.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.Executor;

import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.repositories.EstateRepository;

public class DetailsViewModel extends ViewModel {
	
	// REPOSITORIES
	private final EstateRepository estateDataSource;
	private final Executor         executor;
	
	public DetailsViewModel(EstateRepository estateDataSource, Executor executor) {
		this.estateDataSource = estateDataSource;
		this.executor         = executor;
	}
	
	public LiveData<Estate> getEstate(long id) {
		return estateDataSource.getEstate(id);
	}
}