package remy.pouzet.realestatemanager2.views.Bases;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

/**
 * Created by Remy Pouzet on 27/11/2020.
 */
public abstract class BaseFragment extends Fragment {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public View onCreateView(LayoutInflater inflater,
	                         ViewGroup parent,
	                         Bundle savedInstanseState) {
		View view = provideYourFragmentView(inflater, parent, savedInstanseState);
		return view;
	}
	
	public abstract View provideYourFragmentView(LayoutInflater inflater,
	                                             ViewGroup parent,
	                                             Bundle savedInstanceState);
	
	public void ShowSnackBar(View view,
	                         String message) {
		if (message == null || message
				.trim()
				.equals("")) {
			message = "Please enter text to show";
		}
		Snackbar
				.make(view, message, Snackbar.LENGTH_INDEFINITE)
				.setAction("CLOSE", new View.OnClickListener() {
					@Override
					public void onClick(View view) {
					}
				})
				.setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
				.show();
	}
	
}