package remy.pouzet.realestatemanager2.views.fragments.estateslist;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import remy.pouzet.realestatemanager2.R;
import remy.pouzet.realestatemanager2.databinding.ContentItemsOfFragmentEstateListBinding;
import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.datas.models.ListEvent;

/**
 * Created by Remy Pouzet on 10/11/2020.
 */
public class EstatesListViewHolder extends RecyclerView.ViewHolder {
    
    private final ContentItemsOfFragmentEstateListBinding mContentItemsOfFragmentEstateListBinding;
    private final ImageView                               mainPictureEstate;
    private final TextView                                typeEstate;
    private final TextView                                cityLocationEstate;
    private final TextView                                priceEstate;
    private final TextView                                selledStatut;
    private final ConstraintLayout                        constraintLayout;
    private       String                                  currentPhotoPath;

    public EstatesListViewHolder(@NonNull View itemView) {
        super(itemView);
        mContentItemsOfFragmentEstateListBinding = ContentItemsOfFragmentEstateListBinding.bind(
                itemView);
        selledStatut                             = mContentItemsOfFragmentEstateListBinding.selledStateFragmentEstateList;
        mainPictureEstate                        = mContentItemsOfFragmentEstateListBinding.estateMainPictureOfContentItemOfFragmentEstateList;
        typeEstate                               = mContentItemsOfFragmentEstateListBinding.estateTypeOfContentItemOfFragmentEstateList;
        cityLocationEstate                       = mContentItemsOfFragmentEstateListBinding.estateCityLocationOfContentItemOfFragmentEstateList;
        priceEstate                              = mContentItemsOfFragmentEstateListBinding.estatePriceOfContentItemOfFragmentEstateList;
        constraintLayout                         = mContentItemsOfFragmentEstateListBinding.constraintLayoutOfContentItemOfFragmentEstateList;
    }
    
    public void updateEstates(Estate estate) {
        if (estate.getSellDate() != null && estate.getSellDate().length() > 1) {
            selledStatut.setVisibility(View.VISIBLE);
        } else {
            selledStatut.setVisibility(View.INVISIBLE);
        }
        if (estate.getMainPicture() != null) {
            currentPhotoPath = estate.getMainPicture();
            setPic(mainPictureEstate);
        
        }
        typeEstate.setText(estate.getType());
        cityLocationEstate.setText(estate.getCity());
        priceEstate.setText((estate.getPrice() + "€"));
    
        if (isTablet(this.itemView.getContext())) {
            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    EventBus.getDefault().post(new ListEvent(estate.getId()));
                }
            });
        } else {
            constraintLayout.setOnClickListener((Navigation.createNavigateOnClickListener(R.id.action_nav_estates_list_to_nav_details,
                                                                                          saveEstateId(
                                                                                                  estate))));
        }
    }
    
    private void setPic(ImageView imageView) {
        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();
        
        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        
        BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;
        
        // Determine how much to scale down the image
        int scaleFactor = Math.max(1, Math.min(photoW / targetW, photoH / targetH));
        
        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize       = scaleFactor;
        bmOptions.inPurgeable        = true;
        
        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        imageView.setImageBitmap(bitmap);
    }
    
    public boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources()
                                  .getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE);
        boolean large = ((context.getResources()
                                 .getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }
    
    public Bundle saveEstateId(Estate estate) {
        Bundle bundle = new Bundle();
        bundle.putLong("id", estate.getId());
        return bundle;
    }
}

