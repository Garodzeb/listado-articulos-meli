package com.grodriguez.melisearchdatasource.datasourceAbstractions.products;

import com.grodriguez.melisearchdatasource.model.SearchQuery;
import com.grodriguez.melisearchdatasource.model.dtos.ProductDetailDTO;
import com.grodriguez.melisearchdatasource.model.dtos.ProductRatingDTO;
import com.grodriguez.melisearchdatasource.model.dtos.SearchResultDTO;

import io.reactivex.rxjava3.core.Single;

public interface IProductsRemoteDataSource {

    Single<SearchResultDTO> searchProducts(SearchQuery query);

    Single<ProductDetailDTO> getProductDetails(String productId);

    Single<ProductRatingDTO> getProductRating(String productId);

}// End Interface
