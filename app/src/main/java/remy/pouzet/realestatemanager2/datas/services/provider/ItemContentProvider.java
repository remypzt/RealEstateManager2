package remy.pouzet.realestatemanager2.datas.services.provider;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import remy.pouzet.realestatemanager2.datas.database.EstateDatabase;
import remy.pouzet.realestatemanager2.datas.models.Estate;

/**
 * Created by Remy Pouzet on 18/06/2021.
 */
public class ItemContentProvider extends ContentProvider {
	
	// FOR DATA
	public static final String AUTHORITY  = "remy.pouzet.realestatemanager2.datas.services.provider";
	public static final String TABLE_NAME = Estate.class.getSimpleName();
	public static final Uri    URI_ITEM   = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);
	
	@Override public boolean onCreate() {
		return true;
	}
	
	@Nullable @Override
	public Cursor query(@NonNull Uri uri,
	                    @Nullable String[] projection,
	                    @Nullable String selection,
	                    @Nullable String[] selectionArgs,
	                    @Nullable String sortOrder) {
		if (getContext() != null) {
			long id = ContentUris.parseId(uri);
			final Cursor cursor = EstateDatabase.getInstance(getContext())
			                                    .estateDao()
			                                    .getEstateFromCursor(id);
			cursor.setNotificationUri(getContext().getContentResolver(), uri);
			return cursor;
		}
		
		throw new IllegalArgumentException("Failed to query row for uri " + uri);
	}
	
	@Nullable @Override public String getType(@NonNull Uri uri) {
		return "vnd.android.cursor.item/" + AUTHORITY + "." + TABLE_NAME;
	}
	
	@Nullable @Override public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
		if (getContext() != null) {
			final long id = EstateDatabase.getInstance(getContext())
			                              .estateDao()
			                              .createEstate(Estate.fromContentValues(values));
			if (id != 0) {
				getContext().getContentResolver().notifyChange(uri, null);
				return ContentUris.withAppendedId(uri, id);
			}
		}
		
		throw new IllegalArgumentException("Failed to insert row into " + uri);
	}
	
	@Override
	public int delete(@NonNull Uri uri,
	                  @Nullable String selection,
	                  @Nullable String[] selectionArgs) {
		if (getContext() != null) {
			final int count = EstateDatabase.getInstance(getContext())
			                                .estateDao()
			                                .deleteEstate(ContentUris.parseId(uri));
			getContext().getContentResolver().notifyChange(uri, null);
			return count;
		}
		throw new IllegalArgumentException("Failed to delete row into " + uri);
	}
	
	@Override
	public int update(@NonNull Uri uri,
	                  @Nullable ContentValues values,
	                  @Nullable String selection,
	                  @Nullable String[] selectionArgs) {
		if (getContext() != null) {
			final int count = EstateDatabase.getInstance(getContext())
			                                .estateDao()
			                                .updateEstate(Estate.fromContentValues(values));
			getContext().getContentResolver().notifyChange(uri, null);
			return count;
		}
		throw new IllegalArgumentException("Failed to update row into " + uri);
	}
}
