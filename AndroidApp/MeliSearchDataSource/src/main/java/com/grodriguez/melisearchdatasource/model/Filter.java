package com.grodriguez.melisearchdatasource.model;

import java.util.ArrayList;
import java.util.List;

public class Filter {
    private String id = "";
    private String name = "";
    private String type = "";
    private List<FilterValue> values = new ArrayList<>();

    public Filter() {
    }

    // region GET-SET

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<FilterValue> getValues() {
        return values;
    }

    public void setValues(List<FilterValue> values) {
        this.values = values;
    }

    // endregion

}// End Class
