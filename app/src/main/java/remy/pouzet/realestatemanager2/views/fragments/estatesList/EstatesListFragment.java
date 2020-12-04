package remy.pouzet.realestatemanager2.views.fragments.estatesList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import remy.pouzet.realestatemanager2.databinding.FragmentEstatesListBinding;
import remy.pouzet.realestatemanager2.datas.models.Estate;

public class EstatesListFragment extends Fragment {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private FragmentEstatesListBinding fragmentEstatesListBinding;

    private RecyclerView recyclerView;
    private EstatesListViewModel estatesListViewModel;
    private EstatesListAdapter estatesListAdapter;
    private List<Estate> estatesList;

    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentEstatesListBinding = FragmentEstatesListBinding.inflate(inflater, container, false);
        recyclerView = fragmentEstatesListBinding.fragmentMainRecyclerView;
        estatesListViewModel = new ViewModelProvider(this).get(EstatesListViewModel.class);

        configureRecyclerView();

        estatesListViewModel.getAllEstates();
        estatesListViewModel.observeAllEstates().observe(getViewLifecycleOwner(), this::updateList);

        return fragmentEstatesListBinding.getRoot();
    }


    ///////////////////////////////////////////////////////////////////////////
    // FUNCTIONS
    ///////////////////////////////////////////////////////////////////////////

    // UPDATE UI
    //updateListOfArticles still in Fragments cause I must call the adapter and I cannot do it in viewmodel
    public void updateList(List<Estate> estatesList) {
        this.estatesList.clear();
        if (estatesList != null) {
            this.estatesList.addAll(estatesList);
            estatesListAdapter.notifyDataSetChanged();
        }
    }

    //  - Configure RecyclerView, Adapter, LayoutManager & glue it together
    private void configureRecyclerView() {
        //  - Reset list
        this.estatesList = new ArrayList<>();
        //  - Create adapter passing the list of users
        this.estatesListAdapter = new EstatesListAdapter(this.estatesList);
        //  - Attach the adapter to the recyclerview to populate estates
        this.recyclerView.setAdapter(this.estatesListAdapter);
        // - Set layout manager to position the estates
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}