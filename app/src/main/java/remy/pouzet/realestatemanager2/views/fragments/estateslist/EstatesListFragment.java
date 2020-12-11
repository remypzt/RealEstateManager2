package remy.pouzet.realestatemanager2.views.fragments.estateslist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import remy.pouzet.realestatemanager2.databinding.FragmentEstatesListBinding;
import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.viewmodels.EstatesListViewModel;
import remy.pouzet.realestatemanager2.views.bases.BaseFragment;

//------------------------------------------------------//
// ------------------    Binding    ------------------- //
//------------------------------------------------------//
// ------------------   Variables   ------------------- //
//------------------------------------------------------//
// ------------------   LifeCycle   ------------------- //
//------------------------------------------------------//
// ------------------   Functions   ------------------- //
//------------------------------------------------------//
// ------------------     Intent    ------------------- //
//------------------------------------------------------//
// ------------------   Callbacks   ------------------- //
//------------------------------------------------------//
// ------------------     Data      ------------------- //
//------------------------------------------------------//
// ------------------    Adapter    ------------------- //
//------------------------------------------------------//
// ------------------ Miscellaneous ------------------- //
//------------------------------------------------------//
// ----------------- Navigation, Menu, UI ------------- //
//------------------------------------------------------//

public class EstatesListFragment extends BaseFragment {
    //------------------------------------------------------//
    // ------------------    Binding    ------------------- //
    //------------------------------------------------------//

    //------------------------------------------------------//
    // ------------------   Variables   ------------------- //
    // ------------------------------------------------------//

    private RecyclerView recyclerView;
    private EstatesListViewModel estatesListViewModel;
    private EstatesListAdapter estatesListAdapter;
    private List<Estate> estatesList;
    //------------------------------------------------------//
    // ------------------   LifeCycle   ------------------- //
    //------------------------------------------------------//

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        remy.pouzet.realestatemanager2.databinding.FragmentEstatesListBinding localFragmentEstatesListBinding = FragmentEstatesListBinding
                .inflate(inflater, container, false);
        recyclerView = localFragmentEstatesListBinding.fragmentMainRecyclerView;

        this.configureViewModel();
        this.configureRecyclerView();
        getAllEstates();

        return localFragmentEstatesListBinding.getRoot();
    }

    @Override
    public View provideYourFragmentView(LayoutInflater inflater,
                                        ViewGroup parent, Bundle savedInstanceState) {
        return null;
    }

    //------------------------------------------------------//
    // ------------------   Functions   ------------------- //
    //------------------------------------------------------//

    private void configureViewModel() {
        estatesListViewModel = new ViewModelProvider(this).get(EstatesListViewModel.class);
    }

    private void configureRecyclerView() {
        this.estatesList = new ArrayList<>();
        this.estatesListAdapter = new EstatesListAdapter(this.estatesList);
        this.recyclerView.setAdapter(this.estatesListAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void getAllEstates() {
        estatesListViewModel.getAllEstates();
        estatesListViewModel.observeAllEstates().observe(getViewLifecycleOwner(), this::updateList);
    }

    // UPDATE UI
    //updateListOfArticles still in Fragments cause I must call the adapter and I cannot do it in viewmodel
    public void updateList(List<Estate> estatesList) {
        this.estatesList.clear();
        if (estatesList != null) {
            this.estatesList.addAll(estatesList);
            estatesListAdapter.notifyDataSetChanged();
        }
    }

}