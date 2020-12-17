package remy.pouzet.realestatemanager2.domain.usecases.formfragment;
import android.os.Bundle;

/**
 * Created by Remy Pouzet on 15/12/2020.
 */

public class IsItCreationOrModificationUC {
	
	public IsItCreationOrModification execute(Bundle bundle) {
		if (bundle != null)  // then it's a modification
		{
			return IsItCreationOrModification.ITS_MODIFICATION;
		}
		return IsItCreationOrModification.ITS_CREATION;
	}
	
	public enum IsItCreationOrModification {
		ITS_CREATION, ITS_MODIFICATION,
	}
	
}
