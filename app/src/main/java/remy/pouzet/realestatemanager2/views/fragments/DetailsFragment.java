package remy.pouzet.realestatemanager2.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import remy.pouzet.realestatemanager2.viewmodels.DetailsViewModel;

public class DetailsFragment extends Fragment {
	
	private DetailsViewModel                                                  mDetailsViewModel;
	private remy.pouzet.realestatemanager2.databinding.FragmentDetailsBinding mFragmentDetailsBinding;
	
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         ViewGroup container,
	                         Bundle savedInstanceState) {
		mFragmentDetailsBinding = remy.pouzet.realestatemanager2.databinding.FragmentDetailsBinding.inflate(inflater, container, false);
		mDetailsViewModel       = ViewModelProviders
				.of(this)
				.get(DetailsViewModel.class);
		
		final TextView textView = mFragmentDetailsBinding.textGallery;
		mDetailsViewModel
				.getText()
				.observe(getViewLifecycleOwner(), new Observer<String>() {
					@Override
					public void onChanged(@Nullable String s) {
						textView.setText(s);
					}
				});
		
		return mFragmentDetailsBinding.getRoot();
	}
}