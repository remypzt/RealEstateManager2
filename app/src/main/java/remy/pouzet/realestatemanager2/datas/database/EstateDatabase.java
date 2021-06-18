package remy.pouzet.realestatemanager2.datas.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import remy.pouzet.realestatemanager2.datas.database.dao.EstateDao;
import remy.pouzet.realestatemanager2.datas.models.Estate;

/**
 * Created by Remy Pouzet on 12/11/2020.
 */

@Database(entities = {Estate.class}, version = 1, exportSchema = false)
@TypeConverters({DataConverter.class}) public abstract class EstateDatabase extends RoomDatabase {
	
	public final static String DATABASE_NAME = "MyDatabase.db";
	
	// --- SINGLETON ---
	private static volatile EstateDatabase instance;
	
	// --- INSTANCE ---
	public static EstateDatabase getInstance(Context context) {
		if (instance == null) {
			synchronized (EstateDatabase.class) {
				if (instance == null) {
					instance = Room.databaseBuilder(context.getApplicationContext(),
					                                EstateDatabase.class,
					                                DATABASE_NAME).allowMainThreadQueries()
//					               .addCallback(prepopulateDatabase())
                                   .build();
				}
			}
		}
		return instance;
	}

//	private static Callback prepopulateDatabase() {
//		return new Callback() {
//			static final String PREPOPULATE = "prepopulate";
//			static final String PREPOPULATE_2 = "prepopulate 2";
//
//			@Override public void onCreate(@NonNull SupportSQLiteDatabase db) {
//				super.onCreate(db);
//				ContentValues contentValues1 = new ContentValues();
//				contentValues1.put("type", PREPOPULATE + " type");
//				contentValues1.put("city", PREPOPULATE + " city");
//				contentValues1.put("price", 0);
//				contentValues1.put("mainPicture", PREPOPULATE);
//				contentValues1.put("galeryPictures", PREPOPULATE);
//
//				contentValues1.put("surface", PREPOPULATE);
//				contentValues1.put("rooms", 1);
//				contentValues1.put("description", 1);
//				contentValues1.put("sellDate", PREPOPULATE);
//				contentValues1.put("updateDate", "prepulate");
//				contentValues1.put("agent", PREPOPULATE);
//				contentValues1.put("adress",
//				                   "1431 Plymouth St, Mountain View, CA 94043, États-Unis");
//				contentValues1.put("lat", -14.92873);
//				contentValues1.put("lng", 38.59995);
//				db.insert("Estate", OnConflictStrategy.IGNORE, contentValues1);
//
//				ContentValues contentValues2 = new ContentValues();
//				contentValues2.put("type", PREPOPULATE_2 + "type");
//				contentValues2.put("city", PREPOPULATE_2 + "city");
//				contentValues2.put("price", 0);
//				contentValues2.put("mainPicture", PREPOPULATE_2);
//				contentValues2.put("galeryPictures", PREPOPULATE_2);
//				contentValues2.put("surface", PREPOPULATE_2);
//				contentValues2.put("rooms", 1);
//				contentValues2.put("description", 1);
//				contentValues2.put("sellDate", PREPOPULATE_2);
//				contentValues2.put("updateDate", PREPOPULATE_2);
//				contentValues2.put("agent", PREPOPULATE_2);
//				contentValues2.put("adress",
//				                   "3160 N Shoreline Blvd, Mountain View, CA 94043, États-Unis");
//				contentValues2.put("lat", -24.92873);
//				contentValues2.put("lng", 88.59995);
//
//				db.insert("Estate", OnConflictStrategy.IGNORE, contentValues2);
//			}
//		};
//	}
	
	// --- DAO ---
	public abstract EstateDao estateDao();
}