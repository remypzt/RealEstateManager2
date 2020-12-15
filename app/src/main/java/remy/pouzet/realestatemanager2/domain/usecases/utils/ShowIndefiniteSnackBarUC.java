package remy.pouzet.realestatemanager2.domain.usecases.utils;
import android.content.Context;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE;

/**
 * Created by Remy Pouzet on 15/12/2020.
 */
public class ShowIndefiniteSnackBarUC {
	
	public void showIndefiniteSnackBarUC(View view, String message, Context context) {
		if (message == null || message.trim().equals("")) {
			message = "Please enter text to show";
		}
		Snackbar.make(view, message, LENGTH_INDEFINITE)
		        .setAction("CLOSE", view1 -> {
			        //Do nothing because it's close automatically
		        })
		        .setActionTextColor(ContextCompat.getColor(context, android.R.color.holo_red_light))
		        .show();
	}
	
}
