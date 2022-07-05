package com.grodriguez.melichallenge.framework.room.daos;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.grodriguez.melichallenge.framework.room.entities.site.SiteCategoriesRoomEntity;
import com.grodriguez.melichallenge.framework.utils.AppConstants;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface SiteCategoriesDAO {

    @Insert(onConflict = REPLACE)
    Completable insert(List<SiteCategoriesRoomEntity> categories);

    @Update
    Completable update(SiteCategoriesRoomEntity... categories);

    @Delete
    Completable delete(SiteCategoriesRoomEntity... categories);

    @Query("SELECT * FROM " + AppConstants.SITE_CATEGORIES_ROOM_TABLE_NAME + " WHERE siteId=:siteId")
    List<SiteCategoriesRoomEntity> getSiteCategories(String siteId);

}// End Interface
