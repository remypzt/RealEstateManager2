package remy.pouzet.realestatemanager2.domain.usecases.formfragment;
import android.content.Context;

import remy.pouzet.realestatemanager2.datas.database.EstateDatabase;
import remy.pouzet.realestatemanager2.datas.models.Estate;

/**
 * Created by Remy Pouzet on 17/12/2020.
 */
public class IsNewEstateUC {
	
	public boolean execute(Context context, long id) {
		final Estate estate = EstateDatabase.getInstance(context)
		                                    .estateDao()
		                                    .getEstate(id)
		                                    .getValue();
		return estate == null;
	}
}
