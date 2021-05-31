package remy.pouzet.realestatemanager2.views.fragments.estateslist;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.List;

import remy.pouzet.realestatemanager2.databinding.FragmentEstatesListBinding;
import remy.pouzet.realestatemanager2.datas.models.Estate;
import remy.pouzet.realestatemanager2.datas.models.Request;
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
    
    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////
    
    public  Request              request;
    public  String               jsonRequest;
    public  SharedPreferences    sharedPreferences;
    private RecyclerView         recyclerView;
    private EstatesListViewModel estatesListViewModel;
    private EstatesListAdapter   estatesListAdapter = new EstatesListAdapter();
    
    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentEstatesListBinding localFragmentEstatesListBinding = FragmentEstatesListBinding.inflate(
                inflater,
                container,
                false);
        recyclerView = localFragmentEstatesListBinding.fragmentMainRecyclerView;
        configureViewModel();
        configureRecyclerView();
        updateUI();
        return localFragmentEstatesListBinding.getRoot();
    }
    
    @Override
    public View provideYourFragmentView(LayoutInflater inflater,
                                        ViewGroup parent,
                                        Bundle savedInstanceState) {
        return null;
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PRIVATE METHODS
    ///////////////////////////////////////////////////////////////////////////
    
    private void updateUI() {
        if (getArguments() == null) {
            estatesListViewModel.observeAllEstates()
                                .observe(getViewLifecycleOwner(), this::updateList);
        } else {
            jsonRequest = getArguments().getString("request");
            request     = new Gson().fromJson(jsonRequest, Request.class);
    
            estatesListViewModel.searchEstate(requireContext(), request)
                                .observe(getViewLifecycleOwner(), this::updateList);
        }
    }
    
    private void updateList(List<Estate> estatesList) {
        if (estatesList != null) {
            estatesListAdapter.setData(estatesList);
        }
    }
    
    private void configureViewModel() {
        estatesListViewModel = new ViewModelProvider(this).get(EstatesListViewModel.class);
    }

    private void configureRecyclerView() {
        this.estatesListAdapter = new EstatesListAdapter();
        this.recyclerView.setAdapter(this.estatesListAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
    }
}