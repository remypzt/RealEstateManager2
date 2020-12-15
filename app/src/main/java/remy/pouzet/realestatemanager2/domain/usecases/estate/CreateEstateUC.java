package remy.pouzet.realestatemanager2.domain.usecases.estate;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import remy.pouzet.realestatemanager2.datas.database.EstateDatabase;
import remy.pouzet.realestatemanager2.datas.database.dao.EstateDao;
import remy.pouzet.realestatemanager2.datas.models.Estate;

/**
 * Created by Remy Pouzet on 14/12/2020.
 */
public class CreateEstateUC {
	private final EstateDao       estateDao;
	private final ExecutorService executorService;
	
	public CreateEstateUC(@NonNull Application application) {
		executorService = Executors.newSingleThreadExecutor();
		estateDao       = EstateDatabase.getInstance(application).estateDao();
	}
	
	public void execute(Context context, Estate estate) {
		executorService.execute(() -> EstateDatabase.getInstance(context)
		                                            .estateDao()
		                                            .createEstate(estate));
	}
}
