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
public class AlternatesPicturesAdapter extends RecyclerView.Adapter {
	
	// FOR DATA
	private List<Uri> picturesUriList = new ArrayList<>();
	private Context   context;
	
	public void RecyclerAdapter(List<Uri> picturesUriList, Context context) {
		this.picturesUriList = picturesUriList;
		this.context         = context;
	}
	
	@NonNull @Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		// CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.content_of_alternates_pictures, parent, false);
		return new AlternatesPicturesViewHolder(view);
	}
	
	@Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

//		holder.updatePictures(this.picturesUriList.get(position));
	}
	
	@Override public int getItemCount() {
		return this.picturesUriList.size();
	}
}
