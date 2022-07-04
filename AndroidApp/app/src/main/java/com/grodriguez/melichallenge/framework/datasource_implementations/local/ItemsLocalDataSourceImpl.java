package com.grodriguez.melichallenge.framework.datasource_implementations.local;

import com.grodriguez.melisearchcore.datasource_abstractions.items.IItemsLocalDataSource;
import com.grodriguez.melisearchcore.model.SearchQuery;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class ItemsLocalDataSourceImpl implements IItemsLocalDataSource {

    @Override
    public Single<SearchQuery> getSearchItemQuery() {
        return null;
    }

    @Override
    public Completable saveSearchItemQuery(SearchQuery query) {
        return null;
    }

    @Override
    public Completable deleteSearchItemQuery() {
        return null;
    }

    @Override
    public Single<String> getCurrentItemId() {
        return null;
    }

    @Override
    public Completable saveCurrentItemId(String productId) {
        return null;
    }

    @Override
    public Completable deleteCurrentItemId() {
        return null;
    }

}// End Class
