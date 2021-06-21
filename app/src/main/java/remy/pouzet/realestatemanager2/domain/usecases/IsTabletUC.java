package remy.pouzet.realestatemanager2.domain.usecases;
import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by Remy Pouzet on 20/06/2021.
 */
public class IsTabletUC {
	public boolean execute(Context context) {
		boolean xlarge = ((context.getResources()
		                          .getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE);
		boolean large = ((context.getResources()
		                         .getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
		return (xlarge || large);
	}
}
