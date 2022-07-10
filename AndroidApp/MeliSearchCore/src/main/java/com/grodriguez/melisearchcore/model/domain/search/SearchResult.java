package com.grodriguez.melisearchcore.model.domain.search;

import com.grodriguez.melisearchcore.model.dtos.SearchItemDTO;
import com.grodriguez.melisearchcore.model.dtos.SearchResultDTO;

import java.util.ArrayList;
import java.util.List;

public class SearchResult {

    private PagingMetadata pagingData = new PagingMetadata();
    private List<SearchItem> results = new ArrayList<>();
    private SortType sort = new SortType();
    private List<SortType> availableSorts = new ArrayList<>();
    private List<Filter> filters = new ArrayList<>();
    private List<Filter> availableFilters = new ArrayList<>();

    public SearchResult() {
    }

    public SearchResult(SearchResultDTO dto) {
        this.pagingData = dto.getPaging();
        this.sort = dto.getSort();
        this.availableSorts = dto.getAvailableSorts();
        this.filters = dto.getFilters();
        this.availableFilters = dto.getAvailableFilters();

        this.results = new ArrayList<>();

        for(SearchItemDTO itemDto : dto.getResults())
        {
            SearchItem item = new SearchItem(itemDto);
            this.results.add(item);
        }
    }

    // region GET-SET

    public PagingMetadata getPagingData() {
        return pagingData;
    }

    public void setPagingData(PagingMetadata pagingData) {
        this.pagingData = pagingData;
    }

    public List<SearchItem> getResults() {
        return results;
    }

    public void setResults(List<SearchItem> results) {
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


    // endregion

}// End Class
