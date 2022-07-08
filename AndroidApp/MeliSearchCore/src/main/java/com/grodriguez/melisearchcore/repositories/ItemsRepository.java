package com.grodriguez.melisearchcore.repositories;

import com.grodriguez.melisearchcore.datasource_abstractions.items.IItemsLocalDataSource;
import com.grodriguez.melisearchcore.datasource_abstractions.items.IItemsRemoteDataSource;
import com.grodriguez.melisearchcore.model.domain.SearchQuery;
import com.grodriguez.melisearchcore.model.dtos.ItemDetailDTO;
import com.grodriguez.melisearchcore.model.dtos.ItemRatingDTO;
import com.grodriguez.melisearchcore.model.dtos.SearchResultDTO;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;

public class ItemsRepository {

    private IItemsLocalDataSource localItemsDS;
    private IItemsRemoteDataSource remoteItemsDS;

    public ItemsRepository(IItemsLocalDataSource localItemsDataSource,
                           IItemsRemoteDataSource remoteItemsDataSource) {
        this.localItemsDS = localItemsDataSource;
        this.remoteItemsDS = remoteItemsDataSource;
    }

    // region Public Methods

    // Realiza una búsqueda de artículos en el repositorio remoto con la query pasada como parámetro
    public Single<SearchResultDTO> searchItems(SearchQuery query) {
        if (!query.isEmpty()) {
            // Guarda la query en el repositorio local antes de realizar la consulta al repositorio
            // remoto
            return localItemsDS.saveSearchItemQuery(query)
                    .andThen(remoteItemsDS.searchItems(query.getQuery(), query.buildSearchQuery()));
        } else
            return Single.error(new IllegalArgumentException("Empty query"));
    }

    // Vuelve a realizar la búsqueda de productos con los datos guardados en el repositorio local
    public Single<SearchResultDTO> recoverItemsSearch() throws Exception{
        return localItemsDS.getSearchItemQuery().flatMap(this::searchItems);
    }

    // Elimina del cache la query de búsqueda de productos
    public Completable clearItemsSearchQuery(){
        return localItemsDS.deleteSearchItemQuery();
    }

    // Obtiene los datos de un artículo desde el repositorio remoto
    // Posible optimización: realizar las llamadas a getItemDetails y getItemRating en paralelo y
    // combinar los resultados
    public Single<ItemDetailDTO> getItemDetails(String itemId) {
        if (!itemId.isEmpty()) {
            return remoteItemsDS.getItemDetails(itemId);
        } else
            return Single.error(new IllegalArgumentException("Empty productId"));
    }

    // Obtiene las reseñas de un artículo desde un repositorio remoto
    public Single<ItemRatingDTO> getItemRatings(String itemId) {
        if (!itemId.isEmpty())
            return remoteItemsDS.getItemRatings(itemId);
        else
            return Single.error(new IllegalArgumentException("Empty productId"));
    }

    // endregion

}// End Class
