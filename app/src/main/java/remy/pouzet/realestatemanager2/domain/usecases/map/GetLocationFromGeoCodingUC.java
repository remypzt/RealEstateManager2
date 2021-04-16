package remy.pouzet.realestatemanager2.domain.usecases.map;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import remy.pouzet.realestatemanager2.datas.services.GeoCodingInterfaceService;
import remy.pouzet.realestatemanager2.datas.services.RetrofitService;
import remy.pouzet.realestatemanager2.datas.services.realapi.pojos.ResultsItem;
import remy.pouzet.realestatemanager2.utils.UtilsForResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Remy Pouzet on 26/03/2021.
 */
public class GetLocationFromGeoCodingUC {
	
	public final  GeoCodingInterfaceService          geoCodingInterfaceService;
	private final MutableLiveData<List<ResultsItem>> geoCodingResponse;
	
	public GetLocationFromGeoCodingUC() {
		this.geoCodingInterfaceService = RetrofitService.cteateService(GeoCodingInterfaceService.class);
		geoCodingResponse              = new MutableLiveData<>();
		
	}
	
	public LiveData<List<ResultsItem>> execute(Context context, String address) {
		geoCodingInterfaceService.getResponse((address))
		                         .enqueue(new Callback<remy.pouzet.realestatemanager2.datas.services.realapi.pojos.Response>() {
			                         @Override
			                         public void onResponse(Call<remy.pouzet.realestatemanager2.datas.services.realapi.pojos.Response> call,
			                                                Response<remy.pouzet.realestatemanager2.datas.services.realapi.pojos.Response> response) {
				                         if (response.isSuccessful()) {
					                         geoCodingResponse.setValue(UtilsForResponse.generateResults(
							                         response.body()));
					
				                         } else {
					                         geoCodingResponse.setValue(null);
				                         }
			                         }
			
			                         @Override
			                         public void onFailure(Call<remy.pouzet.realestatemanager2.datas.services.realapi.pojos.Response> call,
			                                               Throwable t) {
				                         geoCodingResponse.setValue(null);
			                         }
		                         });
		return geoCodingResponse;
	}
	
}
