package com.grodriguez.melichallenge.framework.room.daos;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.grodriguez.melichallenge.framework.room.entities.search.SearchQueriesRoomEntity;
import com.grodriguez.melichallenge.framework.utils.AppConstants;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface SearchQueriesDAO {

    @Insert(onConflict = REPLACE)
    Single<Long> insert(SearchQueriesRoomEntity query);

    @Query("DELETE FROM " + AppConstants.SEARCH_QUERIES_ROOM_TABLE_NAME)
    Completable deleteAllQueries();

    @Query("SELECT * FROM " + AppConstants.SEARCH_QUERIES_ROOM_TABLE_NAME + " LIMIT 1")
    Single<SearchQueriesRoomEntity> getCurrentQuery();

}// End Interface
