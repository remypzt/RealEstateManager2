package remy.pouzet.realestatemanager2.views.fragments.AdaptersAndViewHolders;
import android.app.AlertDialog;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import remy.pouzet.realestatemanager2.databinding.ContentOfAlternatesPicturesBinding;

/**
 * Created by Remy Pouzet on 11/06/2021.
 */
public class AlternatesPicturesViewHolder extends RecyclerView.ViewHolder {
	
	private final ContentOfAlternatesPicturesBinding contentOfAlternatesPicturesBinding;
	private final ImageView                          alternatePicture;
	private final boolean                            isFromForm;
	
	public AlternatesPicturesViewHolder(@NonNull View itemView, boolean isFromForm) {
		super(itemView);
		contentOfAlternatesPicturesBinding = ContentOfAlternatesPicturesBinding.bind(itemView);
		alternatePicture                   = contentOfAlternatesPicturesBinding.alternateEstatePicture;
		this.isFromForm                    = isFromForm;
	}
	
	public void updatePictures(Uri uri) {
		if (uri != null) {
			alternatePicture.setImageURI(uri);
		}
		if (isFromForm) {
			alternatePicture.setOnClickListener(new View.OnClickListener() {
				@Override public void onClick(View v) {
					showChoiceSnackBar(v);
				}
			});
		}
	}
	
	public void showChoiceSnackBar(View view) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
		alertDialogBuilder.setTitle(null)
		                  .setCancelable(true)
		                  .setMessage("Erase this picture ?")
		                  .setPositiveButton("Yes", (dialog, id) -> {
			                  alternatePicture.setImageURI(null);
			                  Toast.makeText(view.getContext(), "Supressed !", Toast.LENGTH_SHORT)
			                       .show();
		                  })
		                  .setNegativeButton("No", (dialog, id) -> {
			                  dialog.cancel();
		                  });
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
	
}
