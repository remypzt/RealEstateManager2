package remy.pouzet.realestatemanager2.views.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import remy.pouzet.realestatemanager2.R;
import remy.pouzet.realestatemanager2.databinding.ActivityMainBinding;
import remy.pouzet.realestatemanager2.datas.models.ListEvent;
import remy.pouzet.realestatemanager2.views.fragments.DetailsFragment;
import remy.pouzet.realestatemanager2.views.fragments.FormFragment;
import remy.pouzet.realestatemanager2.views.fragments.SearchFragment;
import remy.pouzet.realestatemanager2.views.fragments.estateslist.EstatesListFragment;
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
// ------------------ Miscellaneous ------------------- //+
//------------------------------------------------------//
// ----------------- Navigation, Menu, UI ------------- //
//------------------------------------------------------//

public class MainActivity extends AppCompatActivity {
	//------------------------------------------------------//
	// ------------------   Variables   ------------------- //
	//------------------------------------------------------//
	private AppBarConfiguration mAppBarConfiguration;
	
	public long            id;
	public boolean         listHadBeenClick = false;
	public Bundle          bundle           = new Bundle();
	public DetailsFragment detailsFragment;
	public NavHostFragment navHostFragment;
	FrameLayout firstFrame;
	FrameLayout secondFrame;
	
	private int                 navigateToNavSearch;
	private EstatesListFragment estatesListFragment;
	
	//------------------------------------------------------//
	// ------------------    Binding    ------------------- //
	//------------------------------------------------------//
	private ActivityMainBinding mActivityMainBinding;
	private RecyclerView        recyclerView;
	
	//------------------------------------------------------//
	// ------------------   LifeCycle   ------------------- //
	//------------------------------------------------------//
	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		bindingManagement();
		menuManagement();
		uiManagement();
		
	}
	
	public void bindingManagement() {
		mActivityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
		firstFrame           = mActivityMainBinding.mainToolbar.contentMainConstraintLayout.firstFrameFragment;
		secondFrame          = mActivityMainBinding.mainToolbar.contentMainConstraintLayout.secondFrameFragment;
		
	}
	
	private void uiManagement() {
		configureAndShowListFragment();
		if (isTablet(this)) {
			configureAndShowDetailsFragment();
			backStackManagement();
		}
	}
	
	private void configureAndShowDetailsFragment() {
		if (!listHadBeenClick) {
			id = 1;
		}
		bundle.putLong("id", id);
		
		detailsFragment = new DetailsFragment();
		FragmentTransaction t = getSupportFragmentManager().beginTransaction();
		detailsFragment.setArguments(bundle);
		t.replace(R.id.second_frame_fragment, detailsFragment, "VISIBLE_FRAGMENT");
		t.commit();
	}

//
//		recyclerView = findViewById(R.id.fragment_main_recycler_view);
//		EstatesListAdapter adapter = new EstatesListAdapter();
//		recyclerView.setAdapter(adapter);
//
//		//Call click method
//		adapter.setListener(EstatesListAdapter.EstatesListViewHolder.ItemClickListener) {
//			@Override
//			public void onItemClick(int position) {
//				Toast.makeText(MainActivity.this, position + "===", Toast.LENGTH_SHORT).show();
//			}
//		});
	
	//------------------------------------------------------//
	// ----------------- Navigation, Menu ----------------- //
	//------------------------------------------------------//
	
	public void menuManagement() {
		setContentView(mActivityMainBinding.getRoot());
		setSupportActionBar(mActivityMainBinding.mainToolbar.toolbar);
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
		mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_estates_list,
		                                                       R.id.nav_loan_simulator,
		                                                       R.id.nav_map_fragment).setOpenableLayout(
				mActivityMainBinding.drawerLayout).build();
	}
	
	@Override public boolean onSupportNavigateUp() {
		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
		return NavigationUI.navigateUp(navController,
		                               mAppBarConfiguration) || super.onSupportNavigateUp();
	}
	
	@Override public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.toolbar_menu, menu);
		MenuItem searchActionButton = menu.findItem(R.id.action_search_button);
		MenuItem modifyActionbutton = menu.findItem(R.id.action_modify_button);
		navigationManagement(searchActionButton, modifyActionbutton);
		return true;
	}
	
	private void backStackManagement() {
		getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
			@Override public void onBackStackChanged() {
				onBackManagement();
			}
		});
	}
	
	private void onBackManagement() {
		Fragment currentBackStackFragment = getSupportFragmentManager().findFragmentByTag(
				"VISIBLE_FRAGMENT");
		if (currentBackStackFragment instanceof DetailsFragment) {
			firstFrame.setVisibility(View.VISIBLE);
			mActivityMainBinding.mainToolbar.fab.setVisibility(View.VISIBLE);
		} else {
			mActivityMainBinding.mainToolbar.fab.setVisibility(View.GONE);
			firstFrame.setVisibility(View.GONE);
		}
		
	}

