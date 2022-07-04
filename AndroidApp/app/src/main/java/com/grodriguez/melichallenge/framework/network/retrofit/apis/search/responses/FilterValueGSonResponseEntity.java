package com.grodriguez.melichallenge.framework.network.retrofit.apis.search.responses;

import com.google.gson.annotations.SerializedName;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.common.responses.CategoryGSonResponseEntity;

import java.util.List;

public class FilterValueGSonResponseEntity {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("results")
    private int results;

    @SerializedName("path_from_root")
    private List<CategoryGSonResponseEntity> pathFromRoot;

    public FilterValueGSonResponseEntity() {
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

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public List<CategoryGSonResponseEntity> getPathFromRoot() {
        return pathFromRoot;
    }

    public void setPathFromRoot(List<CategoryGSonResponseEntity> pathFromRoot) {
        this.pathFromRoot = pathFromRoot;
    }

    // endregion

}// End Class
