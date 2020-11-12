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
	private static volatile EstateDatabase INSTANCE;
	
	// --- INSTANCE ---
	public static EstateDatabase getInstance(Context context) {
		if (INSTANCE == null) {
			synchronized (EstateDatabase.class) {
				if (INSTANCE == null) {
					INSTANCE = Room
							.databaseBuilder(context.getApplicationContext(), EstateDatabase.class, "MyDatabase.db")
							.addCallback(prepopulateDatabase())
							.build();
				}
			}
		}
		return INSTANCE;
	}
	
	private static Callback prepopulateDatabase() {
		return new Callback() {
			
			@Override
			public void onCreate(@NonNull SupportSQLiteDatabase db) {
				super.onCreate(db);
				
				ContentValues contentValues = new ContentValues();
				contentValues.put("id", 1);
				contentValues.put("test", "test");
				db.insert("User", OnConflictStrategy.IGNORE, contentValues);
			}
		};
	}
	
	// ---
	
	// --- DAO ---
	public abstract EstateDao mEstateDao();
}