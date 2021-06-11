package remy.pouzet.realestatemanager2.views.fragments.AdaptersAndViewHolders;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import remy.pouzet.realestatemanager2.databinding.ContentOfAlternatesPicturesBinding;
import remy.pouzet.realestatemanager2.datas.models.Estate;

/**
 * Created by Remy Pouzet on 11/06/2021.
 */
public class AlternatesPicturesViewHolder extends RecyclerView.ViewHolder {
	
	private final ContentOfAlternatesPicturesBinding contentOfAlternatesPicturesBinding;
	private final ImageView                          alternatePicture;
	
	public AlternatesPicturesViewHolder(@NonNull View itemView) {
		super(itemView);
		contentOfAlternatesPicturesBinding = ContentOfAlternatesPicturesBinding.bind(itemView);
		alternatePicture                   = contentOfAlternatesPicturesBinding.alternateEstatePicture;
	}
	
	public void updatePictures(Estate estate) {

//		if (estate.getMainPicture() != null) {
//			currentPhotoPath = estate.getMainPicture();
//			mainPicture.setImageURI(Uri.parse(currentPhotoPath));
//		}
	
	}
	
}
