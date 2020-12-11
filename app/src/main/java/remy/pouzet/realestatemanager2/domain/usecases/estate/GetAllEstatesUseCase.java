package remy.pouzet.realestatemanager2.domain.usecases.estate;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import remy.pouzet.realestatemanager2.datas.database.EstateDatabase;
import remy.pouzet.realestatemanager2.datas.models.Estate;

public class GetAllEstatesUseCase {
	
	public LiveData<List<Estate>> execute(Context context) {
		return EstateDatabase.getInstance(context).estateDao().getAllEstates();
	}
}