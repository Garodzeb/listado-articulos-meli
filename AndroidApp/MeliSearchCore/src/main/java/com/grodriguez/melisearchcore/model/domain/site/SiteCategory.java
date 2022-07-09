package com.grodriguez.melisearchcore.model.domain.site;

public class SiteCategory {
    private String id = "";
    private String name = "";

    public SiteCategory() {
    }

    public SiteCategory(String id, String name) {
        this.id = id;
        this.name = name;
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

    // endregion

}// End Class
