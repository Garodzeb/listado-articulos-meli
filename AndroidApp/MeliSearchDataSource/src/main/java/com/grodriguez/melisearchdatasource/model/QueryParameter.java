package com.grodriguez.melisearchdatasource.model;

public class QueryParameter {

    private String id = "";
    private String value = "";

    public QueryParameter() {
    }

    // region GET-SET

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    // endregion

}// End Class
