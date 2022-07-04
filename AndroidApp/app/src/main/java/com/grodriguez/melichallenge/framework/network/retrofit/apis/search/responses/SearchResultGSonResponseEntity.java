package com.grodriguez.melichallenge.framework.network.retrofit.apis.search.responses;

import com.google.gson.annotations.SerializedName;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.common.responses.PagingGSonResponseEntity;

import java.util.List;

public class SearchResultGSonResponseEntity {

    @SerializedName("paging")
    private PagingGSonResponseEntity paging;

    @SerializedName("results")
    private List<SearchItemGSonResponseEntity> results;

    @SerializedName("sort")
    private SortTypeGSonResponseEntity sort;

    @SerializedName("available_sorts")
    private List<SortTypeGSonResponseEntity> availableSorts;

    @SerializedName("filters")
    private List<FilterGSonResponseEntity> filters;

    @SerializedName("available_filters")
    private List<FilterGSonResponseEntity> availableFilters;

    public SearchResultGSonResponseEntity() {
    }

    // region GET-SET

    public PagingGSonResponseEntity getPaging() {
        return paging;
    }

    public void setPaging(PagingGSonResponseEntity paging) {
        this.paging = paging;
    }

    public List<SearchItemGSonResponseEntity> getResults() {
        return results;
    }

    public void setResults(List<SearchItemGSonResponseEntity> results) {
        this.results = results;
    }

    public SortTypeGSonResponseEntity getSort() {
        return sort;
    }

    public void setSort(SortTypeGSonResponseEntity sort) {
        this.sort = sort;
    }

    public List<SortTypeGSonResponseEntity> getAvailableSorts() {
        return availableSorts;
    }

    public void setAvailableSorts(List<SortTypeGSonResponseEntity> availableSorts) {
        this.availableSorts = availableSorts;
    }

    public List<FilterGSonResponseEntity> getFilters() {
        return filters;
    }

    public void setFilters(List<FilterGSonResponseEntity> filters) {
        this.filters = filters;
    }

    public List<FilterGSonResponseEntity> getAvailableFilters() {
        return availableFilters;
    }

    public void setAvailableFilters(List<FilterGSonResponseEntity> availableFilters) {
        this.availableFilters = availableFilters;
    }

    // endregion

}// End Class
