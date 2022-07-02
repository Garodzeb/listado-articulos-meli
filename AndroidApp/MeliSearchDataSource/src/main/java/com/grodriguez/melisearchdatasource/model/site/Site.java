package com.grodriguez.melisearchdatasource.model.site;

import com.grodriguez.melisearchdatasource.model.price.Currency;
import com.grodriguez.melisearchdatasource.model.search.Category;

import java.util.ArrayList;
import java.util.List;

public class Site {
    private String id = "";
    private String name = "";
    private String countryId = "";
    private String salesFeesMode = "";
    private int mercadoPagoVersion = 0;
    private String defaultCurrencyId = "";
    private String immediatePayment = "";
    private List<String> paymentMethodIds = new ArrayList<>();
    private List<SiteSetting> settings = new ArrayList<>();
    private List<Currency> currencies = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();
    private List<String> channels = new ArrayList<>();

    public Site() {
    }

    // region GET-SEt

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

    public String getSalesFeesMode() {
        return salesFeesMode;
    }

    public void setSalesFeesMode(String salesFeesMode) {
        this.salesFeesMode = salesFeesMode;
    }

    public int getMercadoPagoVersion() {
        return mercadoPagoVersion;
    }

    public void setMercadoPagoVersion(int mercadoPagoVersion) {
        this.mercadoPagoVersion = mercadoPagoVersion;
    }

    public String getDefaultCurrencyId() {
        return defaultCurrencyId;
    }

    public void setDefaultCurrencyId(String defaultCurrencyId) {
        this.defaultCurrencyId = defaultCurrencyId;
    }

    public String getImmediatePayment() {
        return immediatePayment;
    }

    public void setImmediatePayment(String immediatePayment) {
        this.immediatePayment = immediatePayment;
    }

    public List<String> getPaymentMethodIds() {
        return paymentMethodIds;
    }

    public void setPaymentMethodIds(List<String> paymentMethodIds) {
        this.paymentMethodIds = paymentMethodIds;
    }

    public List<SiteSetting> getSettings() {
        return settings;
    }

    public void setSettings(List<SiteSetting> settings) {
        this.settings = settings;
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

    public List<String> getChannels() {
        return channels;
    }

    public void setChannels(List<String> channels) {
        this.channels = channels;
    }

    // endregion

}// End Class
