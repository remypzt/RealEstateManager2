package remy.pouzet.realestatemanager2.views.fragments.AdaptersAndViewHolders;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import remy.pouzet.realestatemanager2.R;

/**
 * Created by Remy Pouzet on 11/06/2021.
 */
public class AlternatesPicturesAdapter extends RecyclerView.Adapter<AlternatesPicturesViewHolder> {
	
	private final Context           context;
	private final boolean           isFromForm;
	private final ItemClickListener itemClickListener;
	// FOR DATA
	private       List<String>      picturesUriList = new ArrayList<>();
	
	public AlternatesPicturesAdapter(List<String> picturesUriList,
	                                 Context context,
	                                 boolean isFromForm,
	                                 ItemClickListener itemClickListener) {
		this.picturesUriList   = picturesUriList;
		this.context           = context;
		this.isFromForm        = isFromForm;
		this.itemClickListener = itemClickListener;
		
	}
	
	@NonNull @Override
	public AlternatesPicturesViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
	                                                       int viewType) {
		// CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View           view     = inflater.inflate(R.layout.content_of_alternates_pictures,
		                                           parent,
		                                           false);
		return new AlternatesPicturesViewHolder(view, isFromForm);
	}
	
	@Override
	public void onBindViewHolder(@NonNull AlternatesPicturesViewHolder holder, int position) {
		holder.updatePictures(Uri.parse(picturesUriList.get(position)),
		                      position,
		                      itemClickListener);
	}
	
	@Override public int getItemCount() {
		return this.picturesUriList.size();
	}
	
	public interface ItemClickListener {
		void onItemClickedListener(View view, int position);
	}
}