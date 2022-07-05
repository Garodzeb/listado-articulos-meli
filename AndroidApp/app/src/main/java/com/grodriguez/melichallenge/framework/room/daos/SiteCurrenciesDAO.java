package com.grodriguez.melichallenge.framework.room.daos;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.grodriguez.melichallenge.framework.room.entities.site.SiteCurrenciesRoomEntity;
import com.grodriguez.melichallenge.framework.utils.AppConstants;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface SiteCurrenciesDAO {

    @Insert(onConflict = REPLACE)
    Completable insert(List<SiteCurrenciesRoomEntity> currencies);

    @Update
    Completable update(SiteCurrenciesRoomEntity... currencies);

    @Delete
    Completable delete(SiteCurrenciesRoomEntity... currencies);

    @Query("SELECT * FROM " + AppConstants.SITE_CURRENCIES_ROOM_TABLE_NAME + " WHERE siteId=:siteId")
    List<SiteCurrenciesRoomEntity> getSiteCurrencies(String siteId);

}// End Interface
