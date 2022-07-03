package com.grodriguez.melisearchdatasource.model.dtos;

import com.grodriguez.melisearchdatasource.model.Currency;
import com.grodriguez.melisearchdatasource.model.Category;

import java.util.ArrayList;
import java.util.List;

public class SiteMetadataDTO {

    private String id = "";
    private String name = "";
    private String countryId = "";
    private String defaultCurrencyId = "";
    private List<Currency> currencies = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();

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

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    // endregion

}// End Class
