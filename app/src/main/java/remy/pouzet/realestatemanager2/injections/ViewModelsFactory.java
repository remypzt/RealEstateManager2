package remy.pouzet.realestatemanager2.injections;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.concurrent.Executor;

import remy.pouzet.realestatemanager2.repositories.EstateRepository;
import remy.pouzet.realestatemanager2.viewmodels.DetailsViewModel;
import remy.pouzet.realestatemanager2.viewmodels.EstatesListViewModel;
import remy.pouzet.realestatemanager2.viewmodels.FormViewModel;
import remy.pouzet.realestatemanager2.viewmodels.SearchViewModel;

/**
 * Created by Remy Pouzet on 17/11/2020.
 */
public class ViewModelsFactory implements ViewModelProvider.Factory {
	
	private final EstateRepository estateDataSource;
	private final Executor         executor;
	
	public ViewModelsFactory(EstateRepository estateDataSource, Executor executor) {
		this.estateDataSource = estateDataSource;
		this.executor         = executor;
	}
	
	@Override public <T extends ViewModel> T create(Class<T> modelClass) {
		if (modelClass.isAssignableFrom(EstatesListViewModel.class)) {
			return (T) new EstatesListViewModel(estateDataSource, executor);
		}
		if (modelClass.isAssignableFrom(FormViewModel.class)) {
			return (T) new FormViewModel(estateDataSource, executor);
		}
		if (modelClass.isAssignableFrom(SearchViewModel.class)) {
			return (T) new SearchViewModel(estateDataSource, executor);
		}
		if (modelClass.isAssignableFrom(DetailsViewModel.class)) {
			return (T) new DetailsViewModel(estateDataSource, executor);
		}
		throw new IllegalArgumentException("Unknown ViewModel class");
	}
}