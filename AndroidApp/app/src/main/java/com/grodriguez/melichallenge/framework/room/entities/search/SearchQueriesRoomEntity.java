package com.grodriguez.melichallenge.framework.room.entities.search;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.grodriguez.melichallenge.framework.utils.AppConstants;

@Entity (tableName = AppConstants.SEARCH_QUERIES_ROOM_TABLE_NAME)
public class SearchQueriesRoomEntity {

    @PrimaryKey(autoGenerate = true)
    public long id;
    public String query = "";

    public SearchQueriesRoomEntity(String query) {
        this.query = query;
    }

}// End Class
