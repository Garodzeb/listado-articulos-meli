package com.grodriguez.melichallenge.presentation.search_product.filters;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grodriguez.melichallenge.R;
import com.grodriguez.melichallenge.application.AppDependenciesContainer;
import com.grodriguez.melichallenge.databinding.ActivityMainBinding;
import com.grodriguez.melichallenge.databinding.DialogFragmentSearchFiltersBinding;
import com.grodriguez.melichallenge.framework.room.AppRoomDatabase;
import com.grodriguez.melichallenge.presentation.ViewModelsFactory;
import com.grodriguez.melichallenge.presentation.search_product.MainViewModel;

public class SearchFiltersDialogFragment extends DialogFragment {

    SearchFiltersAdapter adapter;
    MainViewModel mainViewModel;
    DialogFragmentSearchFiltersBinding binding;

    public SearchFiltersDialogFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DialogFragmentSearchFiltersBinding.inflate(getLayoutInflater(), container, false);

        Context context = getContext();
        AppDependenciesContainer appDependencies = AppDependenciesContainer.getInstance(context, AppRoomDatabase.getInstance(context));
        ViewModelsFactory factory = new ViewModelsFactory(appDependencies.getItemsRepository(), appDependencies.getSiteRepository());

        mainViewModel = new ViewModelProvider(requireActivity(), factory).get(MainViewModel.class);

        initializeUI();

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    // region Initialization Methods

    private void initializeUI() {

    }

    // endregion

}// End Class