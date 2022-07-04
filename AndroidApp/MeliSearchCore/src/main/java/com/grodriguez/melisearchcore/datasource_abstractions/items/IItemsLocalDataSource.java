package com.grodriguez.melisearchcore.datasource_abstractions.items;

import com.grodriguez.melisearchcore.model.SearchQuery;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface IItemsLocalDataSource {

    Single<SearchQuery> getSearchItemQuery();

    Completable saveSearchItemQuery(SearchQuery query);

    Completable deleteSearchItemQuery();

    Single<String> getCurrentItemId();

    Completable saveCurrentItemId(String productId);

    Completable deleteCurrentItemId();

}// End Interface
