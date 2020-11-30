package remy.pouzet.realestatemanager2.datas.models;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Remy Pouzet on 03/11/2020.
 */
@Entity
public class Estate {
	
	@ColumnInfo(name = "type") private       String mType;
	@Nullable private                        String mCity;
	private                                  int    mPrice;
	@PrimaryKey(autoGenerate = true) private long   mId;
	@Nullable private                        String mMainPicture;
	@Nullable private                        String mDescription;
	private                                  int    mSurface;
	private                                  int    mRooms;
	@Nullable private                        String mAdress;
	@Nullable private                        String mAgent;
	@Nullable private                        String mUpdateDate;
	@Nullable private                        String mSellDate;
	//	private List<String> mPOI;
	
	//Why there is a max of 7 parameters authorized and how can I change it ?
	public Estate(String type,
	              String city,
	              int price,
	              String mainPicture,
	              long id,
	              String description,
	              int surface,
	              int rooms,
	              String adress,
	              String agent,
	              String updateDate,
	              String sellDate
//	              List<String> pOI,
//
	
	             ) {
		mId          = id;
		mType        = type;
		mCity        = city;
		mPrice       = price;
		mMainPicture = mainPicture;
		mDescription = description;
		mSurface     = surface;
		mRooms       = rooms;
		mAdress      = adress;
		mAgent       = agent;
		mUpdateDate  = updateDate;
		mSellDate    = sellDate;
//		mPOI          = pOI;
	
	}
	
	public long getId() {
		return mId;
	}
	
	public void setId(long parameterId) {
		mId = parameterId;
	}
	
	@Nullable
	public String getSellDate() {
		return mSellDate;
	}
	
	public void setSellDate(@Nullable String parameterSellDate) {
		mSellDate = parameterSellDate;
	}
	
	public String getUpdateDate() {
		return mUpdateDate;
	}
	
	public void setUpdateDate(String parameterUpdateDate) {
		mUpdateDate = parameterUpdateDate;
	}
	
	public String getAgent() {
		return mAgent;
	}
	
	public void setAgent(String parameterAgent) {
		mAgent = parameterAgent;
	}
	
	public String getType() {
		return mType;
	}
	
	public void setType(String parameterType) {
		mType = parameterType;
	}
	
	public String getCity() {
		return mCity;
	}
	
	public void setCity(String parameterCity) {
		mCity = parameterCity;
	}
	
	public int getPrice() {
		return mPrice;
	}
	
	public void setPrice(int parameterPrice) {
		mPrice = parameterPrice;
	}
	
	public String getMainPicture() {
		return mMainPicture;
	}
	
	public void setMainPicture(String parameterMainPicture) {
		mMainPicture = parameterMainPicture;
	}
	
	public String getDescription() {
		return mDescription;
	}
	
	public void setDescription(String parameterDescription) {
		mDescription = parameterDescription;
	}
	
	public int getSurface() {
		return mSurface;
	}
	
	public void setSurface(int parameterSurface) {
		mSurface = parameterSurface;
	}
	
	public int getRooms() {
		return mRooms;
	}
	
	public void setRooms(int parameterRooms) {
		mRooms = parameterRooms;
	}
	
	public String getAdress() {
		return mAdress;
	}
	
	public void setAdress(String parameterAdress) {
		mAdress = parameterAdress;
	}
	
}
