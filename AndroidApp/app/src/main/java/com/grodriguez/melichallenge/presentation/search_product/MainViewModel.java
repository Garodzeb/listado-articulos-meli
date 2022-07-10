package com.grodriguez.melichallenge.presentation.search_product;

import androidx.lifecycle.MutableLiveData;
import androidx.room.rxjava3.EmptyResultSetException;

import com.grodriguez.melichallenge.BuildConfig;
import com.grodriguez.melichallenge.framework.utils.AppConstants;
import com.grodriguez.melichallenge.framework.utils.AppUtils;
import com.grodriguez.melichallenge.framework.utils.UIStatus;
import com.grodriguez.melichallenge.presentation.ui.BaseViewModel;
import com.grodriguez.melichallenge.presentation.ui.UIState;
import com.grodriguez.melisearchcore.model.domain.search.Filter;
import com.grodriguez.melisearchcore.model.domain.search.FilterValue;
import com.grodriguez.melisearchcore.model.domain.search.PagingMetadata;
import com.grodriguez.melisearchcore.model.domain.search.QueryParameter;
import com.grodriguez.melisearchcore.model.domain.search.SearchItem;
import com.grodriguez.melisearchcore.model.domain.search.SearchQuery;
import com.grodriguez.melisearchcore.model.domain.search.SearchResult;
import com.grodriguez.melisearchcore.model.domain.search.SortType;
import com.grodriguez.melisearchcore.model.domain.site.SiteCurrency;
import com.grodriguez.melisearchcore.model.dtos.SearchResultDTO;
import com.grodriguez.melisearchcore.model.dtos.SiteMetadataDTO;
import com.grodriguez.melisearchcore.model.enums.FilterType;
import com.grodriguez.melisearchcore.repositories.ItemsRepository;
import com.grodriguez.melisearchcore.repositories.SiteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends BaseViewModel {

    private SiteRepository siteRepository;
    private ItemsRepository itemsRepository;

    // region LiveData

    private MutableLiveData<SearchQuery> currentSearchQuery;
    private MutableLiveData<SearchResult> searchResult;
    private MutableLiveData<List<Filter>> availableFilters;
    private MutableLiveData<List<QueryParameter>> searchParameters;
    private MutableLiveData<Boolean> filtersApplied;
    private MutableLiveData<PagingMetadata> currentPagingInfo;

    // region GET-SET

    public MutableLiveData<SearchQuery> getCurrentSearchQuery() {

        if (currentSearchQuery == null)
            currentSearchQuery = new MutableLiveData<>(new SearchQuery());

        return currentSearchQuery;
    }

    public MutableLiveData<SearchResult> getSearchResult() {

        if (searchResult == null)
            searchResult = new MutableLiveData<>(new SearchResult());

        return searchResult;
    }

    public MutableLiveData<List<Filter>> getAvailableFilters() {

        if (availableFilters == null)
            availableFilters = new MutableLiveData<>();

        return availableFilters;
    }

    public MutableLiveData<List<QueryParameter>> getSearchParameters() {

        if (searchParameters == null)
            searchParameters = new MutableLiveData<>(new ArrayList<>());

        return searchParameters;
    }

    public MutableLiveData<Boolean> getFiltersApplied() {
        if (filtersApplied == null)
            filtersApplied = new MutableLiveData<>(false);

        return filtersApplied;
    }

    public void setFiltersApplied(Boolean appliedFilters) {
        getFiltersApplied().setValue(appliedFilters);
    }

    public MutableLiveData<PagingMetadata> getCurrentPagingInfo() {

        if (currentPagingInfo == null)
            currentPagingInfo = new MutableLiveData<>();

        return currentPagingInfo;
    }

    // endregion

    // endregion

    public MainViewModel(ItemsRepository itemsRepository, SiteRepository siteRepository) {
        super();
        this.itemsRepository = itemsRepository;
        this.siteRepository = siteRepository;
    }

    // region Public Methods

    // Realiza una búsqueda de artículos en base al texto y los filtros aplicados por el usuario
    public void searchItem(String searchValue) {
        // Crea la query a mandar
        SearchQuery query = new SearchQuery();
        query.setQuery(searchValue);

        // Valida si tiene que agregar parámetros adicionales a la consulta
        if (getSearchParameters().getValue() != null)
            query.setParameters(getSearchParameters().getValue());

        getCurrentSearchQuery().setValue(query);
        searchItem(query);
    }

    // Sobrecarga del método searchItem que acepta un SearchQuery en vez de un string
    public void searchItem(SearchQuery query) {

        // Cambia el estado de la UI para indicar que esta trabajando en segundo plano
        getUIState().setValue(new UIState(UIStatus.UI_ON_BACKGROUND_WORK));

        // Realiza la consulta a la API
        Disposable observer = itemsRepository.searchItems(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<SearchResultDTO>() {
                    @Override
                    public void onSuccess(@NonNull SearchResultDTO resultDTO) {
                        SearchResult result = new SearchResult(resultDTO);

                        getCurrentPagingInfo().setValue(result.getPagingData());

                        loadCurrentFilters(result);
                        loadItemsCurrencyInfo(result);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getUIState().setValue(new UIState(e));
                    }
                });

        addDisposableObserver(observer);
    }

    // Realiza la misma consulta de búsqueda de artículo guardada en memoria pero le aplica un offset
    // para obtener los nuevos resultados, en este caso le agrega un offset
    public void searchItemAddOffset() {
        SearchQuery query = getCurrentSearchQuery().getValue();

        // Valida que la consulta no sea nula
        if (query != null) {

            // Busca los parámetros de búsqueda de la consulta a realizar
            List<QueryParameter> parameters = getSearchParameters().getValue();

            if (parameters == null)
                parameters = new ArrayList<>();

            // Agrega el offset que corresponde en base a la última información de paginación obtenida
            PagingMetadata pagingData = getCurrentPagingInfo().getValue();

            // Valida que no se hayan mostrado todos los registros posibles
            if (pagingData != null && !pagingData.pagingLimitReached()) {
                // Agrega el offset a aplicar a la consulta
                QueryParameter offsetToApply = new QueryParameter();
                offsetToApply.setId(AppConstants.OFFSET_FILTER_KEY);
                offsetToApply.setValue(Integer.toString(pagingData.applyOffset()));
                parameters.add(offsetToApply);

                query.setParameters(parameters);
                searchItem(query);
            }
        }
    }

    // Realiza la misma consulta de búsqueda de artículo guardada en memoria pero le aplica un offset
    // para obtener los nuevos resultados, en este caso le resta al offset actual
    public void searchItemRemoveOffset() {
        SearchQuery query = getCurrentSearchQuery().getValue();

        // Valida que la consulta no sea nula
        if (query != null) {

            // Busca los parámetros de búsqueda de la consulta a realizar
            List<QueryParameter> parameters = getSearchParameters().getValue();

            if (parameters == null)
                parameters = new ArrayList<>();

            // Agrega el offset que corresponde en base a la última información de paginación obtenida
            PagingMetadata pagingData = getCurrentPagingInfo().getValue();

            // Valida que se haya aplicado algún offset previamente
            if (pagingData != null && pagingData.getOffset() > 0) {
                // Agrega el offset a aplicar a la consulta
                QueryParameter offsetToApply = new QueryParameter();
                offsetToApply.setId(AppConstants.OFFSET_FILTER_KEY);
                offsetToApply.setValue(Integer.toString(pagingData.removeOffset()));
                parameters.add(offsetToApply);

                query.setParameters(parameters);
                searchItem(query);
            }
        }
    }

    // Recupera la consulta realizada por el usuario
    public void recoverSearchQuery() {
        try {
            getUIState().setValue(new UIState(UIStatus.UI_ON_BACKGROUND_WORK));

            // Trata de recuperar la consulta realizada por el usuario guardada en memoria.
            // En el caso de no encontrarla se lanza la excepción EmptyResultSetException
            // y se informa del resultado vació a la interfaz
            Disposable observer = itemsRepository.recoverItemsSearch()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<SearchResultDTO>() {
                        @Override
                        public void onSuccess(@NonNull SearchResultDTO resultDTO) {
                            SearchResult result = new SearchResult(resultDTO);

                            loadCurrentFilters(result);
                            loadItemsCurrencyInfo(result);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            if (e.getClass() != EmptyResultSetException.class)
                                getUIState().setValue(new UIState(e));
                            else {
                                // Actualiza el estado de la interfaz
                                getUIState().setValue(new UIState(UIStatus.UI_IDLE));

                                // Envía un resultado vacío para informar a la interfaz que no
                                // tiene que realizar ninguna acción
                                getSearchResult().postValue(new SearchResult());
                            }
                        }
                    });

            addDisposableObserver(observer);

        } catch (Exception e) {
            if (e.getClass() != EmptyResultSetException.class)
                getUIState().setValue(new UIState(e));
            else {
                // Actualiza el estado de la interfaz
                getUIState().setValue(new UIState(UIStatus.UI_IDLE));

                // Envía un resultado vacío para informar a la interfaz que no
                // tiene que realizar ninguna acción
                getSearchResult().postValue(new SearchResult());
            }
        }
    }

    // Agrega el filtro seleccionado a la consulta a realizar
    public void addFilterToQuery(Filter filter, FilterValue selectedValue) {
        List<QueryParameter> parameters = getSearchParameters().getValue();

        // Valida si ya se había agregado el filtro a la consulta
        int i = 0;
        int parametersSize = parameters.size();
        boolean filterFound = false;

        while (i < parametersSize && !filterFound) {
            QueryParameter qParam = parameters.get(i);

            if (qParam.getId().equalsIgnoreCase(filter.getId())) {
                // Si ya había agregsado el filtro, actualiza su valor
                filterFound = true;

                qParam.setValue(selectedValue.getId());
            } else
                i++;
        }

        // Si no se encontró el filtro en la lista de filtros aplicados
        if (!filterFound) {
            // Agrega el filtro a la lista de parámetros
            parameters.add(new QueryParameter(filter.getId(), selectedValue.getId()));
        }

        getSearchParameters().setValue(parameters);
    }

    // Elimina un filtro de la lista de filtros aplicados
    public void removeFilter(Filter filter, FilterValue selectedValue) {
        // Valida que se hayan ingresado filtros para eliminar
        if (getSearchParameters().getValue() != null && getSearchParameters().getValue().size() > 0) {

            int i = 0;
            int parametersSize = getSearchParameters().getValue().size();
            QueryParameter foundFilter = null;

            // Busca el filtro a eliminar
            while (i < parametersSize && foundFilter == null) {
                QueryParameter qParam = getSearchParameters().getValue().get(i);

                // Valida por que el id y el valor sean iguales al filtro a eliminar
                if (qParam.getId().equals(filter.getId())
                        && qParam.getValue().equalsIgnoreCase(selectedValue.getId()))
                    foundFilter = qParam;
                else
                    i++;
            }

            // Elimina el filtro encontrado
            if (foundFilter != null) {
                getSearchParameters().getValue().remove(i);
            }
        }

    }

    // Limpia los filtros aplicados por el usuario
    public void clearFilters() {
        getSearchParameters().setValue(new ArrayList<>());
    }

    public void loadAvailableFilters() {
        SearchResult searchResult = getSearchResult().getValue();

        if (searchResult != null && searchResult.getAvailableFilters().size() > 0) {
            // Combina en una sola lista los filtros aplicados por el usuario y los filtros disponibles
            List<Filter> availableFilters = new ArrayList<>();

            // El ordenamiento aplicado se lo trata como un filtro adicional
            Filter sortFilter = new Filter();
            sortFilter.setId(AppConstants.SORT_FILTER_ID);
            sortFilter.setName(AppConstants.SORT_FILTER_NAME);
            sortFilter.setType(FilterType.STRING);

            SortType sort = searchResult.getSort();

            FilterValue selectedSort = new FilterValue();
            selectedSort.setId(sort.getId());
            selectedSort.setName(sort.getName());
            selectedSort.setSelected(true);

            sortFilter.getValues().add(selectedSort);

            // Agrega los sorts posibles a la lista de filtros
            for (SortType sortValue : searchResult.getAvailableSorts()) {
                FilterValue value = new FilterValue();
                value.setId(sortValue.getId());
                value.setName(sortValue.getName());

                sortFilter.getValues().add(value);
            }
            availableFilters.add(sortFilter);

            // Busca los filtros que no se deben mostrar en la interfaz
            String hiddenFilters = BuildConfig.HIDDEN_FILTERS;

            // Agrega los filtros aplicados a la consulta
            for (Filter filter : searchResult.getFilters()) {
                // Verifica que no sea uno de los filtros que no estan permitidos
                if (!hiddenFilters.toLowerCase().contains(filter.getId().toLowerCase()))
                    availableFilters.add(filter);
            }

            for (Filter filter : searchResult.getAvailableFilters()) {
                // Verifica que no sea uno de los filtros que no estan permitidos
                if (!hiddenFilters.toLowerCase().contains(filter.getId().toLowerCase()))
                    availableFilters.add(filter);
            }

            getAvailableFilters().setValue(availableFilters);
        }
    }

    @Override
    public void dispose() {
        Disposable observer = itemsRepository.clearItemsSearchQuery()
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        super.dispose();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        AppUtils.logError(e);
                    }
                });

        addDisposableObserver(observer);
    }

    // endregion

    // region Private Methods

    // Guarda los filtros aplicados retornados por la consulta a la API
    private void loadCurrentFilters(SearchResult resultDTO) {
        // Valida si el resultado tiene algún filtro aplicado
        if (resultDTO.getFilters().size() > 0) {
            getSearchParameters().setValue(new ArrayList<>());

            // Actualiza los filtros aplicados en la consulta con los filtros
            // retornados por la API
            for (Filter appliedFilter : resultDTO.getFilters()) {
                for (FilterValue filterValue : appliedFilter.getValues()) {
                    filterValue.setSelected(true);
                    addFilterToQuery(appliedFilter, filterValue);
                }
            }
        }
    }

    // Dado un resultado de búsqueda, agrega la información sobre la moneda tomando los datos
    // guardados en memoria cuando se inicializó la aplicación
    private void loadItemsCurrencyInfo(SearchResult results) {

        Disposable observer = siteRepository.getSiteMetadata(BuildConfig.MELI_API_SITE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<SiteMetadataDTO>() {
                    @Override
                    public void onSuccess(@NonNull SiteMetadataDTO siteMetadata) {
                        // Cambia el estado de la interfaz
                        getUIState().setValue(new UIState(UIStatus.UI_IDLE));

                        // Por cada artículo encontrado
                        for (SearchItem item : results.getResults()) {
                            // Busca la moneda asociada a dicho artículo
                            int i = 0;
                            int currenciesQty = siteMetadata.getCurrencies().size();
                            SiteCurrency currency = null;

                            while (i < currenciesQty && currency == null) {
                                SiteCurrency curr = siteMetadata.getCurrencies().get(i);

                                if (curr.getId().equals(item.getCurrencyId()))
                                    currency = curr;

                                i++;
                            }

                            if (currency != null)
                                item.setCurrency(currency);
                        }

                        getSearchResult().setValue(results);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        AppUtils.logError(e);
                    }
                });

        addDisposableObserver(observer);
    }

    // endregion

}// End Class
