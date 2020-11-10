package remy.pouzet.realestatemanager2.views.fragments.estatesList;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import remy.pouzet.realestatemanager2.databinding.ContentItemsOfFragmentEstateListBinding;
import remy.pouzet.realestatemanager2.datas.models.Estate;

/**
 * Created by Remy Pouzet on 10/11/2020.
 */
public class EstatesListViewHolder extends RecyclerView.ViewHolder {
	
	private @NonNull final ContentItemsOfFragmentEstateListBinding mContentItemsOfFragmentEstateListBinding;
	public                 ImageView                               mainPictureEstate;
	public                 TextView                                typeEstate;
	public                 TextView                                cityLocationEstate;
	public                 TextView                                priceEstate;
	
	public EstatesListViewHolder(@NonNull View itemView) {
		super(itemView);
		mContentItemsOfFragmentEstateListBinding = ContentItemsOfFragmentEstateListBinding.bind(itemView);
		mainPictureEstate                        = mContentItemsOfFragmentEstateListBinding.estateMainPictureOfContentItemOfFragmentEstateList;
		typeEstate                               = mContentItemsOfFragmentEstateListBinding.estateTypeOfContentItemOfFragmentEstateList;
		cityLocationEstate                       = mContentItemsOfFragmentEstateListBinding.estateCityLocationOfContentItemOfFragmentEstateList;
		priceEstate                              = mContentItemsOfFragmentEstateListBinding.estatePriceOfContentItemOfFragmentEstateList;
	}
	
	public void updateEstates(Estate estate) {
		typeEstate.setText("TEST");
	}

//	private void clickRestaurant(String placeID) {
//		RestaurantsRepository.getInstance().mRestaurantsApiInterfaceService
//				.getResponseOfPlaceDetailsRestaurants(placeID, BuildConfig.apiKey)
//				.enqueue(new Callback<ResponseOfPlaceDetailsRestaurants>() {
//					@Override
//					public void onResponse(Call<ResponseOfPlaceDetailsRestaurants> call,
//					                       Response<ResponseOfPlaceDetailsRestaurants> response) {
//						if (response.isSuccessful()) {
//
//							Restaurant restaurant = new Restaurant(placeID, "https://maps.googleapis.com/maps/api/place/photo?maxwidth=800&photoreference=" + response
//									.body()
//									.getResult()
//									.getPhotos()
//									.get(0)
//									.getPhotoReference() + "&key=" + BuildConfig.apiKey, response
//									                                       .body()
//									                                       .getResult()
//									                                       .getName(), response
//									                                       .body()
//									                                       .getResult()
//									                                       .getFormattedAddress(), response
//									                                       .body()
//									                                       .getStatus(), "distance", 0, response
//									                                       .body()
//									                                       .getResult()
//									                                       .getRating(), response
//									                                       .body()
//									                                       .getResult()
//									                                       .getInternationalPhoneNumber(), response
//									                                       .body()
//									                                       .getResult()
//									                                       .getWebsite(), 0, 0);
//
//							RestaurantDetailsActivity.startActivity(textViewProfile.getContext(), restaurant);
//						}
//					}
//
//					@Override
//					public void onFailure(Call<> call,
//					                      Throwable t) {
//					}
//				});
}

