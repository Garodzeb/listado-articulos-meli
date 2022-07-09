package com.grodriguez.melisearchcore.model.domain.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        this.parameters = (parameters != null) ? parameters : new ArrayList<>();
    }

    // endregion

    // region Public methods

    public boolean isEmpty(){
        return query.isEmpty();
    }

    public Map<String, String> buildSearchQuery() {
        Map<String, String> queryParameters = new HashMap<>();
        int length = parameters.size();

        if (length > 0) {
            for (QueryParameter qParam : parameters) {
                queryParameters.put(qParam.getId(), qParam.getValue());
            }
        }

        return queryParameters;
    }

    // endregion

}// End Class
