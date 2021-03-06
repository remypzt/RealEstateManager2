package remy.pouzet.realestatemanager2.datas.services;
import remy.pouzet.realestatemanager2.datas.services.realapi.pojos.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Remy Pouzet on 11/04/2021.
 */
public interface GeoCodingInterfaceService {
	
	@GET("geocode/json?") Call<Response> getResponse(@Query("adress") String adress);
	
}
