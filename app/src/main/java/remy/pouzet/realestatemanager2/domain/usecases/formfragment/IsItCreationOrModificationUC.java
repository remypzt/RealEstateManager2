package remy.pouzet.realestatemanager2.domain.usecases.formfragment;
import android.os.Bundle;

import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.viewmodels.FormViewModel;

/**
 * Created by Remy Pouzet on 15/12/2020.
 */

public class IsItCreationOrModificationUC {
	
	public  long          id;
	private Estate        estate;
	private FormViewModel formViewModel;
	
	public IsItCreationOrModificationUC(Bundle bundle) {
		if (bundle != null)  // then it's a modification
		{
			id = Long.parseLong(bundle.get("id").toString());
			updateUI(getEstate(id));
		}
	}
	
	public void updateUI(Estate estate) {
	
	}
	
	public Estate getEstate(long id) {
		estate = formViewModel.observeEstate(id).getValue();
		return estate;
	}
	
}
