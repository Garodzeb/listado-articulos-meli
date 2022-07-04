package com.grodriguez.melichallenge.framework.network.retrofit.apis.search.responses;

import com.google.gson.annotations.SerializedName;

public class SortTypeGSonResponseEntity {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    public SortTypeGSonResponseEntity() {
    }

    // region GET-SET

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // endregion

}//End Class
