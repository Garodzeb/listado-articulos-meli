package com.grodriguez.melisearchcore.model.domain.search;

import com.grodriguez.melisearchcore.model.domain.site.SiteCategory;

import java.util.ArrayList;
import java.util.List;

public class FilterValue {
    private String id = "";
    private String name = "";
    private boolean selected = false;
    private int results = 0; //TODO: validar si solo tiene valor cuando se pasa en el campo availablefilters
    private List<SiteCategory> pathFromRoot = new ArrayList<>(); //TODO: validar si solo tiene un valor cuando el filtro es igual a 'Category'

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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public List<SiteCategory> getPathFromRoot() {
        return pathFromRoot;
    }

    public void setPathFromRoot(List<SiteCategory> pathFromRoot) {
        this.pathFromRoot = pathFromRoot;
    }

    // endregion

}// End Class
