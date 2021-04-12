package remy.pouzet.realestatemanager2.datas.services.realapi.pojos;

import java.util.List;

public class Response {
	private List<ResultsItem> results;
	private String            status;
	
	public List<ResultsItem> getResults() {
		return results;
	}
	
	public String getStatus() {
		return status;
	}
}