package com.grodriguez.melisearchdatasource.model;

import java.util.ArrayList;
import java.util.List;

public class SearchQuery {

    private String query = "";
    private List<QueryParameter> parameters = new ArrayList<>();

    public SearchQuery() {
    }

    // region GET-SET

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<QueryParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<QueryParameter> parameters) {
        this.parameters = parameters;
    }

    // endregion

    // region Public methods

    public boolean isEmpty(){
        return query.isEmpty();
    }

    public String buildSearchQuery(String baseUrl) {
        String queryString = String.format("%1$s?%2$s", baseUrl, query);
        StringBuilder queryFilters = new StringBuilder();

        int length = parameters.size();

        if (length > 0) {
            for (QueryParameter qParam : parameters) {
                queryFilters.append(String.format("%1$s=%2$s", qParam.getId(), qParam.getValue()));
            }
        }

        return queryString;
    }

    // endregion

}// End Class
