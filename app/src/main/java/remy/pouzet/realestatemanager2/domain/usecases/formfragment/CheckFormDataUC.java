package remy.pouzet.realestatemanager2.domain.usecases.formfragment;
import android.content.Context;

import remy.pouzet.realestatemanager2.R;
import remy.pouzet.realestatemanager2.databinding.FragmentFormBinding;
import remy.pouzet.realestatemanager2.domain.usecases.utils.ShowIndefiniteSnackBarUC;

import static remy.pouzet.realestatemanager2.R.string.minimal_words_lenght;

/**
 * Created by Remy Pouzet on 15/12/2020.
 */
public class CheckFormDataUC {
	
	public boolean checkFormDataUC(FragmentFormBinding mFragmentFormBinding,
	                               Context context,
	                               Boolean sellStatus) {
		int    localTrue         = 1;
		int    localFalse        = 0;
		int    nullEquivalent    = 1;
		int    minimalWordLenght = 3;
		int    localFakeboolean  = localTrue;
		String message           = null;
		
		if (sellStatus) {
			new ShowIndefiniteSnackBarUC().showIndefiniteSnackBarUC(mFragmentFormBinding.getRoot(),
			                                                        String.valueOf((R.string.sell_date_cannot_be_null)),
			                                                        context);
			localFakeboolean = localFalse;
		}
		if (mFragmentFormBinding.valueOfEstateCityFragmentForm.getText()
		                                                      .length() < minimalWordLenght) {
			new ShowIndefiniteSnackBarUC().showIndefiniteSnackBarUC(mFragmentFormBinding.getRoot(),
			                                                        String.valueOf(
					                                                        minimal_words_lenght),
			                                                        context);
			
			localFakeboolean = localFalse;
		}
		if (mFragmentFormBinding.valueOfEstatePriceFragmentForm.getText()
		                                                       .length() < nullEquivalent) {
			new ShowIndefiniteSnackBarUC().showIndefiniteSnackBarUC(mFragmentFormBinding.getRoot(),
			                                                        String.valueOf(R.string.estate_price_cannot_be_null),
			                                                        context);
			localFakeboolean = localFalse;
		}
		if (mFragmentFormBinding.surfaceValueFragmentForm.getText().length() < nullEquivalent) {
			new ShowIndefiniteSnackBarUC().showIndefiniteSnackBarUC(mFragmentFormBinding.getRoot(),
			                                                        String.valueOf(R.string.surface_value_cannot_be_null),
			                                                        context);
			localFakeboolean = localFalse;
		}
		if (mFragmentFormBinding.roomsValueFragmentForm.getText().length() < nullEquivalent) {
			new ShowIndefiniteSnackBarUC().showIndefiniteSnackBarUC(mFragmentFormBinding.getRoot(),
			                                                        String.valueOf(R.string.rooms_value_cannot_be_null),
			                                                        context);
			localFakeboolean = localFalse;
		}
		if (mFragmentFormBinding.contactValueFragmentForm.getText().length() < nullEquivalent) {
			message = "pourquoi String.valueOf R.String ne fonctionne pas ?";
			new ShowIndefiniteSnackBarUC().showIndefiniteSnackBarUC(mFragmentFormBinding.getRoot(),
			                                                        message,
			                                                        context);
			localFakeboolean = localFalse;
		}
		return localFakeboolean != localFalse;
//		String mainPicture, ?
//		String adress, ?
//		String agent, notnull
//	              List<String> pOI, ?
	}
}
