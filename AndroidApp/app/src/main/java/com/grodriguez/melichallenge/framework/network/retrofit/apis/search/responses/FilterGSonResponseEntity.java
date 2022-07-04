package com.grodriguez.melichallenge.framework.network.retrofit.apis.search.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilterGSonResponseEntity {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private String type;

    @SerializedName("values")
    private List<FilterValueGSonResponseEntity> values;

    public FilterGSonResponseEntity() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<FilterValueGSonResponseEntity> getValues() {
        return values;
    }

    public void setValues(List<FilterValueGSonResponseEntity> values) {
        this.values = values;
    }

    // endregion

}// End Class
