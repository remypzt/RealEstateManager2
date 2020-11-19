package remy.pouzet.realestatemanager2.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.Executor;

import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.repositories.EstateRepository;

public class EstatesListViewModel extends ViewModel {
	
	// REPOSITORIES
	private final EstateRepository estateDataSource;
	private final Executor         executor;
	
	public EstatesListViewModel(EstateRepository estateDataSource,
	                            Executor executor) {
		this.estateDataSource = estateDataSource;
		this.executor         = executor;
	}
	
	public LiveData<List<Estate>> getAllEstates() {
		return estateDataSource.getAllEstates();
	}
}




