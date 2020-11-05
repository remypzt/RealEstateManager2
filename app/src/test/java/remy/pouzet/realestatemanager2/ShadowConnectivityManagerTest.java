package remy.pouzet.realestatemanager2;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.shadows.ShadowNetworkInfo;

/**
 * Created by Remy Pouzet on 05/11/2020.
 */
class ShadowConnectivityManagerTest {
	private ConnectivityManager connectivityManager;
	private ShadowNetworkInfo   shadowOfActiveNetworkInfo;
	
	@Before
	public void setUp() throws
	                    Exception {
//
//		connectivityManager =
////				(ConnectivityManager)
//						getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//		shadowOfActiveNetworkInfo = shadowOf(connectivityManager.getActiveNetworkInfo());
	}
	
	@Test
	public void getActiveNetworkInfo_shouldReturnTrueCorrectly() {
		shadowOfActiveNetworkInfo.setConnectionStatus(NetworkInfo.State.CONNECTED);
//		assertThat(connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting()).isTrue();
//		assertThat(connectivityManager.getActiveNetworkInfo().isConnected()).isTrue();
//
//		shadowOfActiveNetworkInfo.setConnectionStatus(NetworkInfo.State.CONNECTING);
//		assertThat(connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting()).isTrue();
//		assertThat(connectivityManager.getActiveNetworkInfo().isConnected()).isFalse();
//
//		shadowOfActiveNetworkInfo.setConnectionStatus(NetworkInfo.State.DISCONNECTED);
//		assertThat(connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting()).isFalse();
//		assertThat(connectivityManager.getActiveNetworkInfo().isConnected()).isFalse();
	}
}
