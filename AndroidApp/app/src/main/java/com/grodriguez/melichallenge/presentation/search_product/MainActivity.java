package com.grodriguez.melichallenge.presentation.search_product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.grodriguez.melichallenge.application.AppDependenciesContainer;
import com.grodriguez.melichallenge.databinding.ActivityMainBinding;
import com.grodriguez.melichallenge.framework.room.AppRoomDatabase;
import com.grodriguez.melichallenge.framework.utils.AppConstants;
import com.grodriguez.melichallenge.framework.utils.AppUtils;
import com.grodriguez.melichallenge.presentation.ViewModelsFactory;
import com.grodriguez.melichallenge.presentation.search_product.filters.SearchFiltersDialogFragment;
import com.grodriguez.melichallenge.presentation.ui.UIState;
import com.grodriguez.melisearchcore.model.domain.search.SearchQuery;
import com.grodriguez.melisearchcore.model.domain.search.SearchResult;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    SearchResultsAdapter searchResultsAdapter;
    MainViewModel mainViewModel;
    ActivityMainBinding binding;

    // region Activity Lifecycle Events

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Context context = getApplicationContext();
        AppDependenciesContainer appDependencies = AppDependenciesContainer.getInstance(context, AppRoomDatabase.getInstance(context));
        ViewModelsFactory factory = new ViewModelsFactory(appDependencies.getItemsRepository(), appDependencies.getSiteRepository());

        mainViewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);

        initializeUI();
        initializeObservers();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SearchQuery query = mainViewModel.getCurrentSearchQuery().getValue();

        if (query == null || query.isEmpty())
            mainViewModel.recoverSearchQuery();
        else {
            mainViewModel.searchItem(query);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (isFinishing())
            mainViewModel.dispose();
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
            showFiltersFragment();
        });
    }

    private void initializeRecyclerView() {
        RecyclerView rv = binding.rvActivityMainSearchResultsList;

        searchResultsAdapter = new SearchResultsAdapter();

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(searchResultsAdapter);
    }

    private void initializeObservers() {
        mainViewModel.getUIState().observe(this, this::updateUIState);
        mainViewModel.getSearchResult().observe(this, this::showSearchResults);
        mainViewModel.getCurrentSearchQuery().observe(this, this::loadCurrentSearchQuery);
    }

    // endregion

    // region private Methods

    private void showFiltersFragment() {
        FragmentManager mng = this.getSupportFragmentManager();

        SearchFiltersDialogFragment fragment = new SearchFiltersDialogFragment();
        fragment.show(mng, fragment.getClass().getName());
    }

    private void updateUIState(UIState status) {
        switch (status.getCurrentStatus()) {
            case UI_IDLE: {
                binding.pbActivityMainLoading.setVisibility(View.GONE);
                break;
            }
            case UI_ON_BACKGROUND_WORK: {
                binding.pbActivityMainLoading.setVisibility(View.VISIBLE);
                break;
            }
            case UI_ON_ERROR: {
                binding.pbActivityMainLoading.setVisibility(View.GONE);

                // Valida si el mensaje de error ocurrió debido a una excepción
                if (status.getError() != null)
                    AppUtils.logError(status.getError());

                    // Valida si el mensaje de error es un mensaje controlado
                else if (!status.getMessage().isEmpty()) {
                    // En el caso de que sea un mensaje controlado muestra un toast en pantalla
                    Toast.makeText(getApplicationContext(), status.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private void loadCurrentSearchQuery(SearchQuery query) {
        // Valida si el campo de búsqueda esta vacío y en caso afirmativo carga el valor original
        // de la query guardada en memoria
        if (binding.txtActivityMainSearchQuery.getText().toString().isEmpty())
            binding.txtActivityMainSearchQuery.setText(query.getQuery());
    }

    private void showSearchResults(SearchResult result) {
        // Muestra los resultados encontrados en pantalla
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
            binding.trActivityMainSearchResultsBar.setVisibility(View.GONE);
            binding.rvActivityMainSearchResultsList.setVisibility(View.GONE);
            binding.lblActivityMainNoSearchResults.setVisibility(View.VISIBLE);
        }

    }

    // endregion

}// End Class