package remy.pouzet.realestatemanager2;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowNetworkInfo;

import remy.pouzet.realestatemanager2.utils.Utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by Remy Pouzet on 05/11/2020.
 */

@RunWith(RobolectricTestRunner.class) public class ShadowConnectivityManagerTest {
	private ConnectivityManager connectivityManager;
	private ShadowNetworkInfo   shadowOfActiveNetworkInfo;
	
	@Before public void setUp() throws
	                            Exception {
		
		connectivityManager = (ConnectivityManager) ApplicationProvider.getApplicationContext()
		                                                               .getSystemService(Context.CONNECTIVITY_SERVICE);
		
		shadowOfActiveNetworkInfo = shadowOf(connectivityManager.getActiveNetworkInfo());
	}
	
	@Test public void getActiveNetworkInfo_shouldReturnTrueCorrectly() {
		shadowOfActiveNetworkInfo.setConnectionStatus(NetworkInfo.State.CONNECTED);
		assertTrue(Utils.isNetworkAvailable(ApplicationProvider.getApplicationContext()));
		
		shadowOfActiveNetworkInfo.setConnectionStatus(NetworkInfo.State.CONNECTING);
		assertFalse(Utils.isNetworkAvailable(ApplicationProvider.getApplicationContext()));
		
		shadowOfActiveNetworkInfo.setConnectionStatus(NetworkInfo.State.DISCONNECTED);
		assertFalse(Utils.isNetworkAvailable(ApplicationProvider.getApplicationContext()));
	}
}
