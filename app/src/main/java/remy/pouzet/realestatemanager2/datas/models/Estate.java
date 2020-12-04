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

    @PrimaryKey(autoGenerate = true)
    private long mId;

    @ColumnInfo(name = "type")
    private String mType;

    @Nullable
    private String mCity;

    private int mPrice;

    @Nullable
    private String mMainPicture;

    @Nullable
    private String mDescription;

    private int mSurface;

    private int mRooms;

    @Nullable
    private String mAdress;

    @Nullable
    private String mStatus;

    @Nullable
    private String mAgent;

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
    ) {
        mId = id;
        mType = type;
        mCity = city;
        mPrice = price;
        mMainPicture = mainPicture;
        mDescription = description;
        mSurface = surface;
        mRooms = rooms;
        mAdress = adress;
        mStatus = status;
        mAgent = agent;
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
