package com.grodriguez.melisearchcore.model.dtos;

import com.grodriguez.melisearchcore.model.domain.site.SiteCurrency;
import com.grodriguez.melisearchcore.model.domain.site.SiteCategory;

import java.util.ArrayList;
import java.util.List;

public class SiteMetadataDTO {

    private String id = "";
    private String name = "";
    private String countryId = "";
    private String defaultCurrencyId = "";
    private List<SiteCurrency> currencies = new ArrayList<>();
    private List<SiteCategory> categories = new ArrayList<>();

    public SiteMetadataDTO() {
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

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getDefaultCurrencyId() {
        return defaultCurrencyId;
    }

    public void setDefaultCurrencyId(String defaultCurrencyId) {
        this.defaultCurrencyId = defaultCurrencyId;
    }

    public List<SiteCurrency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<SiteCurrency> currencies) {
        this.currencies = (currencies != null) ? currencies : new ArrayList<>();
    }

    public List<SiteCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<SiteCategory> categories) {
        this.categories = (categories != null) ? categories : new ArrayList<>();
    }

    // endregion

}// End Class
