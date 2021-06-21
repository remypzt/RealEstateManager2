package remy.pouzet.realestatemanager2;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import remy.pouzet.realestatemanager2.datas.database.EstateDatabase;
import remy.pouzet.realestatemanager2.datas.services.provider.ItemContentProvider;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by Remy Pouzet on 18/06/2021.
 */

@RunWith(AndroidJUnit4.class) public class EstateContentProviderTest {
	
	// DATA SET FOR TEST
	private static final long            USER_ID = 1;
	// FOR DATA
	private              ContentResolver mContentResolver;
	
	@Before public void setUp() {
		Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), EstateDatabase.class)
		    .allowMainThreadQueries()
		    .build();
		mContentResolver = InstrumentationRegistry.getContext().getContentResolver();
	}
	
	@Test public void getItemsWhenNoItemInserted() {
		final Cursor cursor = mContentResolver.query(ContentUris.withAppendedId(ItemContentProvider.URI_ITEM,
		                                                                        USER_ID),
		                                             null,
		                                             null,
		                                             null,
		                                             null);
		assertThat(cursor, notNullValue());
		assertThat(cursor.getCount(), is(0));
		cursor.close();
	}
	
	@Test public void insertAndGetItem() {
		// BEFORE : Adding demo item
		final Uri userUri = mContentResolver.insert(ItemContentProvider.URI_ITEM, generateItem());
		// TEST
		final Cursor cursor = mContentResolver.query(ContentUris.withAppendedId(ItemContentProvider.URI_ITEM,
		                                                                        USER_ID),
		                                             null,
		                                             null,
		                                             null,
		                                             null);
		
		assertThat(cursor, notNullValue());
		assertThat(cursor.getCount(), is(1));
		cursor.moveToFirst();
//		assertThat(cursor.moveToFirst(), is(true));
		assertThat(cursor.getString(cursor.getColumnIndexOrThrow("id")), is("1"));
		cursor.close();
		mContentResolver.delete(userUri, null, null);
		
	}
	
	// ---
	
	private ContentValues generateItem() {
		final ContentValues values = new ContentValues();
		values.put("1", "id");
		values.put("loft", "type");
		values.put("Providercity", "city");
		values.put("5", "price");
		values.put("provider description", "description");
		values.put("5", "surface");
		values.put("5", "rooms");
		values.put("Mairie 35000 Rennes", "adress");
		values.put("provider agent", "agent");
		values.put("18/06/2021", "updateDate");
		
		values.put("", "mainPicture");
		values.put("", "sellDate");
		values.put("0.0", "lat");
		values.put("0.0", "lng");
		values.put("", "galeryPicture");
		return values;
	}
}

