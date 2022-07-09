package com.grodriguez.melisearchcore.model.domain.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Filter {

    public enum FilterType {
        TEXT,
        NUMBER,
        RANGE,
        BOOLEAN,
        STRING,
        NONE
    }

    private String id = "";
    private String name = "";
    private FilterType type = FilterType.NONE;
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

    public FilterType getType() {
        return type;
    }

    public void setType(String type) {
        FilterType filterType;

        switch (type.trim().toUpperCase(Locale.ROOT))
        {
            case "TEXT":
                filterType = FilterType.TEXT;
                break;
            case "NUMBER":
                filterType = FilterType.NUMBER;
                break;
            case "RANGE":
                filterType = FilterType.RANGE;
                break;
            case "BOOLEAN":
                filterType = FilterType.BOOLEAN;
                break;
            case "STRING":
                filterType = FilterType.STRING;
                break;
            default:
                filterType = FilterType.NONE;
                break;
        }

        this.type = filterType;
    }

    public List<FilterValue> getValues() {
        return values;
    }

    public void setValues(List<FilterValue> values) {
        this.values = values;
    }

    // endregion

}// End Class
