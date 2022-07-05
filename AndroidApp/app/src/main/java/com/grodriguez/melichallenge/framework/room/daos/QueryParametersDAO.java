package com.grodriguez.melichallenge.framework.room.daos;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.grodriguez.melichallenge.framework.room.entities.search.QueryParametersRoomEntity;
import com.grodriguez.melichallenge.framework.utils.AppConstants;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface QueryParametersDAO {

    @Insert(onConflict = REPLACE)
    Completable insert(List<QueryParametersRoomEntity> queryParams);

    @Update
    Completable update(QueryParametersRoomEntity... queryParams);

    @Delete
    Completable delete(QueryParametersRoomEntity... queryParams);

    @Query("SELECT * FROM " + AppConstants.QUERY_PARAMETERS_ROOM_TABLE_NAME + " WHERE queryId=:queryId")
    List<QueryParametersRoomEntity> getQueryParameters(long queryId);

}// End Interface
