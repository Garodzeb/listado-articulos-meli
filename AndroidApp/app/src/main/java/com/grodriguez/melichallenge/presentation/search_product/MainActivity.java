package com.grodriguez.melichallenge.presentation.search_product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.grodriguez.melichallenge.R;
import com.grodriguez.melichallenge.application.AppDependenciesContainer;
import com.grodriguez.melichallenge.databinding.ActivityMainBinding;
import com.grodriguez.melichallenge.framework.room.AppRoomDatabase;
import com.grodriguez.melichallenge.framework.utils.AppConstants;
import com.grodriguez.melichallenge.framework.utils.AppUtils;
import com.grodriguez.melichallenge.presentation.ViewModelsFactory;
import com.grodriguez.melichallenge.presentation.initial.InitialActivity;
import com.grodriguez.melichallenge.presentation.product_detail.ProductDetailActivity;
import com.grodriguez.melichallenge.presentation.search_product.filters.SearchFilterFragment;
import com.grodriguez.melichallenge.presentation.ui.UIState;
import com.grodriguez.melisearchcore.model.domain.search.SearchItem;
import com.grodriguez.melisearchcore.model.domain.search.SearchQuery;
import com.grodriguez.melisearchcore.model.domain.search.SearchResult;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements SearchResultsAdapter.IItemSelectedListener {

    SearchResultsAdapter searchResultsAdapter;
    MainViewModel mainViewModel;
    ActivityMainBinding binding;

    // region Activity Lifecycle Events

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            Context context = getApplicationContext();
            AppDependenciesContainer appDependencies = AppDependenciesContainer.getInstance(context, AppRoomDatabase.getInstance(context));
            ViewModelsFactory factory = new ViewModelsFactory(appDependencies.getItemsRepository(), appDependencies.getSiteRepository());

            mainViewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);

            initializeUI();
            initializeObservers();
        } catch (Exception ex) {
            // Finaliza la actividad si ocurre un error en la inicialización
            AppUtils.logError(ex);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            SearchQuery query = mainViewModel.getCurrentSearchQuery().getValue();

            if (query == null || query.isEmpty())
                mainViewModel.recoverSearchQuery();
            else {
                mainViewModel.searchItem(query);
            }
        } catch (Exception ex) {
            showErrorScreen(ex);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (isFinishing())
            mainViewModel.dispose();
    }


    @Override
    public void onItemSelected(SearchItem item) {

        AppUtils.removeCustomPreference(getApplicationContext(), AppConstants.CURRENT_ITEM_ID_SHARED_PREFERENCE_KEY);
        AppUtils.saveCustomStringPreference(getApplicationContext(), AppConstants.CURRENT_ITEM_ID_SHARED_PREFERENCE_KEY, item.getId());

        Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    // endregion

    // region Initialization Methods

    private void initializeUI() {
        initializeRecyclerView();

        binding.txtActivityMainSearchQuery.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                String searchValue = binding.txtActivityMainSearchQuery.getText().toString();

                if (!searchValue.isEmpty())
                    mainViewModel.searchItem(searchValue);

                return false;
            }
        });

        binding.btnActivityMainSearchFilters.setOnClickListener(view -> {
            toggleFiltersFragment();
        });
    }

    private void initializeRecyclerView() {
        RecyclerView rv = binding.rvActivityMainSearchResultsList;

        searchResultsAdapter = new SearchResultsAdapter(this);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(searchResultsAdapter);
    }

    private void initializeObservers() {
        mainViewModel.getUIState().observe(this, this::updateUIState);
        mainViewModel.getSearchResult().observe(this, this::showSearchResults);
        mainViewModel.getCurrentSearchQuery().observe(this, this::loadCurrentSearchQuery);
        mainViewModel.getFiltersApplied().observe(this, this::updateFilters);
    }

    // endregion

    // region private Methods

    private void updateFilters(Boolean filtersUpdated) {
        binding.fcvActivityMainSearchFilters.setVisibility(View.GONE);

        if (filtersUpdated)
            mainViewModel.searchItem(binding.txtActivityMainSearchQuery.getText().toString());
    }

    private void toggleFiltersFragment() {
        if (binding.fcvActivityMainSearchFilters.getVisibility() == View.GONE) {
            mainViewModel.loadAvailableFilters();
            binding.fcvActivityMainSearchFilters.setVisibility(View.VISIBLE);
        }
        else
            binding.fcvActivityMainSearchFilters.setVisibility(View.GONE);
    }

    private void updateUIState(UIState status) {
        switch (status.getCurrentStatus()) {
            case UI_IDLE: {
                binding.fragActivityMainGenericErrorMessage.getRoot().setVisibility(View.GONE);
                binding.pbActivityMainLoading.setVisibility(View.GONE);
                break;
            }
            case UI_ON_BACKGROUND_WORK: {
                binding.fragActivityMainGenericErrorMessage.getRoot().setVisibility(View.GONE);
                binding.pbActivityMainLoading.setVisibility(View.VISIBLE);
                break;
            }
            case UI_ON_ERROR: {
                binding.pbActivityMainLoading.setVisibility(View.GONE);

                // Valida si el mensaje de error ocurrió debido a una excepción no controlada
                if (status.getError() != null) {
                    showErrorScreen(status.getError());
                }
                // Valida si el mensaje de error es un mensaje controlado
                else if (!status.getMessage().isEmpty()) {
                    // En el caso de que sea un mensaje controlado muestra un toast en pantalla
                    Toast.makeText(getApplicationContext(), status.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    // Carga en la interfaz el valor de la consulta realizada, solo se llama cuando se tiene que
    // recuperar una consulta cuando se destruyo el Activity
    private void loadCurrentSearchQuery(SearchQuery query) {
        // Valida si el campo de búsqueda esta vacío y en caso afirmativo carga el valor original
        // de la query guardada en memoria
        if (binding.txtActivityMainSearchQuery.getText().toString().isEmpty())
            binding.txtActivityMainSearchQuery.setText(query.getQuery());
    }

    // Muestra los resultados de la consulta en pantalla
    private void showSearchResults(SearchResult result) {
        if (result.getResults().size() > 0) {
            binding.trActivityMainSearchResultsBar.setVisibility(View.VISIBLE);
            binding.rvActivityMainSearchResultsList.setVisibility(View.VISIBLE);
            binding.lblActivityMainNoSearchResults.setVisibility(View.GONE);

            int totalResults = result.getPaging().getTotal();
            String formattedQty;

            // Modifica el mensaje a mostrar en base a si se encontraron más resultados que el
            // tope permitido para mostrar
            if (totalResults < AppConstants.MAX_SEARCH_RESULTS_DISPLAY_QTY)
                formattedQty = NumberFormat.getInstance().format(result.getPaging().getTotal());
            else
                formattedQty = NumberFormat.getInstance().format(AppConstants.MAX_SEARCH_RESULTS_DISPLAY_QTY);

            String searchResultsQtyLabel = String.format("%s resultados", formattedQty);
            binding.lblActivityMainSearchResultQty.setText(searchResultsQtyLabel);

            String appliedFiltersLabel = String.format("Filtrar (%s)", result.getFilters().size());
            binding.btnActivityMainSearchFilters.setText(appliedFiltersLabel);

            searchResultsAdapter.updateItems(result.getResults());
        } else {
            binding.rvActivityMainSearchResultsList.setVisibility(View.GONE);
            binding.lblActivityMainNoSearchResults.setVisibility(View.VISIBLE);
        }

    }

    // Muestra una pantalla de error y logea el error en la consola
    private void showErrorScreen(Throwable error) {
        AppUtils.logError(error);
        binding.fragActivityMainGenericErrorMessage.getRoot().setVisibility(View.VISIBLE);
    }

    // endregion

}// End Class