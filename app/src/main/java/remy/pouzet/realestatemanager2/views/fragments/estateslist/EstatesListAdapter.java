package remy.pouzet.realestatemanager2.views.fragments.estateslist;

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
    private final List<Estate> estatesList = new ArrayList<>();

    @NonNull
    @Override
    public EstatesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.content_items_of_fragment_estate_list, parent, false);
        return new EstatesListViewHolder(view);
    }

    // UPDATE VIEW HOLDER
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

    public void setData(List<Estate> estates) {
        this.estatesList.clear();
        this.estatesList.addAll(estates);
        notifyDataSetChanged();
    }
}
