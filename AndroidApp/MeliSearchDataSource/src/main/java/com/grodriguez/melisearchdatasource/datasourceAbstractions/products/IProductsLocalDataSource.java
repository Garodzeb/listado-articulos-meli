package com.grodriguez.melisearchdatasource.datasourceAbstractions.products;

import com.grodriguez.melisearchdatasource.model.SearchQuery;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface IProductsLocalDataSource {

    Single<SearchQuery> getSearchProductQuery();

    Completable saveSearchProductQuery(SearchQuery query);

    Completable deleteSearchProductQuery();

    Single<String> getCurrentProductId();

    Completable saveCurrentProductId(String productId);

    Completable deleteCurrentProductId();

}// End Interface
