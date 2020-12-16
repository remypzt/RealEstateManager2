package remy.pouzet.realestatemanager2.domain.usecases.estate;
import android.content.Context;

import remy.pouzet.realestatemanager2.datas.database.EstateDatabase;
import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.datas.models.EstateRaw;

/**
 * Created by Remy Pouzet on 14/12/2020.
 */
public class CreateEstateUC {
	
	public void execute(Context context, EstateRaw estateRaw) {
		final Estate estate = new TransformEstateRawToEstateUC().execute(estateRaw);
		EstateDatabase.getInstance(context).estateDao().createEstate(estate);
	}
}
