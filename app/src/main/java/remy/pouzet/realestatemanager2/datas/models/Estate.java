package remy.pouzet.realestatemanager2.datas.models;

import android.content.ContentValues;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

/**
 * Created by Remy Pouzet on 03/11/2020.
 */
@Entity public class Estate {
	
	@ColumnInfo(name = "type") @Nullable String type;
	
	@ColumnInfo(name = "city") @Nullable private String city;
	
	@ColumnInfo(name = "price") private int price;
	
	@ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) private long id;
	
	@ColumnInfo(name = "mainPicture") @Nullable private String mainPicture;
	
	@ColumnInfo(name = "galeryPictures") @Nullable private List<String> galeryPictures;
	
	@ColumnInfo(name = "description") @Nullable private String description;
	
	@ColumnInfo(name = "surface") private int surface;
	
	@ColumnInfo(name = "rooms") private int rooms;
	
	@ColumnInfo(name = "adress") @Nullable private String adress;
	
	@ColumnInfo(name = "agent") @Nullable private String agent;
	
	@ColumnInfo(name = "updateDate") @Nullable private String updateDate;
	
	@ColumnInfo(name = "sellDate") @Nullable private String sellDate;
	
	@ColumnInfo(name = "lat") @Nullable private Double lat;
	@ColumnInfo(name = "lng") @Nullable private Double lng;
	
	public Estate(String type,
	              @org.jetbrains.annotations.Nullable String city,
	              int price,
	              @org.jetbrains.annotations.Nullable String mainPicture,
	              @org.jetbrains.annotations.Nullable List<String> galeryPictures,
	              long id,
	              @org.jetbrains.annotations.Nullable String description,
	              int surface,
	              int rooms,
	              @org.jetbrains.annotations.Nullable String adress,
	              @org.jetbrains.annotations.Nullable String agent,
	              @org.jetbrains.annotations.Nullable String updateDate,
	              @org.jetbrains.annotations.Nullable String sellDate,
	              @org.jetbrains.annotations.Nullable Double lat,
	              @org.jetbrains.annotations.Nullable Double lng
	
	             ) {
		this.id             = id;
		this.type           = type;
		this.city           = city;
		this.price          = price;
		this.mainPicture    = mainPicture;
		this.galeryPictures = galeryPictures;
		this.description    = description;
		this.surface        = surface;
		this.rooms          = rooms;
		this.adress         = adress;
		this.agent          = agent;
		this.updateDate     = updateDate;
		this.sellDate       = sellDate;
		this.lat            = lat;
		this.lng            = lng;
	}
	
	@Nullable public List<String> getGaleryPictures() {
		return galeryPictures;
	}
	
	public void setGaleryPictures(@Nullable List<String> galeryPictures) {
		this.galeryPictures = galeryPictures;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String parameterType) {
		type = parameterType;
	}
	
	@Nullable public String getCity() {
		return city;
	}
	
	public void setCity(@Nullable String parameterCity) {
		city = parameterCity;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int parameterPrice) {
		price = parameterPrice;
	}
	
	public long getId() {
		return id;
	}
	
	// --- UTILS ---
	public static Estate fromContentValues(ContentValues values) {
		final Estate estate = new Estate(null,
		                                 null,
		                                 0,
		                                 null,
		                                 null,
		                                 0,
		                                 null,
		                                 0,
		                                 0,
		                                 null,
		                                 null,
		                                 null,
		                                 null,
		                                 null,
		                                 null);
		if (values.containsKey("id")) {
			estate.setId(values.getAsLong("id"));
		}
		if (values.containsKey("type")) {
			estate.setType(values.getAsString("type"));
		}
		if (values.containsKey("city")) {
			estate.setCity(values.getAsString("city"));
		}
		if (values.containsKey("price")) {
			estate.setPrice(values.getAsInteger("price"));
		}
		if (values.containsKey("mainPicture")) {
			estate.setMainPicture(values.getAsString("mainPicture"));
		}
		if (values.containsKey("description")) {
			estate.setDescription(values.getAsString("description"));
		}
		if (values.containsKey("surface")) {
			estate.setSurface(values.getAsInteger("surface"));
		}
		if (values.containsKey("rooms")) {
			estate.setRooms(values.getAsInteger("rooms"));
		}
		if (values.containsKey("adress")) {
			estate.setAdress(values.getAsString("adress"));
		}
		if (values.containsKey("agent")) {
			estate.setAgent(values.getAsString("agent"));
		}
		if (values.containsKey("updateDate")) {
			estate.setUpdateDate(values.getAsString("updateDate"));
		}
		if (values.containsKey("sellDate")) {
			estate.setSellDate(values.getAsString("sellDate"));
		}
		if (values.containsKey("lat")) {
			estate.setLat(values.getAsDouble("lat"));
		}
		if (values.containsKey("lng")) {
			estate.setLng(values.getAsDouble("lng"));
		}
		if (values.containsKey("galeryPicture")) {
			estate.setGaleryPictures((List<String>) values.get("galeryPicture"));
		}
		return estate;
	}
	
	public void setId(long parameterId) {
		id = parameterId;
	}
	
	@Nullable public String getMainPicture() {
		return mainPicture;
	}
	
	public void setMainPicture(@Nullable String parameterMainPicture) {
		mainPicture = parameterMainPicture;
	}
	
	@Nullable public String getDescription() {
		return description;
	}
	
	public void setDescription(@Nullable String parameterDescription) {
		description = parameterDescription;
	}
	
	public int getSurface() {
		return surface;
	}
	
	public void setSurface(int parameterSurface) {
		surface = parameterSurface;
	}
	
	public int getRooms() {
		return rooms;
	}
	
	public void setRooms(int parameterRooms) {
		rooms = parameterRooms;
	}
	
	@Nullable public String getAdress() {
		return adress;
	}
	
	public void setAdress(@Nullable String parameterAdress) {
		adress = parameterAdress;
	}
	
	@Nullable public String getAgent() {
		return agent;
	}
	
	public void setAgent(@Nullable String parameterAgent) {
		agent = parameterAgent;
	}
	
	@Nullable public String getUpdateDate() {
		return updateDate;
	}
	
	@Nullable public String getSellDate() {
		return sellDate;
	}
	
	public void setSellDate(@Nullable String parameterSellDate) {
		sellDate = parameterSellDate;
	}
	
	@Nullable public Double getLat() {
		return lat;
	}
	
	public void setLat(@Nullable Double lat) {
		this.lat = lat;
	}
	
	@Nullable public Double getLng() {
		return lng;
	}
	
	public void setLng(@Nullable Double lng) {
		this.lng = lng;
	}
	
	public void setUpdateDate(@Nullable String parameterUpdateDate) {
		updateDate = parameterUpdateDate;
	}
}
