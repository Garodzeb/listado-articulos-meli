package com.grodriguez.melisearchcore.model.domain;

public class PagingMetadata {
    private int total = -1;
    private int primaryResults = -1;
    private int offset = -1;
    private int limit = -1;

    public PagingMetadata() {
    }

    // region GET-SET

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPrimaryResults() {
        return primaryResults;
    }

    public void setPrimaryResults(int primaryResults) {
        this.primaryResults = primaryResults;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    // endregion

}// End Class
