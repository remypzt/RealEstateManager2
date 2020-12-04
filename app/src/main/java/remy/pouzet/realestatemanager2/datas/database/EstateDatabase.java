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
                            .allowMainThreadQueries()
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
                ContentValues contentValues1 = new ContentValues();
                contentValues1.put("type", "prepopulate type");
                contentValues1.put("mCity", "prepopulate city");
                contentValues1.put("mPrice", 0);
                contentValues1.put("mMainPicture", "prepopulate");
                contentValues1.put("mSurface", "prepopulate");
                contentValues1.put("mRooms", 1);
                contentValues1.put("mDescription", 1);
                contentValues1.put("mStatus", "prepopulate");
                contentValues1.put("mAgent", "prepopulate");
                contentValues1.put("mAdress", "prepopulate");
                db.insert("Estate", OnConflictStrategy.IGNORE, contentValues1);

                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("type", "prepopulate type 2");
                contentValues2.put("mCity", "prepopulate city 2");
                contentValues2.put("mPrice", 0);
                contentValues2.put("mMainPicture", "prepopulate 2");
                contentValues2.put("mSurface", "prepopulate 2");
                contentValues2.put("mRooms", 1);
                contentValues2.put("mDescription", 1);
                contentValues2.put("mStatus", "prepopulate 2");
                contentValues2.put("mAgent", "prepopulate 2");
                contentValues2.put("mAdress", "prepopulate 2");
                db.insert("Estate", OnConflictStrategy.IGNORE, contentValues2);
            }
        };
    }

    // ---

    // --- DAO ---
    public abstract EstateDao mEstateDao();
}