package com.grodriguez.melichallenge.presentation.search_product;

import androidx.lifecycle.MutableLiveData;
import androidx.room.rxjava3.EmptyResultSetException;

import com.grodriguez.melichallenge.framework.utils.AppUtils;
import com.grodriguez.melichallenge.framework.utils.UIStatus;
import com.grodriguez.melichallenge.presentation.ui.BaseViewModel;
import com.grodriguez.melichallenge.presentation.ui.UIState;
import com.grodriguez.melisearchcore.model.domain.QueryParameter;
import com.grodriguez.melisearchcore.model.domain.SearchQuery;
import com.grodriguez.melisearchcore.model.dtos.SearchResultDTO;
import com.grodriguez.melisearchcore.repositories.ItemsRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends BaseViewModel {

    private ItemsRepository itemsRepository;

    // region LiveData

    private MutableLiveData<SearchQuery> currentSearchQuery;
    private MutableLiveData<SearchResultDTO> searchResult;
    private MutableLiveData<QueryParameter> parameters;

    // region GET-SET

    public MutableLiveData<SearchQuery> getCurrentSearchQuery() {

        if (currentSearchQuery == null)
            currentSearchQuery = new MutableLiveData<>(new SearchQuery());

        return currentSearchQuery;
    }

    public MutableLiveData<SearchResultDTO> getSearchResult() {

        if (searchResult == null)
            searchResult = new MutableLiveData<>(new SearchResultDTO());

        return searchResult;
    }

    // endregion

    // endregion

    public MainViewModel(ItemsRepository itemsRepository) {
        super();
        this.itemsRepository = itemsRepository;
    }

    // region Public Methods

    // Realiza una búsqueda de artículos en base al texto y los filtros aplicados por el usuario
    public void searchItem(String searchValue, List<QueryParameter> parameters) {
        // Crea la query a mandar
        SearchQuery query = new SearchQuery();
        query.setQuery(searchValue);

        if (parameters != null)
            query.setParameters(parameters);

        getCurrentSearchQuery().setValue(query);

        // Cambia el estado de la UI para indicar que esta trabajando en segundo plano
        getUIState().setValue(new UIState(UIStatus.UI_ON_BACKGROUND_WORK));

        // Realiza la consulta a la API
        Disposable observer = itemsRepository.searchItems(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<SearchResultDTO>() {
                    @Override
                    public void onSuccess(@NonNull SearchResultDTO searchResult) {
                        // Si pudo obtener un resultado, actualiza el estado de la interfaz
                        getUIState().setValue(new UIState(UIStatus.UI_IDLE));

                        // Manda a la UI el resultado obtenido
                        getSearchResult().postValue(searchResult);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getUIState().setValue(new UIState(e));
                    }
                });

        addDisposableObserver(observer);
    }

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
                        public void onSuccess(@NonNull SearchResultDTO searchResult) {
                            // Si pudo obtener un resultado, actualiza el estado de la interfaz
                            getUIState().setValue(new UIState(UIStatus.UI_IDLE));

                            // Manda a la UI el resultado obtenido
                            getSearchResult().postValue(searchResult);
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
                                getSearchResult().postValue(new SearchResultDTO());
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
                getSearchResult().postValue(new SearchResultDTO());
            }
        }
    }

    // endregion


    @Override
    public void dispose() {
        Disposable observer = itemsRepository.clearItemsSearchQuery()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
}// End Class
