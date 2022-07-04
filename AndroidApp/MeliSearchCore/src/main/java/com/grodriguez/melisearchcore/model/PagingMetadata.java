package com.grodriguez.melisearchcore.model;

public class PagingMetadata {
    private int total = 0;
    private int primaryResults = 0;
    private int offset = 0;
    private int limit = 0;

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
