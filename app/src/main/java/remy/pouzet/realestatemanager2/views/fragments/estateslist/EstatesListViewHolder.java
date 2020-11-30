package remy.pouzet.realestatemanager2.views.fragments.estateslist;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import remy.pouzet.realestatemanager2.R;
import remy.pouzet.realestatemanager2.databinding.ContentItemsOfFragmentEstateListBinding;
import remy.pouzet.realestatemanager2.datas.models.Estate;

/**
 * Created by Remy Pouzet on 10/11/2020.
 */
public class EstatesListViewHolder extends RecyclerView.ViewHolder {
	
	private final ContentItemsOfFragmentEstateListBinding mContentItemsOfFragmentEstateListBinding;
	private final ImageView                               mainPictureEstate;
	private final TextView                                typeEstate;
	private final TextView                                cityLocationEstate;
	private final TextView                                priceEstate;
	private final ConstraintLayout                        constraintLayout;
	
	public EstatesListViewHolder(@NonNull View itemView) {
		super(itemView);
		mContentItemsOfFragmentEstateListBinding = ContentItemsOfFragmentEstateListBinding.bind(itemView);
		mainPictureEstate                        = mContentItemsOfFragmentEstateListBinding.estateMainPictureOfContentItemOfFragmentEstateList;
		typeEstate                               = mContentItemsOfFragmentEstateListBinding.estateTypeOfContentItemOfFragmentEstateList;
		cityLocationEstate                       = mContentItemsOfFragmentEstateListBinding.estateCityLocationOfContentItemOfFragmentEstateList;
		priceEstate                              = mContentItemsOfFragmentEstateListBinding.estatePriceOfContentItemOfFragmentEstateList;
		constraintLayout                         = mContentItemsOfFragmentEstateListBinding.constraintLayoutOfContentItemOfFragmentEstateList;
	}
	
	public void updateEstates(Estate estate) {
		typeEstate.setText(estate.getType());
		cityLocationEstate.setText(estate.getCity());
		priceEstate.setText((estate.getPrice() + "€"));
		constraintLayout.setOnClickListener(
				//TODO put id inside bundle
				(Navigation.createNavigateOnClickListener(R.id.action_nav_estates_list_to_nav_details, saveEstateId(estate))));
	}
	
	public Bundle saveEstateId(Estate estate) {
		Bundle bundle = new Bundle();
		bundle.putLong("id", estate.getId());
		return bundle;
	}
}

