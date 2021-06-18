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
	
	private final Context      context;
	// FOR DATA
	private       List<String> picturesUriList = new ArrayList<>();
	private final boolean      isFromForm;
	
	public AlternatesPicturesAdapter(List<String> picturesUriList,
	                                 Context context,
	                                 boolean isFromForm) {
		this.picturesUriList = picturesUriList;
		this.context         = context;
		this.isFromForm      = isFromForm;
		
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
		holder.updatePictures(Uri.parse(picturesUriList.get(position)));
	}
	
	@Override public int getItemCount() {
		return this.picturesUriList.size();
	}
}
