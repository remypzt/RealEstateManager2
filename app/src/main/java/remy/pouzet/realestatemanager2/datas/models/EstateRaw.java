package remy.pouzet.realestatemanager2.datas.models;
/**
 * Created by Remy Pouzet on 16/12/2020.
 */
public class EstateRaw {
	
	private Boolean isSellStatus;
	private String  typeValue;
	private String  cityValue;
	private String  priceValue;
	private String  mainPictureValue;
	private int     id;
	private String  descriptionValue;
	private String  surfaceValue;
	private String  roomsValue;
	private String  adressValue;
	private String  contactValue;
	private String  updateDate;
	private String  sellDate;
	private Double  lat;
	private Double  lng;
	
	public EstateRaw(Boolean isSellStatus,
	                 String typeValue,
	                 String cityValue,
	                 String priceValue,
	                 String mainPictureValue,
	                 int id,
	                 String descriptionValue,
	                 String surfaceValue,
	                 String roomsValue,
	                 String adressValue,
	                 String contactValue,
	                 String updateDate,
	                 String sellDate,
	                 Double lat,
	                 Double lng) {
		
		this.isSellStatus     = isSellStatus;
		this.typeValue        = typeValue;
		this.cityValue        = cityValue;
		this.priceValue       = priceValue;
		this.mainPictureValue = mainPictureValue;
		this.id               = id;
		this.descriptionValue = descriptionValue;
		this.surfaceValue     = surfaceValue;
		this.roomsValue       = roomsValue;
		this.adressValue      = adressValue;
		this.contactValue     = contactValue;
		this.updateDate       = updateDate;
		this.sellDate         = sellDate;
		this.lat              = lat;
		this.lng              = lng;
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Boolean getSellStatus() {
		return isSellStatus;
	}
	
	public void setSellStatus(Boolean sellStatus) {
		isSellStatus = sellStatus;
	}
	
	public String getTypeValue() {
		return typeValue;
	}
	
	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}
	
	public String getCityValue() {
		return cityValue;
	}
	
	public void setCityValue(String cityValue) {
		this.cityValue = cityValue;
	}
	
	public String getPriceValue() {
		return priceValue;
	}
	
	public void setPriceValue(String priceValue) {
		this.priceValue = priceValue;
	}
	
	public String getMainPictureValue() {
		return mainPictureValue;
	}
	
	public void setMainPictureValue(String mainPictureValue) {
		this.mainPictureValue = mainPictureValue;
	}
	
	public String getDescriptionValue() {
		return descriptionValue;
	}
	
	public void setDescriptionValue(String descriptionValue) {
		this.descriptionValue = descriptionValue;
	}
	
	public String getSurfaceValue() {
		return surfaceValue;
	}
	
	public void setSurfaceValue(String surfaceValue) {
		this.surfaceValue = surfaceValue;
	}
	
	public String getRoomsValue() {
		return roomsValue;
	}
	
	public void setRoomsValue(String roomsValue) {
		this.roomsValue = roomsValue;
	}
	
	public String getAdressValue() {
		return adressValue;
	}
	
	public void setAdressValue(String adressValue) {
		this.adressValue = adressValue;
	}
	
	public String getContactValue() {
		return contactValue;
	}
	
	public void setContactValue(String contactValue) {
		this.contactValue = contactValue;
	}
	
	public String getUpdateDate() {
		return updateDate;
	}
	
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
	public String getSellDate() {
		return sellDate;
	}
	
	public void setSellDate(String sellDate) {
		this.sellDate = sellDate;
	}
	
	public Double getLat() {
		return lat;
	}
	
	public void setLat(Double lat) {
		this.lat = lat;
	}
	
	public Double getLng() {
		return lng;
	}
	
	public void setLng(Double lng) {
		this.lng = lng;
	}
}