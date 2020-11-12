package remy.pouzet.realestatemanager2.datas.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Remy Pouzet on 03/11/2020.
 */
@Entity
public class Estate {
	
	private String mType;
	private String mCity;
	private int    mPrice;
	
	@PrimaryKey(autoGenerate = true) private long mId;
	
	private String mMainPicture;
	
	private String mDescription;
	private int    mSurface;
	private int    mRooms;
	private String mAdress;
	
	private String mStatus;
	private String mAgent;
	
	//	private List<String> mPOI;
//	private Date         mEntranceDate;
//	private Date         mSelledDate;
	
	public Estate(String type,
	              String city,
	              int price,
	              String mainPicture,
	              long id,
	              String description,
	              int surface,
	              int rooms,
	              String adress,
	              String status,
	              String agent
//	              List<String> pOI,
//	              Date entranceDate,
//	              Date selledDate,
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
		mStatus      = status;
		mAgent       = agent;
//		mPOI          = pOI;
//		mEntranceDate = entranceDate;
//		mSelledDate   = selledDate;
	
	}
	
	public long getId() {
		return mId;
	}
	
	public void setId(long parameterId) {
		mId = parameterId;
	}
	
	public String getStatus() {
		return mStatus;
	}
	
	public void setStatus(String parameterStatus) {
		mStatus = parameterStatus;
	}

//	public List<String> getPOI() {
//		return mPOI;
//	}
//
//	public void setPOI(List<String> parameterPOI) {
//		mPOI = parameterPOI;
//	}
//
//
//	public Date getEntranceDate() {
//		return mEntranceDate;
//	}
//
//	public void setEntranceDate(Date parameterEntranceDate) {
//		mEntranceDate = parameterEntranceDate;
//	}
//
//	public Date getSelledDate() {
//		return mSelledDate;
//	}
//
//	public void setSelledDate(Date parameterSelledDate) {
//		mSelledDate = parameterSelledDate;
//	}
	
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
