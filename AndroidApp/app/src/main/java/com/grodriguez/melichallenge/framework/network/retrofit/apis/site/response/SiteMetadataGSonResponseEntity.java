package com.grodriguez.melichallenge.framework.network.retrofit.apis.site.response;

import com.google.gson.annotations.SerializedName;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.common.responses.CategoryGSonResponseEntity;

import java.util.List;

public class SiteMetadataGSonResponseEntity {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("country_id")
    private String countryId;

    @SerializedName("default_currency_id")
    private String defaultCurrencyId;

    @SerializedName("currencies")
    private List<CurrencyGSonResponseEntity> currencies;

    @SerializedName("categories")
    private List<CategoryGSonResponseEntity> categories;

    public SiteMetadataGSonResponseEntity() {
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

    public List<CurrencyGSonResponseEntity> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrencyGSonResponseEntity> currencies) {
        this.currencies = currencies;
    }

    public List<CategoryGSonResponseEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryGSonResponseEntity> categories) {
        this.categories = categories;
    }

    // endregion

}// End Class
