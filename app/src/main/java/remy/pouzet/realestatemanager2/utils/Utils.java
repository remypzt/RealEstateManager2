package remy.pouzet.realestatemanager2.utils;

import android.content.Context;
import android.net.wifi.WifiManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Remy Pouzet on 02/11/2020.
 */
public class Utils {
	
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
	public static Boolean isInternetAvailable(Context context) {
		WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		return wifi.isWifiEnabled();
	}
}
