package remy.pouzet.realestatemanager2.datas.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import remy.pouzet.realestatemanager2.datas.models.Estate;

/**
 * Created by Remy Pouzet on 12/11/2020.
 */
@Dao public interface EstateDao {
	
	@Query("SELECT * FROM Estate ") List<Estate> getAllEstates();
	
	@Query("SELECT * FROM Estate  WHERE id = :id") LiveData<Estate> getEstate(long id);
	
	@Insert(onConflict = OnConflictStrategy.REPLACE) long createEstate(Estate estate);
	
	@Update int updateEstate(Estate estate);
	
	@Query("DELETE FROM Estate  WHERE id = :id") int deleteEstate(long id);
}
