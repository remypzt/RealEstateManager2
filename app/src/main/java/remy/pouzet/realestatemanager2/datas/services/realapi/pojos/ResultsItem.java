package remy.pouzet.realestatemanager2.datas.services.realapi.pojos;

import java.util.List;

public class ResultsItem {
	private String                      formattedAddress;
	private List<String>                types;
	private Geometry                    geometry;
	private List<AddressComponentsItem> addressComponents;
	private PlusCode                    plusCode;
	private String                      placeId;
	
	public String getFormattedAddress() {
		return formattedAddress;
	}
	
	public List<String> getTypes() {
		return types;
	}
	
	public Geometry getGeometry() {
		return geometry;
	}
	
	public List<AddressComponentsItem> getAddressComponents() {
		return addressComponents;
	}
	
	public PlusCode getPlusCode() {
		return plusCode;
	}
	
	public String getPlaceId() {
		return placeId;
	}
}