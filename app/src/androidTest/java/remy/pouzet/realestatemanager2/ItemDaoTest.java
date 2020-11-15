package remy.pouzet.realestatemanager2;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import remy.pouzet.realestatemanager2.datas.database.EstateDatabase;
import remy.pouzet.realestatemanager2.datas.models.Estate;

import static org.junit.Assert.assertTrue;

/**
 * Created by Remy Pouzet on 15/11/2020.
 */

@RunWith(AndroidJUnit4.class)
public class ItemDaoTest {
	// DATA SET FOR TEST
	private static final long                    ESTATE_ID               = 1;
	private static final Estate                  ESTATE_DEMO             = new Estate("Type", "City", 0, "R.drawable.ic_add", 1, "Description", 0, 0, "Adress", "Status", "Agent");
	@Rule public         InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
	// FOR DATA
	private              EstateDatabase          database;
	
	@Before
	public void initDb() throws
	                     Exception {
		this.database = Room
				.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), EstateDatabase.class)
				.allowMainThreadQueries()
				.build();
	}
	
	@After
	public void closeDb() throws
	                      Exception {
		database.close();
	}
	
	@Test
	public void insertAndGetUser() throws
	                               InterruptedException {
		// BEFORE : Adding a new user
		this.database
				.mEstateDao()
				.createEstate(ESTATE_DEMO);
		// TEST
		Estate localEstate = LiveDataTestUtil.getValue(this.database
				                                               .mEstateDao()
				                                               .getEstate(ESTATE_ID));
		assertTrue(localEstate
				           .getType()
				           .equals(ESTATE_DEMO.getType()) && localEstate
				           .getType()
				           .equals(ESTATE_ID));
		
	}
	
}
