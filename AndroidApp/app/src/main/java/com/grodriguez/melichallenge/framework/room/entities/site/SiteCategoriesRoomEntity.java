package com.grodriguez.melichallenge.framework.room.entities.site;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.grodriguez.melichallenge.framework.utils.AppConstants;

@Entity(tableName = AppConstants.SITE_CATEGORIES_ROOM_TABLE_NAME,
        primaryKeys = {"id", "siteId"},
        foreignKeys = {
                @ForeignKey(entity = SiteMetadataRoomEntity.class,
                        parentColumns = "id",
                        childColumns = "siteId",
                        onDelete = CASCADE)
        })
public class SiteCategoriesRoomEntity {

    @NonNull
    public String id;
    @NonNull
    public String siteId;

    public String name;

    public SiteCategoriesRoomEntity(@NonNull String id, String name, @NonNull String siteId) {
        this.id = id;
        this.name = name;
        this.siteId = siteId;
    }

}// End Class
