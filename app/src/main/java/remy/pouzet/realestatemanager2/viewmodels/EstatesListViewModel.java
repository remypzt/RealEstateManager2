package remy.pouzet.realestatemanager2.viewmodels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.sqlite.db.SimpleSQLiteQuery;

import java.util.List;

import remy.pouzet.realestatemanager2.datas.database.EstateDatabase;
import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.datas.models.Request;
import remy.pouzet.realestatemanager2.domain.usecases.estate.GetAllEstatesUC;

public class EstatesListViewModel extends AndroidViewModel {
	
	///////////////////////////////////////////////////////////////////////////
	// CONSTRUCTOR
	///////////////////////////////////////////////////////////////////////////
	
	public EstatesListViewModel(@NonNull Application application) {
		super(application);
	}
	
	///////////////////////////////////////////////////////////////////////////
	// METHODS
	///////////////////////////////////////////////////////////////////////////
	
	public LiveData<List<Estate>> observeAllEstates() {
		return new GetAllEstatesUC().execute(this.getApplication());
	}
	
	public LiveData<List<Estate>> searchEstate(Context context, Request query) {
		SimpleSQLiteQuery simpleSQLiteQuery = new SimpleSQLiteQuery(query.queryString,
		                                                            query.args.toArray());
		return EstateDatabase.getInstance(context).estateDao().searchEstates(simpleSQLiteQuery);
	}
	
}



