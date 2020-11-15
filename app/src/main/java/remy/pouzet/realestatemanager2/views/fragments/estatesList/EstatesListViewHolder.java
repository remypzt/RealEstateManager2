package remy.pouzet.realestatemanager2.views.fragments.estatesList;

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
	public        ImageView                               mainPictureEstate;
	public        TextView                                typeEstate;
	public        TextView                                cityLocationEstate;
	public        TextView                                priceEstate;
	public        ConstraintLayout                        constraintLayout;
	
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
		constraintLayout.setOnClickListener((Navigation.createNavigateOnClickListener(R.id.action_nav_estates_list_to_nav_details, null)));
	}
}

