package remy.pouzet.realestatemanager2.domain.usecase.estate;

import android.content.Context;

import java.util.List;

import remy.pouzet.realestatemanager2.datas.database.EstateDatabase;
import remy.pouzet.realestatemanager2.datas.models.Estate;

public class GetAllEstatesUseCase {

    public List<Estate> execute(Context context) {
        return EstateDatabase.getInstance(context)
                .mEstateDao()
                .getAllEstates();
    }
}
