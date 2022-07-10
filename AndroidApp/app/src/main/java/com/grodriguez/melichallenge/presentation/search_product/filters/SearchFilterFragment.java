package com.grodriguez.melichallenge.presentation.search_product.filters;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grodriguez.melichallenge.application.AppDependenciesContainer;
import com.grodriguez.melichallenge.databinding.FragmentSearchFilterBinding;
import com.grodriguez.melichallenge.framework.room.AppRoomDatabase;
import com.grodriguez.melichallenge.framework.utils.AppUtils;
import com.grodriguez.melichallenge.presentation.ViewModelsFactory;
import com.grodriguez.melichallenge.presentation.search_product.MainViewModel;
import com.grodriguez.melisearchcore.model.domain.search.Filter;
import com.grodriguez.melisearchcore.model.domain.search.FilterValue;

public class SearchFilterFragment extends Fragment implements ISearchFiltersListener {

    SearchFiltersAdapter adapter;
    MainViewModel mainViewModel;
    FragmentSearchFilterBinding binding;

    public SearchFilterFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchFilterBinding.inflate(inflater);

        Context context = getContext();
        AppDependenciesContainer appDependencies = AppDependenciesContainer.getInstance(context, AppRoomDatabase.getInstance(context));
        ViewModelsFactory factory = new ViewModelsFactory(appDependencies.getItemsRepository(), appDependencies.getSiteRepository());

        mainViewModel = new ViewModelProvider(requireActivity(), factory).get(MainViewModel.class);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeUI();
    }

    // region Initialization Methods

    private void initializeUI() {
        initializeRecyclerView();
        initalizeObservers();

        binding.btnFragSearchFiltersCleanFilters.setOnClickListener(view -> {
            mainViewModel.clearFilters();
        });

        binding.btnFragSearchFiltersApplyFilters.setOnClickListener(view -> {
            mainViewModel.setFiltersApplied(true);
        });

        mainViewModel.loadAvailableFilters();
    }

    private void initializeRecyclerView() {
        try {
            RecyclerView rv = binding.rvFragSearchFilters;

            adapter = new SearchFiltersAdapter(this);

            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            rv.setAdapter(adapter);
        }
        catch (Exception ex) {
            AppUtils.logError(ex);
        }
    }

    private void initalizeObservers() {
        mainViewModel.getAvailableFilters().observe(requireActivity(), filters -> {
            try {
                if (filters != null)
                    // Actualiza los filtros mostrados en pantalla
                    adapter.updateFilters(filters);
            }
            catch (Exception ex) {
                AppUtils.logError(ex);
            }
        });
    }

    // endregion

    @Override
    public void filterSelected(Filter filter, FilterValue value) {
        mainViewModel.addFilterToQuery(filter, value);
    }

    @Override
    public void removeSelected(Filter filter, FilterValue value) {
        mainViewModel.removeFilter(filter, value);
    }


}// End class