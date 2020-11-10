package remy.pouzet.realestatemanager2.views.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.snackbar.Snackbar;

import remy.pouzet.realestatemanager2.R;
import remy.pouzet.realestatemanager2.databinding.ActivityMainBinding;
import remy.pouzet.realestatemanager2.databinding.AppBarMainBinding;

//------------------------------------------------------//
// ------------------    Binding    ------------------- //
// ------------------   Variables   ------------------- //
// ------------------   LifeCycle   ------------------- //
// ------------------   Functions   ------------------- //
// ------------------     Intent    ------------------- //
// ------------------   Callbacks   ------------------- //
// ------------------     Data      ------------------- //
// ------------------    Adapter    ------------------- //
// ------------------ Miscellaneous ------------------- //
// ----------------- Navigation, Menu, UI ------------- //
//------------------------------------------------------//
public class MainActivity extends AppCompatActivity {
	
	// ------------------   Variables   ------------------- //
	private AppBarConfiguration mAppBarConfiguration;
	
	// ------------------    Binding    ------------------- //
	private ActivityMainBinding mActivityMainBinding;
	private AppBarMainBinding   mAppBarMainBinding;
	
	// ------------------   LifeCycle   ------------------- //
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
		
		setContentView(mActivityMainBinding.getRoot());
		setSupportActionBar(mActivityMainBinding.mainToolbar.toolbar);
		
		mActivityMainBinding.mainToolbar.fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar
						.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null)
						.show();
			}
		});
		
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
		mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_loan_simulator)
				.setDrawerLayout(mActivityMainBinding.drawerLayout)
				.build();
		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
		NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
		NavigationUI.setupWithNavController(mActivityMainBinding.navView, navController);
	}
	
	// ----------------- Navigation, Menu, UI ------------- //
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.toolbar_menu, menu);
		return true;
	}
	
	@Override
	public boolean onSupportNavigateUp() {
		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
		return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
	}
}