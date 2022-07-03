package com.grodriguez.melisearchdatasource.repositories;

import com.grodriguez.melisearchdatasource.datasourceAbstractions.products.IProductsLocalDataSource;
import com.grodriguez.melisearchdatasource.datasourceAbstractions.products.IProductsRemoteDataSource;
import com.grodriguez.melisearchdatasource.model.SearchQuery;
import com.grodriguez.melisearchdatasource.model.dtos.ProductDetailDTO;
import com.grodriguez.melisearchdatasource.model.dtos.ProductRatingDTO;
import com.grodriguez.melisearchdatasource.model.dtos.SearchResultDTO;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class ProductRepository {

    private IProductsLocalDataSource localProductsDS;
    private IProductsRemoteDataSource remoteProductsDS;

    public ProductRepository(IProductsLocalDataSource localProductsDataSource,
                             IProductsRemoteDataSource remoteProductsDataSource) {
        this.localProductsDS = localProductsDataSource;
        this.remoteProductsDS = remoteProductsDataSource;
    }

    // region Public Methods

    // Realiza una búsqueda de artículos en el repositorio remoto con la query pasada como parámetro
    public Single<SearchResultDTO> searchProducts(SearchQuery query) {
        if (!query.isEmpty()) {
            // Guarda la query en el repositorio local antes de realizar la consulta al repositorio
            // remoto
            return localProductsDS.saveSearchProductQuery(query)
                    .andThen(remoteProductsDS.searchProducts(query));
        } else
            return Single.error(new IllegalArgumentException("Empty query"));
    }

    // Vuelve a realizar la búsqueda de productos con los datos guardados en el repositorio local
    public Single<SearchResultDTO> refreshProductSearch() {
        return localProductsDS.getSearchProductQuery().flatMap(this::searchProducts);
    }

    // Elimina del cache la query de búsqueda de productos
    public Completable clearProductSearchQuery(){
        return localProductsDS.deleteSearchProductQuery();
    }

    // Obtiene los datos de un artículo desde el repositorio remoto
    public Single<ProductDetailDTO> getProductDetails(String productId) {
        if (!productId.isEmpty()) {
            // Guarda el id del artículo en el repositorio local antes de ir a buscarlo al
            // repositorio remoto
            return localProductsDS.saveCurrentProductId(productId)
                    .andThen(remoteProductsDS.getProductDetails(productId));
        } else
            return Single.error(new IllegalArgumentException("Empty productId"));
    }

    // Vuelve a buscar los datos del artículo guardado en memoria
    public Single<ProductDetailDTO> refreshProductDetail() {
        return localProductsDS.getCurrentProductId().flatMap(this::getProductDetails);
    }

    // Obtiene las reseñas de un artículo desde un repositorio remoto
    public Single<ProductRatingDTO> getProductRating(String productId) {
        if (!productId.isEmpty())
            return remoteProductsDS.getProductRating(productId);
        else
            return Single.error(new IllegalArgumentException("Empty productId"));
    }

    // Vuelve a buscar el rating del artículo con el código de artículo guardado en memoria
    public Single<ProductRatingDTO> refreshProductRating() {
        return localProductsDS.getCurrentProductId().flatMap(this::getProductRating);
    }

    // Elimina del cache el id del artículo actual
    public Completable clearProductId() {
        return localProductsDS.deleteCurrentProductId();
    }

    // endregion

}// End Class
