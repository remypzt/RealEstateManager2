package remy.pouzet.realestatemanager2.domain.usecases.estate;
import android.content.Context;

import remy.pouzet.realestatemanager2.datas.database.EstateDatabase;
import remy.pouzet.realestatemanager2.datas.models.Estate;

/**
 * Created by Remy Pouzet on 14/12/2020.
 */
public class CreateEstateUseCase {
	
	public void execute(Context context, Estate estate) {
		
		EstateDatabase.getInstance(context).estateDao().createEstate(estate);
		
	}
}
