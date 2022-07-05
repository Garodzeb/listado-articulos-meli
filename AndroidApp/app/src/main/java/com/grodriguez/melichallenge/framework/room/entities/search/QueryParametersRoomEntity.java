package com.grodriguez.melichallenge.framework.room.entities.search;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.grodriguez.melichallenge.framework.utils.AppConstants;

@Entity(tableName = AppConstants.QUERY_PARAMETERS_ROOM_TABLE_NAME,
        primaryKeys = {"id", "queryId"},
        foreignKeys = { @ForeignKey(entity = SearchQueriesRoomEntity.class,
                                    parentColumns = "id",
                                    childColumns = "queryId",
                                    onDelete = CASCADE)})
public class QueryParametersRoomEntity {

    @NonNull
    public String id;
    @NonNull
    public long queryId;

    public String value;

    public QueryParametersRoomEntity(@NonNull String id, long queryId, String value) {
        this.id = id;
        this.queryId = queryId;
        this.value = value;
    }

}// End Class
