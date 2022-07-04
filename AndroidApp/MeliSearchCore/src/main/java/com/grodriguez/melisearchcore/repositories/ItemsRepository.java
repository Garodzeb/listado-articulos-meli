package com.grodriguez.melisearchcore.repositories;

import com.grodriguez.melisearchcore.datasource_abstractions.items.IItemsLocalDataSource;
import com.grodriguez.melisearchcore.datasource_abstractions.items.IItemssRemoteDataSource;
import com.grodriguez.melisearchcore.model.SearchQuery;
import com.grodriguez.melisearchcore.model.dtos.ItemDetailDTO;
import com.grodriguez.melisearchcore.model.dtos.ItemRatingDTO;
import com.grodriguez.melisearchcore.model.dtos.SearchResultDTO;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class ItemsRepository {

    private IItemsLocalDataSource localItemsDS;
    private IItemssRemoteDataSource remoteItemsDS;

    public ItemsRepository(IItemsLocalDataSource localItemsDataSource,
                           IItemssRemoteDataSource remoteItemsDataSource) {
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
                    .andThen(remoteItemsDS.searchItems(query.buildSearchQuery()));
        } else
            return Single.error(new IllegalArgumentException("Empty query"));
    }

    // Vuelve a realizar la búsqueda de productos con los datos guardados en el repositorio local
    public Single<SearchResultDTO> refreshItemsSearch() {
        return localItemsDS.getSearchItemQuery().flatMap(this::searchItems);
    }

    // Elimina del cache la query de búsqueda de productos
    public Completable clearItemsSearchQuery(){
        return localItemsDS.deleteSearchItemQuery();
    }

    // Obtiene los datos de un artículo desde el repositorio remoto
    public Single<ItemDetailDTO> getItemDetails(String itemId) {
        if (!itemId.isEmpty()) {
            // Guarda el id del artículo en el repositorio local antes de ir a buscarlo al
            // repositorio remoto
            return localItemsDS.saveCurrentItemId(itemId)
                    .andThen(remoteItemsDS.getItemDetails(itemId));
        } else
            return Single.error(new IllegalArgumentException("Empty productId"));
    }

    // Vuelve a buscar los datos del artículo guardado en memoria
    public Single<ItemDetailDTO> refreshProductDetail() {
        return localItemsDS.getCurrentItemId().flatMap(this::getItemDetails);
    }

    // Obtiene las reseñas de un artículo desde un repositorio remoto
    public Single<ItemRatingDTO> getItemRating(String itemId) {
        if (!itemId.isEmpty())
            return remoteItemsDS.getItemRatings(itemId);
        else
            return Single.error(new IllegalArgumentException("Empty productId"));
    }

    // Vuelve a buscar el rating del artículo con el código de artículo guardado en memoria
    public Single<ItemRatingDTO> refreshItemRating() {
        return localItemsDS.getCurrentItemId().flatMap(this::getItemRating);
    }

    // Elimina del cache el id del artículo actual
    public Completable clearItemId() {
        return localItemsDS.deleteCurrentItemId();
    }

    // endregion

}// End Class
