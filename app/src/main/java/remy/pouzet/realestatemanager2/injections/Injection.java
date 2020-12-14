package remy.pouzet.realestatemanager2.injections;

import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import remy.pouzet.realestatemanager2.datas.database.EstateDatabase;
import remy.pouzet.realestatemanager2.repositories.EstateRepository;

/**
 * Created by Remy Pouzet on 17/11/2020.
 */
public class Injection {
	
	private Injection() {
		throw new IllegalStateException("Injection class");
	}

//    public static ViewModelsFactory provideViewModelFactory(Context context) {
//        EstateRepository dataSourceEstate = provideEstateSource(context);
//        Executor executor = provideExecutor();
//        return new ViewModelsFactory(dataSourceEstate, executor);
//    }
	
	public static EstateRepository provideEstateSource(Context context) {
		EstateDatabase database = EstateDatabase.getInstance(context);
		return new EstateRepository(database.estateDao());
	}
	
	public static Executor provideExecutor() {
		return Executors.newSingleThreadExecutor();
	}
}
