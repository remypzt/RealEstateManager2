package remy.pouzet.realestatemanager2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.SimpleDateFormat;
import java.util.Date;

import remy.pouzet.realestatemanager2.utils.Utils;

import static org.junit.Assert.assertEquals;

/**
 * Created by Remy Pouzet on 03/11/2020.
 */
@RunWith(JUnit4.class)
public class UnitTest {
	@Test
	public void testingConversionEuroToDollar() {
		assertEquals(117, Utils.convertEurotoDollar(100), 0);
	}
	
	@Test
	public void testingGetDateUEformat() {
		long inMillis = System.currentTimeMillis();
		assertEquals(getTimeStamp(inMillis), Utils.getTodayDateUEformat());
	}
	
	public String getTimeStamp(long timeinMillies) {
		String           date      = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		date = formatter.format(new Date(timeinMillies));
		return date;
	}
	
}
