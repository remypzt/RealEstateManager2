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

import java.util.List;

import remy.pouzet.realestatemanager2.datas.database.EstateDatabase;
import remy.pouzet.realestatemanager2.datas.database.dao.EstateDao;
import remy.pouzet.realestatemanager2.datas.models.Estate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Remy Pouzet on 15/11/2020.
 */

@RunWith(AndroidJUnit4.class) public class ItemDaoTest {

	// DATA SET FOR TEST
	private static final Estate ESTATE_DEMO   = new Estate("Type",
	                                                       "City",
	                                                       0,
	                                                       "R.drawable.ic_add",
	                                                       1,
	                                                       "Description",
	                                                       0,
	                                                       0,
	                                                       "Adress",
	                                                       "Status",
	                                                       "Agent",
	                                                       "test",
	                                                       0.0,
	                                                       0.0);
	private static final Estate ESTATE_DEMO_2 = new Estate("Type2",
	                                                       "City2",
	                                                       0,
	                                                       "R.drawable.ic_add2",
	                                                       2,
	                                                       "Description2",
	                                                       0,
	                                                       0,
	                                                       "Adress2",
	                                                       "Status2",
	                                                       "Agent2",
	                                                       "test",
	                                                       0.0,
	                                                       0.0);
	
	@Rule public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
	// FOR DATA
	private      EstateDatabase          database;
	private      EstateDao               mEstateDao;
	
	@Before public void initDb() throws
	                             Exception {
		this.database   = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(),
		                                               EstateDatabase.class)
		                      .allowMainThreadQueries()
		                      .build();
		this.mEstateDao = this.database.estateDao();
	}
	
	@After
	public void closeDb() throws
	                      Exception {
		database.close();
	}
	
	@Test
	public void createAndGetEstateTest() throws
	                                     InterruptedException {
//		BEFORE : Adding a new user
		this.database.estateDao()
				.createEstate(ESTATE_DEMO);
		// TEST
		Estate localEstate = LiveDataTestUtil.getValue(this.database.estateDao()
				                                               .getEstate(1));
		assertEquals(localEstate.getId(), ESTATE_DEMO.getId());
		assertEquals(localEstate.getId(), 1);
	}
	
	@Test
	public void insertAndGetAllEstatesTest() throws
	                                         Exception {
		this.database.estateDao()
				.createEstate(ESTATE_DEMO);
		this.database.estateDao()
				.createEstate(ESTATE_DEMO_2);
		List<Estate> allEstates = LiveDataTestUtil.getValue(mEstateDao.getAllEstates());
		assertEquals(allEstates.size(), 2);
		
	}
	
	@Test
	public void deleteEstateTest() throws
	                               Exception {
		this.database.estateDao()
				.createEstate(ESTATE_DEMO);
		mEstateDao.deleteEstate(1);
		List<Estate> allEstates = LiveDataTestUtil.getValue(mEstateDao.getAllEstates());
		assertTrue(allEstates.isEmpty());
	}
	
	@Test
	public void updateEstateTest() throws
	                               Exception {
		this.database.estateDao()
				.createEstate(ESTATE_DEMO);
		ESTATE_DEMO.setType("TypeModified");
		this.database.estateDao()
				.updateEstate(ESTATE_DEMO);
		Estate localEstate = LiveDataTestUtil.getValue(this.database.estateDao()
				                                               .getEstate(1));
		assertEquals(localEstate.getType(), ESTATE_DEMO.getType());
	}
	
}
