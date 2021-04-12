package remy.pouzet.realestatemanager2.datas.services.realapi.pojos;

import java.util.List;

public class AddressComponentsItem {
	private List<String> types;
	private String       shortName;
	private String       longName;
	
	public List<String> getTypes() {
		return types;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	public String getLongName() {
		return longName;
	}
}