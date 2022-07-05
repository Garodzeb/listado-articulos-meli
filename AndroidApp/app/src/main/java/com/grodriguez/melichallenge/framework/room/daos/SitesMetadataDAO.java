package com.grodriguez.melichallenge.framework.room.daos;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.grodriguez.melichallenge.framework.room.entities.site.SiteMetadataRoomEntity;
import com.grodriguez.melichallenge.framework.utils.AppConstants;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface SitesMetadataDAO {

    @Insert(onConflict = REPLACE)
    Completable insert(SiteMetadataRoomEntity... sites);

    @Update
    Completable update(SiteMetadataRoomEntity... sites);

    @Delete
    Completable delete(SiteMetadataRoomEntity... sites);

    @Query("SELECT * FROM " + AppConstants.SITE_METADATA_ROOM_TABLE_NAME + " WHERE id = :siteId")
    Single<SiteMetadataRoomEntity> getSiteMetadata(String siteId);

}// End Interface
