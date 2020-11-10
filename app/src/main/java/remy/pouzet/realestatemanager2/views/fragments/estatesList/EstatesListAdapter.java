package remy.pouzet.realestatemanager2.views.fragments.estatesList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import remy.pouzet.realestatemanager2.R;
import remy.pouzet.realestatemanager2.datas.models.Estate;

/**
 * Created by Remy Pouzet on 10/11/2020.
 */
public class EstatesListAdapter extends RecyclerView.Adapter<EstatesListViewHolder> {
	
	// FOR DATA
	private final List<Estate> estatesList;
	
	// CONSTRUCTOR
	public EstatesListAdapter(List<Estate> estatesList) {
		this.estatesList = estatesList;
	}
	
	@NonNull
	@Override
	public EstatesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
	                                                int viewType) {
		// CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
		Context        context  = parent.getContext();
		LayoutInflater inflater = LayoutInflater.from(context);
		View           view     = inflater.inflate(R.layout.fragment_estates_list, parent, false);
		
		return new EstatesListViewHolder(view);
		
	}
	
	// UPDATE VIEW HOLDER WITH A GITHUBUSER
	@Override
	public void onBindViewHolder(@NonNull EstatesListViewHolder viewHolder,
	                             int position) {
		viewHolder.updateEstates(this.estatesList.get(position));
		
	}
	
	// RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
	@Override
	public int getItemCount() {
		return this.estatesList.size();
	}
}
