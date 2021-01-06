package remy.pouzet.realestatemanager2.domain.usecases.estate;
import android.content.Context;

import androidx.lifecycle.LiveData;

import remy.pouzet.realestatemanager2.datas.database.EstateDatabase;
import remy.pouzet.realestatemanager2.datas.models.Estate;

/**
 * Created by Remy Pouzet on 14/12/2020.
 */
public class GetEstateUC {
	
	public LiveData<Estate> execute(Context context, long id) {
		return EstateDatabase.getInstance(context).estateDao().getEstate(id);
	}
}
