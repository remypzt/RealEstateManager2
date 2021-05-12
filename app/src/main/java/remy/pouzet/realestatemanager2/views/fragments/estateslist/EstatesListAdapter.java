package remy.pouzet.realestatemanager2.views.fragments.estateslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import remy.pouzet.realestatemanager2.R;
import remy.pouzet.realestatemanager2.datas.models.Estate;

/**
 * Created by Remy Pouzet on 10/11/2020.
 */
public class EstatesListAdapter extends RecyclerView.Adapter<EstatesListViewHolder> {
    
    // FOR DATA
    private List<Estate> estatesList = new ArrayList<>();
    private Context      context;
    
    public void RecyclerAdapter(List<Estate> estatesList, Context context) {
        this.estatesList = estatesList;
        this.context     = context;
    }
    
    @NonNull @Override
    public EstatesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View           view     = inflater.inflate(R.layout.content_items_of_fragment_estate_list,
                                                   parent,
                                                   false);
        return new EstatesListViewHolder(view);
    }
    
    // UPDATE VIEW HOLDER
    @Override public void onBindViewHolder(@NonNull EstatesListViewHolder viewHolder,
                                           int position) {
        viewHolder.updateEstates(this.estatesList.get(position));

//        viewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                viewHolder.listener.onItemClick(position);
//            }
//        });
    
    }
    
    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override public int getItemCount() {
        return this.estatesList.size();
    }
    
    public void setData(List<Estate> estates) {
        this.estatesList.clear();
        this.estatesList.addAll(estates);
        notifyDataSetChanged();
    }

//    public static class EstatesListViewHolder extends RecyclerView.ViewHolder {
//
//        private final ContentItemsOfFragmentEstateListBinding mContentItemsOfFragmentEstateListBinding;
//        private final ImageView                               mainPictureEstate;
//        private final TextView                                typeEstate;
//        private final TextView                                cityLocationEstate;
//        private final TextView                                priceEstate;
//        private final TextView                                selledStatut;
//        private final ConstraintLayout                        constraintLayout;
//
//        public EstatesListViewHolder(@NonNull View itemView) {
//            super(itemView);
//            mContentItemsOfFragmentEstateListBinding = ContentItemsOfFragmentEstateListBinding.bind(
//                    itemView);
//            selledStatut                             = mContentItemsOfFragmentEstateListBinding.selledStateFragmentEstateList;
//            mainPictureEstate                        = mContentItemsOfFragmentEstateListBinding.estateMainPictureOfContentItemOfFragmentEstateList;
//            typeEstate                               = mContentItemsOfFragmentEstateListBinding.estateTypeOfContentItemOfFragmentEstateList;
//            cityLocationEstate                       = mContentItemsOfFragmentEstateListBinding.estateCityLocationOfContentItemOfFragmentEstateList;
//            priceEstate                              = mContentItemsOfFragmentEstateListBinding.estatePriceOfContentItemOfFragmentEstateList;
//            constraintLayout                         = mContentItemsOfFragmentEstateListBinding.constraintLayoutOfContentItemOfFragmentEstateList;
//        }
//
//        public void updateEstates(Estate estate) {
//            if (estate.getSellDate() != null && estate.getSellDate().length() > 1) {
//                selledStatut.setVisibility(View.VISIBLE);
//            } else {
//                selledStatut.setVisibility(View.INVISIBLE);
//            }
//            typeEstate.setText(estate.getType());
//            cityLocationEstate.setText(estate.getCity());
//            priceEstate.setText((estate.getPrice() + "€"));
//
//            constraintLayout.setOnClickListener((Navigation.createNavigateOnClickListener(R.id.action_nav_estates_list_to_nav_details,
//                                                                                          saveEstateId(
//                                                                                                  estate))));
//
////            if (isTablet(this.itemView.getContext())) {
////
////            } else {
////                constraintLayout.setOnClickListener((Navigation.createNavigateOnClickListener(R.id.action_nav_estates_list_to_nav_details,
////                                                                                              saveEstateId(
////                                                                                                      estate))));
////            }
//        }
//
//        public boolean isTablet(Context context) {
//            boolean xlarge = ((context.getResources()
//                                      .getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE);
//            boolean large = ((context.getResources()
//                                     .getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
//            return (xlarge || large);
//        }
//
//        public Bundle saveEstateId(Estate estate) {
//            Bundle bundle = new Bundle();
//            bundle.putLong("id", estate.getId());
//            return bundle;
//        }
//
//
//        //Declarative interface
//       public ItemClickListener listener;
//        //set method
//        public void setListener(ItemClickListener listener) {
//            this.listener = listener;
//        }
//        //Defining interface
//        public interface ItemClickListener{
//            //Achieve the click method, passing the subscript.
//            void onItemClick(int position);
//        }
//
//    }
}
