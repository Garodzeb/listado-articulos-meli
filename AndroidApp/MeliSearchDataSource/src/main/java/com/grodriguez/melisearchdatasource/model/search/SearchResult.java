package com.grodriguez.melisearchdatasource.model.search;

import com.grodriguez.melisearchdatasource.model.product.Product;

import java.util.ArrayList;
import java.util.List;

public class SearchResult {
    private String siteId = "";
    private String countryDefaultTimeZone = "";
    private String query = ""; // Solo se agrega a la respuesta si se pasa el parámetro 'q'
    private PagingMetadata paging = new PagingMetadata();
    private List<Product> results = new ArrayList<>();
    private SortType sort = new SortType();
    private List<SortType> availableSorts = new ArrayList<>();
    private List<Filter> filters = new ArrayList<>();
    private List<Filter> availablefilters = new ArrayList<>();

    public SearchResult() {
    }

    // region GET-SET

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getCountryDefaultTimeZone() {
        return countryDefaultTimeZone;
    }

    public void setCountryDefaultTimeZone(String countryDefaultTimeZone) {
        this.countryDefaultTimeZone = countryDefaultTimeZone;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public PagingMetadata getPaging() {
        return paging;
    }

    public void setPaging(PagingMetadata paging) {
        this.paging = paging;
    }

    public List<Product> getResults() {
        return results;
    }

    public void setResults(List<Product> results) {
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

    public List<Filter> getAvailablefilters() {
        return availablefilters;
    }

    public void setAvailablefilters(List<Filter> availablefilters) {
        this.availablefilters = availablefilters;
    }

    // endregion

}// End Class
