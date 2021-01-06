package remy.pouzet.realestatemanager2.views.bases;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE;

/**
 * Created by Remy Pouzet on 27/11/2020.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanseState) {
        return provideYourFragmentView(inflater, parent, savedInstanseState);
    }

    public abstract View provideYourFragmentView(LayoutInflater inflater,
                                                 ViewGroup parent,
                                                 Bundle savedInstanceState);
    
    public void showIndefiniteSnackBar(View view, String message) {
        if (message == null || message.trim().equals("")) {
            message = "Please enter text to show";
        }
        Snackbar
                .make(view, message, LENGTH_INDEFINITE)
                .setAction("CLOSE", view1 -> {
                    //Do nothing because it's close automatically
                })
                .setActionTextColor(ContextCompat.getColor(requireContext(),
                        android.R.color.holo_red_light))
                .show();
    }

    public void showLongSnackBar(View view, String message) {
        if (message == null || message.trim().equals("")) {
            message = "Please enter text to show";
        }
        Snackbar
                .make(view, message, BaseTransientBottomBar.LENGTH_LONG)
                .setAction("CLOSE", view1 -> {
                    //Do nothing because it's close automatically
                })
                .setActionTextColor(ContextCompat.getColor(requireContext(),
                        android.R.color.holo_red_light))
                .show();
    }

}