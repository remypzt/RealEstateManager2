package remy.pouzet.realestatemanager2.datas.database;
import androidx.room.TypeConverter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Remy Pouzet on 15/06/2021.
 */
public class DataConverter implements Serializable {
	
	@TypeConverter public String toString(List<String> uriList) {
		if (uriList == null) {
			return (null);
		}
		String uris = uriList.toString();
		return uris;
	}
	
	@TypeConverter public List<String> toUriStringList(String uriListString) {
		if (uriListString == null) {
			return (null);
		}
		uriListString = uriListString.substring(1, uriListString.length() - 1);
		List<String> uris = new ArrayList<String>(Arrays.asList(uriListString.split(", ")));
		return uris;
	}
	
}
