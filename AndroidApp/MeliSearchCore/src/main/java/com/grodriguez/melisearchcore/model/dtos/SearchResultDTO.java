package com.grodriguez.melisearchcore.model.dtos;

import com.grodriguez.melisearchcore.model.Filter;
import com.grodriguez.melisearchcore.model.PagingMetadata;
import com.grodriguez.melisearchcore.model.SortType;

import java.util.ArrayList;
import java.util.List;

public class SearchResultDTO {

    private PagingMetadata paging = new PagingMetadata();
    private List<SearchItemDTO> results = new ArrayList<>();
    private SortType sort = new SortType();
    private List<SortType> availableSorts = new ArrayList<>();
    private List<Filter> filters = new ArrayList<>();
    private List<Filter> availableFilters = new ArrayList<>();

    public SearchResultDTO() {
    }

    // region GET-SET

    public PagingMetadata getPaging() {
        return paging;
    }

    public void setPaging(PagingMetadata paging) {
        this.paging = paging;
    }

    public List<SearchItemDTO> getResults() {
        return results;
    }

    public void setResults(List<SearchItemDTO> results) {
        this.results = results;
    }

    public SortType getSort() {
        return sort;
    }

    public void setSort(SortType sort) {
        this.sort = sort;
    }

    public List<SortType> getAvailableSorts() {
        return availableSorts;
    }

    public void setAvailableSorts(List<SortType> availableSorts) {
        this.availableSorts = availableSorts;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public List<Filter> getAvailableFilters() {
        return availableFilters;
    }

    public void setAvailableFilters(List<Filter> availableFilters) {
        this.availableFilters = availableFilters;
    }

    //endregion

}// End Class
