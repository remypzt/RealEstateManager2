package remy.pouzet.realestatemanager2.datas.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Remy Pouzet on 21/05/2021.
 */
public class Request {
	
	// Query string
	public String       queryString = "";
	public List<Object> args        = new ArrayList();
	String cityValue, minimumPriceValue, maximumPriceValue, minimumSurfaceValue, maximumSurfaceValue, minimumRoomsNumberValue, maximumRoomsNumberValue;
	
	public Request(String cityValue,
	               String minimumPriceValue,
	               String maximumPriceValue,
	               String minimumSurfaceValue,
	               String maximumSurfaceValue,
	               String minimumRoomsNumberValue,
	               String maximumRoomsNumberValue) {
		
		this.cityValue               = cityValue;
		this.minimumPriceValue       = minimumPriceValue;
		this.maximumPriceValue       = maximumPriceValue;
		this.minimumSurfaceValue     = minimumSurfaceValue;
		this.maximumSurfaceValue     = maximumSurfaceValue;
		this.minimumRoomsNumberValue = minimumRoomsNumberValue;
		this.maximumRoomsNumberValue = maximumRoomsNumberValue;
		
		boolean containsCondition = false;

// Beginning of query string
		queryString += "SELECT * FROM Estate";

// Optional parts are added to query string and to args upon here
		
		if (!cityValue.isEmpty()) {
			queryString += " WHERE";
			queryString += " city LIKE ?";
			args.add(cityValue);
			containsCondition = true;
		}
		
		if (!minimumPriceValue.isEmpty()) {
//
			if (containsCondition) {
				queryString += " AND";
			} else {
				queryString += " WHERE";
				containsCondition = true;
			}
			
			queryString += " price > ?";
			
			args.add(Integer.parseInt(minimumPriceValue));
		}
		
		if (!maximumPriceValue.isEmpty()) {
			
			if (containsCondition) {
				queryString += " AND";
			} else {
				queryString += " WHERE";
				containsCondition = true;
			}
			
			queryString += " price < ?";
			args.add(Integer.parseInt(maximumPriceValue));
		}
		
		if (!minimumSurfaceValue.isEmpty()) {
			
			if (containsCondition) {
				queryString += " AND";
			} else {
				queryString += " WHERE";
				containsCondition = true;
			}
			queryString += " surface > ?";
			args.add(Integer.parseInt(minimumSurfaceValue));
		}
		
		if (!maximumSurfaceValue.isEmpty()) {
			
			if (containsCondition) {
				queryString += " AND";
			} else {
				queryString += " WHERE";
				containsCondition = true;
			}
			queryString += " surface < ?";
			args.add(Integer.parseInt(maximumSurfaceValue));
		}
		
		if (!minimumRoomsNumberValue.isEmpty()) {
			
			if (containsCondition) {
				queryString += " AND";
			} else {
				queryString += " WHERE";
				containsCondition = true;
			}
			
			queryString += " rooms > ?";
			args.add(Integer.parseInt(minimumRoomsNumberValue));
		}
		
		if (!maximumRoomsNumberValue.isEmpty()) {
			
			if (containsCondition) {
				queryString += " AND";
			} else {
				queryString += " WHERE";
				containsCondition = true;
			}
			queryString += " rooms < ?";
			args.add(Integer.parseInt(maximumRoomsNumberValue));
		}

// End of query string
		queryString += ";";
	}
	
}

	

