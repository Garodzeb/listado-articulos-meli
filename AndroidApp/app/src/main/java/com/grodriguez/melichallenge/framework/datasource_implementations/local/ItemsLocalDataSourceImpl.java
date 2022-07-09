package com.grodriguez.melichallenge.framework.datasource_implementations.local;

import android.content.Context;

import androidx.room.rxjava3.EmptyResultSetException;

import com.grodriguez.melichallenge.framework.room.AppRoomDatabase;
import com.grodriguez.melichallenge.framework.room.daos.QueryParametersDAO;
import com.grodriguez.melichallenge.framework.room.daos.SearchQueriesDAO;
import com.grodriguez.melichallenge.framework.room.entities.search.QueryParametersRoomEntity;
import com.grodriguez.melichallenge.framework.room.entities.search.SearchQueriesRoomEntity;
import com.grodriguez.melichallenge.framework.utils.AppConstants;
import com.grodriguez.melisearchcore.datasource_abstractions.items.IItemsLocalDataSource;
import com.grodriguez.melisearchcore.model.domain.search.QueryParameter;
import com.grodriguez.melisearchcore.model.domain.search.SearchQuery;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class ItemsLocalDataSourceImpl implements IItemsLocalDataSource {

    private final SearchQueriesDAO _searchQueriesDao;
    private final QueryParametersDAO _queryParametersDao;

    public ItemsLocalDataSourceImpl(Context context, AppRoomDatabase roomDB) {
        _searchQueriesDao = roomDB.getSearchQueriesDao();
        _queryParametersDao = roomDB.getQueryParametersDao();
    }

    @Override
    public Single<SearchQuery> getSearchItemQuery() throws EmptyResultSetException {
        return _searchQueriesDao.getCurrentQuery().map(query -> {
            if (query != null) {
                SearchQuery searchQuery = new SearchQuery();
                searchQuery.setQuery(query.query);

                List<QueryParameter> parameters = new ArrayList<>();

                // Valida si la consulta tiene parámetros adicionales
                List<QueryParametersRoomEntity> qParams = _queryParametersDao.getQueryParameters(query.id);
                if (qParams != null && qParams.size() > 0) {

                    for (QueryParametersRoomEntity qParamEntity : qParams) {
                        QueryParameter qParam = new QueryParameter();
                        qParam.setId(qParamEntity.id);
                        qParam.setValue(qParamEntity.value);

                        parameters.add(qParam);
                    }
                }

                searchQuery.setParameters(parameters);

                return searchQuery;
            } else {
                RoomNoDataException exception = new RoomNoDataException();
                exception.setTable(AppConstants.SEARCH_QUERIES_ROOM_TABLE_NAME);
                throw exception;
            }
        });
    }

    @Override
    public Completable saveSearchItemQuery(SearchQuery query) {
        // Guarda la consulta y los filtros aplicados a la misma
        SearchQueriesRoomEntity sQueryEntity = new SearchQueriesRoomEntity(query.getQuery());

        return _searchQueriesDao.insert(sQueryEntity)
                .flatMapCompletable(queryId -> saveSearchItemQueryParameters(queryId, query));
    }

    private Completable saveSearchItemQueryParameters(long queryId, SearchQuery query) {
        List<QueryParameter> qParameters = query.getParameters();

        // Valida que la consulta tenga parámetros que guardar
        if (qParameters.size() == 0)
            return Completable.complete();
        else {
            // Crea un listado con todos los registros a insertar
            List<QueryParametersRoomEntity> paramEntities = new ArrayList<>();
            for (QueryParameter qParam : query.getParameters()) {
                QueryParametersRoomEntity paramEntity = new QueryParametersRoomEntity(
                        qParam.getId(), queryId, qParam.getValue());
                paramEntities.add(paramEntity);
            }

            return _queryParametersDao.insert(paramEntities);
        }
    }

    @Override
    public Completable deleteSearchItemQuery() {
        return _searchQueriesDao.deleteAllQueries();
    }

}// End Class
