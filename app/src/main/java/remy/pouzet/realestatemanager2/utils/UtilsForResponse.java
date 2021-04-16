package remy.pouzet.realestatemanager2.utils;
import java.util.List;

import remy.pouzet.realestatemanager2.datas.services.realapi.pojos.Response;
import remy.pouzet.realestatemanager2.datas.services.realapi.pojos.ResultsItem;

/**
 * Created by Remy Pouzet on 11/04/2021.
 */
public class UtilsForResponse {
	
	public static List<ResultsItem> generateResults(Response response) {
		
		if (response != null) {
			List<ResultsItem> resultsFromResponse = response.getResults();
			return resultsFromResponse;
//			for (int x = 0;
//			     x <= resultsFromResponse.size() - 1;
//			     x++) {
//				resultsFromResponse.add(addResultsFromResponses(resultsFromResponse.get(x)));
//			}
		}
		return null;
	}

//	public static Response addResultsFromResponses(ResultsItem resultsItem){
//		return new ResultsItem();
//
//	}
}
	
	

