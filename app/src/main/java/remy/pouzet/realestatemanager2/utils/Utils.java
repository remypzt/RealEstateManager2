package remy.pouzet.realestatemanager2.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Remy Pouzet on 02/11/2020.
 */

//------------------------------------------------------//
// ------------------    Binding    ------------------- //
// ------------------   Variables   ------------------- //
// ------------------   LifeCycle   ------------------- //
// ------------------   Functions   ------------------- //
// ------------------     Intent    ------------------- //
// ------------------   Callbacks   ------------------- //
// ------------------     Data      ------------------- //
// ------------------    Adapter    ------------------- //
// ------------------ Miscellaneous ------------------- //
// ----------------- Navigation, Menu, UI ------------- //
//------------------------------------------------------//

public class Utils {
	
	private Utils() {
		throw new IllegalStateException("Utility class");
	}
	
	/**
	 * Conversion d'un prix d'un bien immobilier (Dollars vers Euros)
	 * NOTE : NE PAS SUPPRIMER, A MONTRER DURANT LA SOUTENANCE
	 *
	 * @param dollars
	 * @return
	 */
	public static int convertDollarToEuro(int dollars) {
		return (int) Math.round(dollars * 0.854);
	}
	
	public static int convertEurotoDollar(int euros) {
		return (int) Math.round(euros * 1.170);
	}
	
	/**
	 * Conversion de la date d'aujourd'hui en un format plus approprié
	 * NOTE : NE PAS SUPPRIMER, A MONTRER DURANT LA SOUTENANCE
	 *
	 * @return
	 */
	public static String getTodayDateUEformat() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(new Date());
	}
	
	public static String getTodayDateUSformat() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		return dateFormat.format(new Date());
	}
	
	/**
	 * Vérification de la connexion réseau
	 * NOTE : NE PAS SUPPRIMER, A MONTRER DURANT LA SOUTENANCE
	 *
	 * @param context
	 * @return
	 */
	
	public static Boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			Network nw = connectivityManager.getActiveNetwork();
			if (nw == null) {
				return false;
			}
			NetworkCapabilities actNw = connectivityManager.getNetworkCapabilities(nw);
			return actNw != null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET));
		} else {
			NetworkInfo nwInfo = connectivityManager.getActiveNetworkInfo();
			return nwInfo != null && nwInfo.isConnected();
		}
	}
	
	public static String uIformat(int dayOfMonth,
	                              int monthOfYear,
	                              int year) {
		
		String dateInUIformat;
		
		dateInUIformat = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
		
		return dateInUIformat;
	}
	
	public static String bEformat(int dayOfMonth,
	                              int monthOfYear,
	                              int year) {
		
		String dateInBEformat;
		
		dateInBEformat = year + "0" + (monthOfYear + 1) + "" + dayOfMonth;
		
		return dateInBEformat;
	}
	
	public static String bEformatException(int dayOfMonth,
	                                       int monthOfYear,
	                                       int year) {
		
		String dateInBEformatException;
		
		dateInBEformatException = year + "0" + (monthOfYear + 1) + "0" + dayOfMonth;
		
		return dateInBEformatException;
	}
}
