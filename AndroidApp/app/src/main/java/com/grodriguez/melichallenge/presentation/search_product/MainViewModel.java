package com.grodriguez.melichallenge.presentation.search_product;

import androidx.lifecycle.MutableLiveData;
import androidx.room.rxjava3.EmptyResultSetException;

import com.grodriguez.melichallenge.BuildConfig;
import com.grodriguez.melichallenge.framework.utils.AppUtils;
import com.grodriguez.melichallenge.framework.utils.UIStatus;
import com.grodriguez.melichallenge.presentation.ui.BaseViewModel;
import com.grodriguez.melichallenge.presentation.ui.UIState;
import com.grodriguez.melisearchcore.model.domain.search.Filter;
import com.grodriguez.melisearchcore.model.domain.search.FilterValue;
import com.grodriguez.melisearchcore.model.domain.search.QueryParameter;
import com.grodriguez.melisearchcore.model.domain.search.SearchItem;
import com.grodriguez.melisearchcore.model.domain.search.SearchQuery;
import com.grodriguez.melisearchcore.model.domain.search.SearchResult;
import com.grodriguez.melisearchcore.model.domain.site.SiteCurrency;
import com.grodriguez.melisearchcore.model.dtos.SearchResultDTO;
import com.grodriguez.melisearchcore.model.dtos.SiteMetadataDTO;
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
    private MutableLiveData<List<QueryParameter>> searchParameters;

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

    public MutableLiveData<List<QueryParameter>> getSearchParameters() {

        if (searchParameters == null)
            searchParameters = new MutableLiveData<>(new ArrayList<>());

        return searchParameters;
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
                        loadItemsCurrencyInfo(new SearchResult(resultDTO));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getUIState().setValue(new UIState(e));
                    }
                });

        addDisposableObserver(observer);
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
                            loadItemsCurrencyInfo(new SearchResult(resultDTO));
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
        SearchResult searchResult = getSearchResult().getValue();

        // Valida si ya se había realizado una consulta previa con filtros
        if (searchResult != null && searchResult.getFilters().size() > 0) {
            // Busca los filtros actuales de la consulta
            List<Filter> currentFilters = searchResult.getFilters();

            // Busca si ya se había seteado el mismo tipo de filtro
            int i = 0;
            int filtersSize = currentFilters.size();
            Filter appliedFilter = null;

            while (i < filtersSize && appliedFilter == null) {
                Filter aux = currentFilters.get(i);

                if (aux.getId().equals(filter.getId()))
                    appliedFilter = aux;

                i++;
            }

            // Si ya se había aplicado el mismo tipo de filtro
            if (appliedFilter != null) {
                int j = 0;
                int currentQueryLength = Objects.requireNonNull(getSearchParameters().getValue()).size();
                boolean foundParameter = false;

                List<QueryParameter> parameters = getSearchParameters().getValue();

                while (j < currentQueryLength && !foundParameter) {
                    QueryParameter qParam = parameters.get(j);

                    if (qParam.getId().equals(appliedFilter.getId())) {
                        foundParameter = true;
                        parameters.remove(j);

                        // Dependiendo del tipo de filtro reemplaza o agrega al listado de valores el filtro
                        // a aplicar
                        if (appliedFilter.getType() == Filter.FilterType.STRING) {
                            //TODO: validar si este es el único caso al que aplica

                            // Si el filtro es de tipo STRING se agrega el valor seleccionado en vez
                            // de reemplazarlo, esto se hace separando con comas cada nuevo valor
                            // Ej.: param1,param2...
                            String newValue = String.format("%s,%s", qParam.getValue(), selectedValue.getId());
                            parameters.add(new QueryParameter(filter.getId(), newValue));
                        } else
                            parameters.add(new QueryParameter(filter.getId(), selectedValue.getId()));
                    }

                    j++;
                }

                getSearchParameters().setValue(parameters);

            } else {
                // Si no se había aplicado el filtro previamente, lo agrega al listado de filtros
                Objects.requireNonNull(getSearchParameters().getValue())
                        .add(new QueryParameter(filter.getId(), selectedValue.getId()));
            }
        } else {
            // Si es la primera consulta que se realiza, agrega directamente el filtro al listado de filtros
            Objects.requireNonNull(getSearchParameters().getValue())
                    .add(new QueryParameter(filter.getId(), selectedValue.getId()));
        }
    }

    public void removeFilter(Filter filter, FilterValue selectedValue) {
        if (getSearchParameters().getValue() != null && getSearchParameters().getValue().size() > 0) {
            int i = 0;
            int parametersSize = getSearchParameters().getValue().size();
            boolean filterFound = false;

            while (i < parametersSize && !filterFound) {
                QueryParameter qParam = getSearchParameters().getValue().get(i);

                if (qParam.getId().equals(filter.getId()) && qParam.getValue().equals(selectedValue.getId()))
                    filterFound = true;
                else
                    i++;
            }

            if (filterFound)
                getSearchParameters().getValue().remove(i);
        }

    }

    // Limpia los filtros aplicados por el usuario
    public void clearFilters() {
        getSearchParameters().setValue(new ArrayList<>());
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
