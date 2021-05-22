package remy.pouzet.realestatemanager2.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.sqlite.db.SupportSQLiteQuery;

import remy.pouzet.realestatemanager2.R;
import remy.pouzet.realestatemanager2.databinding.FragmentSearchBinding;
import remy.pouzet.realestatemanager2.datas.models.Request;
import remy.pouzet.realestatemanager2.viewmodels.SearchViewModel;
import remy.pouzet.realestatemanager2.views.bases.BaseFragment;

public class SearchFragment extends BaseFragment {
	
	//------------------------------------------------------//
	// ------------------   Variables   ------------------- //
	// ------------------------------------------------------//
	public SearchViewModel       searchViewModel;
	public Request               request;
	public FragmentSearchBinding fragmentSearchBinding;
	
	public String cityValue, minimumPriceValue, maximumPriceValue, minimumSurfaceValue, maximumSurfaceValue, minimumRoomsNumberValue, maximumRoomsNumberValue;
	
	//------------------------------------------------------//
	// ------------------   Binding   ------------------- //
	//------------------------------------------------------//
	public EditText cityEditText, minimumPriceEditText, maximumPriceEditText, minimumSurfaceEditText, maximumSurfaceEditText, minimumRoomsNumberEditText, maximumRoomsNumberEditText;
	public ImageButton searchButton;
	
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		fragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false);
		viewBindingManagement();
		return inflater.inflate(R.layout.fragment_search, container, false);
	}
	
	//------------------------------------------------------//
	// ------------------   LifeCycle   ------------------- //
	//------------------------------------------------------//
	
	private void viewBindingManagement() {
		cityEditText               = fragmentSearchBinding.valueOfEstateCityFragmentSearch;
		minimumPriceEditText       = fragmentSearchBinding.valueOfEstateMinimumPriceFragmentSearch;
		maximumPriceEditText       = fragmentSearchBinding.valueOfEstateMaximumPriceFragmentSearch;
		minimumSurfaceEditText     = fragmentSearchBinding.valueOfEstateMinimumSurfaceFragmentSearch;
		maximumSurfaceEditText     = fragmentSearchBinding.valueOfEstateMaximumSurfaceFragmentSearch;
		minimumRoomsNumberEditText = fragmentSearchBinding.valueOfEstateMinimumRoomsNumberFragmentSearch;
		maximumRoomsNumberEditText = fragmentSearchBinding.valueOfEstateMaximumRoomsNumberFragmentSearch;
		searchButton               = fragmentSearchBinding.validateResearchButton;
		
	}
	
	@Override
	public View provideYourFragmentView(LayoutInflater inflater,
	                                    ViewGroup parent,
	                                    Bundle savedInstanceState) {
		return null;
	}
	
	@Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.configureViewModel();
		searchManagement();
	}
	
	//------------------------------------------------------//
	// ------------------   Functions   ------------------- //
	//------------------------------------------------------//
	
	private void configureViewModel() {
		searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
	}
	
	//------------------------------------------------------//
// ----------------- Navigation, Menu, UI ------------- //
//------------------------------------------------------//
	
	public void searchManagement() {
		
		searchButton.setOnClickListener(v -> {
			
			Toast.makeText(requireContext(), "test", Toast.LENGTH_LONG).show();
			
			cityValue               = cityEditText.getText().toString();
			minimumPriceValue       = minimumPriceEditText.getText().toString();
			maximumPriceValue       = maximumPriceEditText.getText().toString();
			minimumSurfaceValue     = minimumSurfaceEditText.getText().toString();
			maximumSurfaceValue     = maximumSurfaceEditText.getText().toString();
			minimumRoomsNumberValue = minimumRoomsNumberEditText.getText().toString();
			maximumRoomsNumberValue = maximumRoomsNumberEditText.getText().toString();
			
			request = new Request(cityValue,
			                      minimumPriceValue,
			                      maximumPriceValue,
			                      minimumSurfaceValue,
			                      maximumSurfaceValue,
			                      minimumRoomsNumberValue,
			                      maximumRoomsNumberValue);
			
			searchViewModel.searchEstate(requireContext(), (SupportSQLiteQuery) request);
		});
		
		cityEditText.setText("prepopulate city");
		cityValue               = cityEditText.getText().toString();
		minimumPriceValue       = minimumPriceEditText.getText().toString();
		maximumPriceValue       = maximumPriceEditText.getText().toString();
		minimumSurfaceValue     = minimumSurfaceEditText.getText().toString();
		maximumSurfaceValue     = maximumSurfaceEditText.getText().toString();
		minimumRoomsNumberValue = minimumRoomsNumberEditText.getText().toString();
		maximumRoomsNumberValue = maximumRoomsNumberEditText.getText().toString();
		
		request = new Request(cityValue,
		                      minimumPriceValue,
		                      maximumPriceValue,
		                      minimumSurfaceValue,
		                      maximumSurfaceValue,
		                      minimumRoomsNumberValue,
		                      maximumRoomsNumberValue);
		
		searchViewModel.searchEstate(requireContext(), (SupportSQLiteQuery) request);
		
	}
	
}