package remy.pouzet.realestatemanager2.domain.usecases.estate;
import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.datas.models.EstateRaw;

/**
 * Created by Remy Pouzet on 16/12/2020.
 */
public class TransformEstateRawToEstateUC {
	public Estate execute(EstateRaw estateRaw) {
		return new Estate(estateRaw.getTypeValue(),
		                  estateRaw.getCityValue(),
		                  Integer.parseInt(estateRaw.getPriceValue()),
		                  estateRaw.getMainPictureValue(),
		                  estateRaw.getId(),
		                  estateRaw.getDescriptionValue(),
		                  Integer.parseInt(estateRaw.getSurfaceValue()),
		                  Integer.parseInt(estateRaw.getRoomsValue()),
		                  estateRaw.getAdressValue(),
		                  estateRaw.getContactValue(),
		                  estateRaw.getUpdateDate(),
		                  estateRaw.getSellDate());
		
	}
}
