package com.grodriguez.melisearchcore.datasource_abstractions.items;

import com.grodriguez.melisearchcore.model.domain.SearchQuery;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface IItemsLocalDataSource {

    Single<SearchQuery> getSearchItemQuery() throws Exception;

    Completable saveSearchItemQuery(SearchQuery query);

    Completable deleteSearchItemQuery();

}// End Interface
