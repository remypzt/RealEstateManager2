package remy.pouzet.realestatemanager2.repositories;

import androidx.lifecycle.LiveData;

import java.util.List;

import remy.pouzet.realestatemanager2.datas.database.dao.EstateDao;
import remy.pouzet.realestatemanager2.datas.models.Estate;

/**
 * Created by Remy Pouzet on 17/11/2020.
 */
public class EstateRepository {
	
	private final EstateDao estateDao;
	
	public EstateRepository(EstateDao estateDao) {
		this.estateDao = estateDao;
	}
	
	// --- GET ---
	/*public LiveData<List<Estate>> getAllEstates() {
		return this.estateDao.getAllEstates();
	}*/
	
	public LiveData<Estate> getEstate(long id) {
		return this.estateDao.getEstate(id);
	}
	
	// --- CREATE ---
	public void createEstate(Estate estate) {
		estateDao.createEstate(estate);
	}
	
	// --- DELETE ---
	public void deleteEstate(long estateId) {
		estateDao.deleteEstate(estateId);
	}
	
	// --- UPDATE ---
	public void updateEstate(Estate estate) {
		estateDao.updateEstate(estate);
	}
}
