package remy.pouzet.realestatemanager2.viewmodels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.util.List;

import remy.pouzet.realestatemanager2.datas.database.EstateDatabase;
import remy.pouzet.realestatemanager2.datas.models.Estate;

public class SearchViewModel extends AndroidViewModel {
	
	public SearchViewModel(@NonNull Application application) {
		super(application);
	}
	
	public LiveData<List<Estate>> searchEstate(Context context, SupportSQLiteQuery query) {
		return EstateDatabase.getInstance(context).estateDao().searchEstates(query);
	}
}