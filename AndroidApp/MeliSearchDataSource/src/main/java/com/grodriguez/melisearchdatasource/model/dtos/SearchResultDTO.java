package com.grodriguez.melisearchdatasource.model.dtos;

import com.grodriguez.melisearchdatasource.model.Filter;
import com.grodriguez.melisearchdatasource.model.SearchPaging;
import com.grodriguez.melisearchdatasource.model.SortType;

import java.util.ArrayList;
import java.util.List;

public class SearchResultDTO {

    private SearchPaging paging = new SearchPaging();
    private List<SearchProductDTO> results = new ArrayList<>();
    private List<SortType> sort = new ArrayList<>();
    private List<SortType> availableSorts = new ArrayList<>();
    private List<Filter> filters = new ArrayList<>();
    private List<Filter> availableFilters = new ArrayList<>();

    public SearchResultDTO() {
    }

    // region GET-SET

    public SearchPaging getPaging() {
        return paging;
    }

    public void setPaging(SearchPaging paging) {
        this.paging = paging;
    }

    public List<SearchProductDTO> getResults() {
        return results;
    }

    public void setResults(List<SearchProductDTO> results) {
        this.results = results;
    }

    public List<SortType> getSort() {
        return sort;
    }

    public void setSort(List<SortType> sort) {
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
