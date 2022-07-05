package com.grodriguez.melichallenge.framework.room.entities.site;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.grodriguez.melichallenge.framework.utils.AppConstants;

@Entity (tableName = AppConstants.SITE_CURRENCIES_ROOM_TABLE_NAME,
        primaryKeys = {"id", "siteId"},
        foreignKeys = {
                @ForeignKey(entity = SiteMetadataRoomEntity.class,
                        parentColumns = "id",
                        childColumns = "siteId",
                        onDelete = CASCADE)
        })
public class SiteCurrenciesRoomEntity {

    @NonNull
    public String id;
    @NonNull
    public String siteId;
    public String symbol;

    public SiteCurrenciesRoomEntity(@NonNull String id, String symbol, @NonNull String siteId) {
        this.id = id;
        this.symbol = symbol;
        this.siteId = siteId;
    }

}// End Class
