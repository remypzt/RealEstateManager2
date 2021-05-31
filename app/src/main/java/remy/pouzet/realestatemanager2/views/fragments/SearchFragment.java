package remy.pouzet.realestatemanager2.views.fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.gson.Gson;

import remy.pouzet.realestatemanager2.R;
import remy.pouzet.realestatemanager2.databinding.FragmentSearchBinding;
import remy.pouzet.realestatemanager2.datas.models.Request;
import remy.pouzet.realestatemanager2.viewmodels.SearchViewModel;
import remy.pouzet.realestatemanager2.views.bases.BaseFragment;
import remy.pouzet.realestatemanager2.views.fragments.estateslist.EstatesListFragment;

public class SearchFragment extends BaseFragment {
	
	//------------------------------------------------------//
	// ------------------   Variables   ------------------- //
	// ------------------------------------------------------//
	public SearchViewModel       searchViewModel;
	public Request               request;
	public FragmentSearchBinding fragmentSearchBinding;
	public Bundle                bundle = new Bundle();
	public String                cityValue, minimumPriceValue, maximumPriceValue, minimumSurfaceValue, maximumSurfaceValue, minimumRoomsNumberValue, maximumRoomsNumberValue;
	
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
		return fragmentSearchBinding.getRoot();
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
			
			if (isTablet(requireContext())) {
				EstatesListFragment estatesListFragment = new EstatesListFragment();
				estatesListFragment.setArguments(saveRequest(request));
				getActivity().getSupportFragmentManager()
				             .beginTransaction()
				             .replace(R.id.second_frame_fragment,
				                      estatesListFragment,
				                      "VISIBLE_FRAGMENT")
				             .addToBackStack(null)
				             .commit();
			} else {
				Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
				          .navigate(R.id.action_action_search_button_to_nav_estates_list,
				                    saveRequest(request));
			}
		});
	}
	
	public Bundle saveRequest(Request request) {
		Bundle bundle = new Bundle();
		bundle.putSerializable("request", new Gson().toJson(request));
		return bundle;
	}
	
	public boolean isTablet(Context context) {
		boolean xlarge = ((context.getResources()
		                          .getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE);
		boolean large = ((context.getResources()
		                         .getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
		return (xlarge || large);
	}
}