//------------------------------------------------------//
// ------------------   Functions   ------------------- //
//------------------------------------------------------//
	
	@Override protected void onStart() {
		if (isTablet(this)) {
			if (!EventBus.getDefault().isRegistered(this)) {
				EventBus.getDefault().register(this);
			}
		}
		super.onStart();
	}
	
	private void configureAndShowListFragment() {
		if (!isTablet(this)) {
			// B - Create new main fragment
			estatesListFragment = new EstatesListFragment();
			
			// C - Add it to FrameLayout container
			getSupportFragmentManager().beginTransaction()
			                           .add(R.id.estates_list_constraint_layout,
			                                estatesListFragment)
			                           .commit();
		} else {
			estatesListFragment = new EstatesListFragment();
			getSupportFragmentManager().beginTransaction()
			                           .add(R.id.first_frame_fragment, estatesListFragment)
			                           .commit();
		}
	}
	
	public boolean isTablet(Context context) {
		boolean xlarge = ((context.getResources()
		                          .getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE);
		boolean large = ((context.getResources()
		                         .getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
		return (xlarge || large);
	}
	
	@Override protected void onDestroy() {
		if (isTablet(this)) {
			EventBus.getDefault().unregister(this);
		}
		super.onDestroy();
	}
	
	@Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		if (isTablet(this)) {
			switch (item.getItemId()) {
				case R.id.action_search_button:
					SearchFragment searchFragment = new SearchFragment();
					getSupportFragmentManager().beginTransaction()
					                           .replace(R.id.second_frame_fragment,
					                                    searchFragment,
					                                    "VISIBLE_FRAGMENT")
					                           .addToBackStack(null)
					                           .commit();
					return true;
				case R.id.action_modify_button:
					id = Long.parseLong(getSupportFragmentManager().findFragmentByTag(
							"VISIBLE_FRAGMENT").getArguments().get("id").toString());
					bundle.putLong("id", id);
					FormFragment formFragment = new FormFragment();
					formFragment.setArguments(bundle);
					getSupportFragmentManager().beginTransaction()
					                           .addToBackStack(null)
					                           .replace(R.id.second_frame_fragment,
					                                    formFragment,
					                                    "VISIBLE_FRAGMENT")
					                           .commit();
					return true;
				
				default:
					return super.onOptionsItemSelected(item);
			}
		} else {
			NavController navController = Navigation.findNavController(this,
			                                                           R.id.nav_host_fragment);
			if (navController.getCurrentDestination().getId() == R.id.nav_estates_list) {
				navigateToNavSearch = R.id.action_nav_estates_list_to_nav_search;
			} else if (navController.getCurrentDestination().getId() == R.id.nav_details) {
				navigateToNavSearch = R.id.action_nav_details_to_action_search_button;
			}
			switch (item.getItemId()) {
				case R.id.action_search_button:
					Navigation.findNavController(this, R.id.nav_host_fragment)
					          .navigate(navigateToNavSearch);
					return true;
				case R.id.action_modify_button:
					navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(
							R.id.nav_host_fragment);
					
					detailsFragment = (DetailsFragment) navHostFragment.getChildFragmentManager()
					                                                   .getFragments()
					                                                   .get(0);
					id = detailsFragment.id;
					bundle.putLong("id", id);
					
					Navigation.findNavController(this, R.id.nav_host_fragment)
					          .navigate(R.id.action_nav_details_to_nav_form, bundle);
					
					return true;
				default:
					return super.onOptionsItemSelected(item);
			}
		}
	}
	
	public void navigationManagement(MenuItem searchActionButton, MenuItem modifyActionButton) {
		if (isTablet(this)) {
			//TODO manage navigationdrawer
			mActivityMainBinding.mainToolbar.fab.setOnClickListener(new View.OnClickListener() {
				@Override public void onClick(View v) {
					mActivityMainBinding.mainToolbar.fab.setVisibility(View.GONE);
					FormFragment formFragment = new FormFragment();
					getSupportFragmentManager().beginTransaction()
					                           .replace(R.id.second_frame_fragment,
					                                    formFragment,
					                                    "VISIBLE_FRAGMENT")
					                           .commit();
					
				}
			});
			
		} else {
			NavController navController = Navigation.findNavController(this,
			                                                           R.id.nav_host_fragment);
			NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
			NavigationUI.setupWithNavController(mActivityMainBinding.navView, navController);
			
			/* Set items visibility depends navigation position*/
			navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
				if (destination.getId() != R.id.nav_estates_list) {
					mActivityMainBinding.mainToolbar.fab.setVisibility(View.GONE);
				} else {
					mActivityMainBinding.mainToolbar.fab.setVisibility(View.VISIBLE);
				}
				searchActionButton.setVisible(destination.getId() == R.id.nav_details || destination
						                                                                         .getId() == R.id.nav_estates_list);
				modifyActionButton.setVisible(destination.getId() == R.id.nav_details);
			});
			
			Navigation.setViewNavController(mActivityMainBinding.mainToolbar.fab,
			                                Navigation.findNavController(this,
			                                                             R.id.nav_host_fragment));
			mActivityMainBinding.mainToolbar.fab.setOnClickListener((Navigation.createNavigateOnClickListener(
					R.id.action_nav_estates_list_to_nav_form,
					null)));
		}
	}
	
	@Subscribe public void updateDetailsRegardToClickListListener(ListEvent listEvent) {
		if (isTablet(this)) {
			listHadBeenClick = true;
			id               = listEvent.getId();
			configureAndShowDetailsFragment();
		}
	}
	
	@Override public void onBackPressed() {
		Fragment currentBackStackFragment = getSupportFragmentManager().findFragmentByTag(
				"VISIBLE_FRAGMENT");
		if (currentBackStackFragment instanceof FormFragment && currentBackStackFragment.getArguments() == null) {
			configureAndShowDetailsFragment();
			mActivityMainBinding.mainToolbar.fab.setVisibility(View.VISIBLE);
		} else {
			super.onBackPressed();
		}
	}
}

//	public String getCurrentFragment() {
//		return getSupportFragmentManager().findFragmentById(R.id.second_frame_fragment)
//		                                  .getClass()
//		                                  .getSimpleName();
//	}
	


	
