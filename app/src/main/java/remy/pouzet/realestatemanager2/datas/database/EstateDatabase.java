package remy.pouzet.realestatemanager2.datas.database;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import remy.pouzet.realestatemanager2.datas.database.dao.EstateDao;
import remy.pouzet.realestatemanager2.datas.models.Estate;

/**
 * Created by Remy Pouzet on 12/11/2020.
 */

@Database(entities = {Estate.class}, version = 1, exportSchema = false)
public abstract class EstateDatabase extends RoomDatabase {
	
	// --- SINGLETON ---
	private static volatile EstateDatabase instance;
	
	// --- INSTANCE ---
	public static EstateDatabase getInstance(Context context) {
		if (instance == null) {
			synchronized (EstateDatabase.class) {
				if (instance == null) {
					instance = Room
							.databaseBuilder(context.getApplicationContext(),
							                 EstateDatabase.class,
							                 "MyDatabase.db")
							.addCallback(prepopulateDatabase())
							.build();
				}
			}
		}
		return instance;
	}
	
	private static Callback prepopulateDatabase() {
		return new Callback() {
			static final String PREPOPULATE = "prepopulate";
			static final String PREPOPULATE_2 = "prepopulate 2";
			
			@Override public void onCreate(@NonNull SupportSQLiteDatabase db) {
				super.onCreate(db);
				ContentValues contentValues1 = new ContentValues();
				contentValues1.put("type", PREPOPULATE + "type");
				contentValues1.put("mCity", PREPOPULATE + "city");
				contentValues1.put("mPrice", 0);
				contentValues1.put("mMainPicture", PREPOPULATE);
				contentValues1.put("mSurface", PREPOPULATE);
				contentValues1.put("mRooms", 1);
				contentValues1.put("mDescription", 1);
				contentValues1.put("mSellDate", PREPOPULATE);
				contentValues1.put("mUpdateDate", "prepulate");
				contentValues1.put("mAgent", PREPOPULATE);
				contentValues1.put("mAdress", PREPOPULATE);
				db.insert("Estate", OnConflictStrategy.IGNORE, contentValues1);
				
				ContentValues contentValues2 = new ContentValues();
				contentValues2.put("type", PREPOPULATE_2 + "type");
				contentValues2.put("mCity", PREPOPULATE_2 + "city");
				contentValues2.put("mPrice", 0);
				contentValues2.put("mMainPicture", PREPOPULATE_2);
				contentValues2.put("mSurface", PREPOPULATE_2);
				contentValues2.put("mRooms", 1);
				contentValues2.put("mDescription", 1);
				contentValues1.put("mSellDate", PREPOPULATE_2);
				contentValues1.put("mUpdateDate", PREPOPULATE_2);
				contentValues2.put("mAgent", PREPOPULATE_2);
				contentValues2.put("mAdress", PREPOPULATE_2);
				db.insert("Estate", OnConflictStrategy.IGNORE, contentValues2);
			}
		};
	}
	
	// ---
	
	// --- DAO ---
	public abstract EstateDao mEstateDao();
}