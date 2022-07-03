package com.grodriguez.melisearchdatasource.model;

import java.util.ArrayList;
import java.util.List;

public class FilterValue {
    private String id = "";
    private String name = "";
    private int results = 0; //TODO: Solo tiene valor cuando se pasa en el campo availablefilters
    private List<Category> pathFromRoot = new ArrayList<>(); //TODO: Solo tiene un valor cuando el filtro es igual a 'Category'

    public FilterValue() {
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

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public List<Category> getPathFromRoot() {
        return pathFromRoot;
    }

    public void setPathFromRoot(List<Category> pathFromRoot) {
        this.pathFromRoot = pathFromRoot;
    }

    // endregion

}// End Class
