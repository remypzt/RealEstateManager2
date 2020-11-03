package remy.pouzet.realestatemanager2.data.model;

import android.widget.ImageView;

import java.util.Date;
import java.util.List;

/**
 * Created by Remy Pouzet on 03/11/2020.
 */
public class Estate {
	
	private String    mType;
	private String    mCity;
	private int       mPrice;
	private ImageView mMainPicture;
	
	private Galerie mGalerie;
	private String  mDescription;
	private int     mSurface;
	private int     mRooms;
	private String  mAdress;
	
	private List<String> mPOI;
	private String       mStatus;
	private Date         mEntranceDate;
	private Date         mSelledDate;
	private String       mAgent;
	
	public Estate(String type,
	              String city,
	              int price,
	              ImageView mainPicture,
	              Galerie galerie,
	              String description,
	              int surface,
	              int rooms,
	              String adress,
	              List<String> pOI,
	              String status,
	              Date entranceDate,
	              Date selledDate,
	              String agent) {
		mType         = type;
		mCity         = city;
		mPrice        = price;
		mMainPicture  = mainPicture;
		mGalerie      = galerie;
		mDescription  = description;
		mSurface      = surface;
		mRooms        = rooms;
		mAdress       = adress;
		mPOI          = pOI;
		mStatus       = status;
		mEntranceDate = entranceDate;
		mSelledDate   = selledDate;
		mAgent        = agent;
	}
	
	public List<String> getPOI() {
		return mPOI;
	}
	
	public void setPOI(List<String> parameterPOI) {
		mPOI = parameterPOI;
	}
	
	public String getStatus() {
		return mStatus;
	}
	
	public void setStatus(String parameterStatus) {
		mStatus = parameterStatus;
	}
	
	public Date getEntranceDate() {
		return mEntranceDate;
	}
	
	public void setEntranceDate(Date parameterEntranceDate) {
		mEntranceDate = parameterEntranceDate;
	}
	
	public Date getSelledDate() {
		return mSelledDate;
	}
	
	public void setSelledDate(Date parameterSelledDate) {
		mSelledDate = parameterSelledDate;
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
	
	public ImageView getMainPicture() {
		return mMainPicture;
	}
	
	public void setMainPicture(ImageView parameterMainPicture) {
		mMainPicture = parameterMainPicture;
	}
	
	public Galerie getGalerie() {
		return mGalerie;
	}
	
	public void setGalerie(Galerie parameterGalerie) {
		mGalerie = parameterGalerie;
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
