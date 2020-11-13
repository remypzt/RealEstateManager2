package remy.pouzet.realestatemanager2.views.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import remy.pouzet.realestatemanager2.R;
import remy.pouzet.realestatemanager2.databinding.ActivityMainBinding;
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

public class MainActivity extends AppCompatActivity {
	//------------------------------------------------------//
	// ------------------   Variables   ------------------- //
	//------------------------------------------------------//
	private AppBarConfiguration mAppBarConfiguration;
	//------------------------------------------------------//
	// ------------------    Binding    ------------------- //
	//------------------------------------------------------//
	private ActivityMainBinding mActivityMainBinding;
	
	//------------------------------------------------------//
	// ------------------   LifeCycle   ------------------- //
	//------------------------------------------------------//
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
		menuManagement();
	}
	
	@Override
	public boolean onSupportNavigateUp() {
		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
		return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
	}
	
	public void menuManagement() {
		setContentView(mActivityMainBinding.getRoot());
		setSupportActionBar(mActivityMainBinding.mainToolbar.toolbar);
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
		mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_estates_list, R.id.nav_loan_simulator)
				.setDrawerLayout(mActivityMainBinding.drawerLayout)
				.build();
	}
	
	//------------------------------------------------------//
	// ----------------- Navigation, Menu, UI ------------- //
	//------------------------------------------------------//
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.toolbar_menu, menu);
		MenuItem searchActionButton = menu.findItem(R.id.action_search);
		MenuItem modifyActionbutton = menu.findItem(R.id.action_modify);
		navigationManagement(searchActionButton, modifyActionbutton);
		return true;
	}
	
	public void navigationManagement(MenuItem searchActionButton,
	                                 MenuItem modifyActionButton) {
		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
		NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
		NavigationUI.setupWithNavController(mActivityMainBinding.navView, navController);
		navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
			@Override
			public void onDestinationChanged(@NonNull NavController controller,
			                                 @NonNull NavDestination destination,
			                                 @Nullable Bundle arguments) {
				if (destination.getId() != R.id.nav_estates_list) {
					mActivityMainBinding.mainToolbar.fab.setVisibility(View.GONE);
				}
				if (destination.getId() == R.id.nav_details || destination.getId() == R.id.nav_estates_list) {
					searchActionButton.setVisible(true);
				}
				if (destination.getId() != R.id.nav_details) {
					modifyActionButton.setVisible(false);
				}
			}
		});
		
		Navigation.setViewNavController(mActivityMainBinding.mainToolbar.fab, Navigation.findNavController(this, R.id.nav_host_fragment));
		mActivityMainBinding.mainToolbar.fab.setOnClickListener((Navigation.createNavigateOnClickListener(R.id.action_nav_estates_list_to_nav_form, null)));
		
	}
}