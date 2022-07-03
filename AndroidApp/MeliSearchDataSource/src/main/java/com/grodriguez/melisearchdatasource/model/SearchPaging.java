package com.grodriguez.melisearchdatasource.model;

public class SearchPaging extends PagingMetadata {

    private int primaryResults = 0;

    public SearchPaging() {
        super();
    }

    // region GET-SET

    public int getPrimaryResults() {
        return primaryResults;
    }

    public void setPrimaryResults(int primaryResults) {
        this.primaryResults = primaryResults;
    }

    // endregion

}// End Class
