package com.grodriguez.melichallenge.framework.room.entities.site;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.grodriguez.melichallenge.framework.utils.AppConstants;

@Entity (tableName = AppConstants.SITE_METADATA_ROOM_TABLE_NAME)
public class SiteMetadataRoomEntity {

    @PrimaryKey
    @NonNull
    public String id;
    public String name;
    public String countryId;
    public String defaultCurrencyId;

    public SiteMetadataRoomEntity(@NonNull String id, String name, String countryId, String defaultCurrencyId) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
        this.defaultCurrencyId = defaultCurrencyId;
    }

}// End Class
