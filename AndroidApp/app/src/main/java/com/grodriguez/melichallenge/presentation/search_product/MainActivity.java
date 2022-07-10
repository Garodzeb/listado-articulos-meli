package com.grodriguez.melichallenge.presentation.search_product;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.grodriguez.melichallenge.application.AppDependenciesContainer;
import com.grodriguez.melichallenge.databinding.ActivityMainBinding;
import com.grodriguez.melichallenge.framework.room.AppRoomDatabase;
import com.grodriguez.melichallenge.framework.utils.AppConstants;
import com.grodriguez.melichallenge.framework.utils.AppUtils;
import com.grodriguez.melichallenge.presentation.ViewModelsFactory;
import com.grodriguez.melichallenge.presentation.product_detail.ProductDetailActivity;
import com.grodriguez.melichallenge.presentation.ui.UIState;
import com.grodriguez.melisearchcore.model.domain.search.SearchItem;
import com.grodriguez.melisearchcore.model.domain.search.SearchQuery;
import com.grodriguez.melisearchcore.model.domain.search.SearchResult;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements SearchResultsAdapter.ISearchResultsAdapterListener {

    SearchResultsAdapter searchResultsAdapter;
    LinearLayoutManager layoutManager;
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
        try {
            AppUtils.removeCustomPreference(getApplicationContext(), AppConstants.CURRENT_ITEM_ID_SHARED_PREFERENCE_KEY);
            AppUtils.saveCustomStringPreference(getApplicationContext(), AppConstants.CURRENT_ITEM_ID_SHARED_PREFERENCE_KEY, item.getId());

            Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception ex) {
            showErrorScreen(ex);
        }
    }

    // endregion

    // region Initialization Methods

    private void initializeUI() {
        initializeRecyclerView();

        binding.txtActivityMainSearchQuery.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                String searchValue = binding.txtActivityMainSearchQuery.getText().toString().trim();

                // Elimina los filtros aplicados y vuelve a realizar una consulta
                if (!searchValue.isEmpty()) {
                    mainViewModel.clearFilters();
                    mainViewModel.searchItem(searchValue);
                }

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
        layoutManager = new LinearLayoutManager(this);

        rv.setLayoutManager(layoutManager);
        rv.setAdapter(searchResultsAdapter);

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                try {
                    // Cuando se llega al último artículo de la lista, vuelve a realizar la búsqueda pero esta
                    // vez aplica un offset a las consulta.
                    // Dependiendo de la dirección del scroll se agrega o se resta del offset a aplicar
                    if (!recyclerView.canScrollVertically(AppConstants.SCROLL_DIRECTION_DOWN)
                            && recyclerView.canScrollVertically(AppConstants.SCROLL_DIRECTION_UP)) {

                        mainViewModel.searchItemAddOffset();
                    }
                    /* TODO: Se deja deshabilitado el volver a cargar la consulta restando un offset
                    *  debido a que no se encontro una manera de evitar que se ejecute con cualquier
                    * movimiento del usuario, idealmente se deberia poder ejecutar después de que el
                    * usuario deje de scrollear y no apenas se activa el scroll que es lo que ocurre
                    * actualmente */
                    /*else if (!recyclerView.canScrollVertically(AppConstants.SCROLL_DIRECTION_UP)) {
                        mainViewModel.searchItemRemoveOffset();
                    }*/

                } catch (Exception ex) {
                    showErrorScreen(ex);
                }
            }
        });
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
        } else
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

            // Busca la cantidad de resultados encontrados
            int totalResults = result.getPagingData().getTotal();
            String formattedQty;
            String searchResultsQtyLabel;

            // Modifica el mensaje a mostrar en base a si se encontraron más resultados que el
            // tope permitido para mostrar (2000)
            if (totalResults < AppConstants.MAX_SEARCH_RESULTS_DISPLAY_QTY) {
                formattedQty = NumberFormat.getInstance().format(result.getPagingData().getTotal());
                searchResultsQtyLabel = String.format("%s resultados", formattedQty);
            } else {
                formattedQty = NumberFormat.getInstance().format(AppConstants.MAX_SEARCH_RESULTS_DISPLAY_QTY);
                searchResultsQtyLabel = String.format("+%s resultados", formattedQty);
            }

            // Muestra la cantidad de registros encontrados
            binding.lblActivityMainSearchResultQty.setText(searchResultsQtyLabel);

            // Muestra la cantidad de filtros seleccionados
            String appliedFiltersLabel = String.format("Filtrar (%s)", result.getFilters().size());
            binding.btnActivityMainSearchFilters.setText(appliedFiltersLabel);

            // Actualiza el recyclerview con los artículos a mostrar
            searchResultsAdapter.updateItems(result.getResults());

            // Una vez cargado el artículo muestra el artículo en la primera posición de la lista
            layoutManager.scrollToPosition(0);
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