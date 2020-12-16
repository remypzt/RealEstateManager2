package remy.pouzet.realestatemanager2.domain.usecases.formfragment;

import remy.pouzet.realestatemanager2.datas.models.EstateRaw;

/**
 * Created by Remy Pouzet on 15/12/2020.
 */

public class CheckFormDataUC {
	
	private static final int MINIMAL_WORD_LENGTH = 3;
	private static final int EMPTY               = 1;
	
	public EstateFormState execute(EstateRaw estateRaw) {
		if (estateRaw.getSellStatus()) {
			return EstateFormState.IS_SELL;
		}
		
		if (estateRaw.getCityValue().length() < MINIMAL_WORD_LENGTH) {
			return EstateFormState.ERROR_MINIMAL_WORD_LENGTH;
		}
		
		if (estateRaw.getPriceValue().length() < EMPTY) {
			return EstateFormState.ERROR_PRICE_VALUE;
		}
		
		if (estateRaw.getSurfaceValue().length() < EMPTY) {
			return EstateFormState.ERROR_SURFACE_VALUE;
		}
		
		if (estateRaw.getRoomsValue().length() < EMPTY) {
			return EstateFormState.ERROR_ROOMS_VALUE;
		}
		
		if (estateRaw.getContactValue().length() < EMPTY) {
			return EstateFormState.ERROR_CONTACT_VALUE;
		}
		return EstateFormState.IS_VALID;
	}
	
	public enum EstateFormState {
		IS_VALID,
		IS_SELL,
		ERROR_MINIMAL_WORD_LENGTH,
		ERROR_SURFACE_VALUE,
		ERROR_ROOMS_VALUE,
		ERROR_PRICE_VALUE,
		ERROR_CONTACT_VALUE,
	}
}

