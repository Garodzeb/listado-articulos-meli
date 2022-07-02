package com.grodriguez.melisearchdatasource.model.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductPrice {
    private String id = "";
    private String type = "";
    private double amount = 0;
    private double regularAmount = 0;
    private String currencyId = "";
    private Date lastUpdated = new Date();
    private List<ProductPriceCondition> conditions = new ArrayList<>();
    private String exchangeRateContext = "";
    private Object metadata = "";// TODO: Ver como se maneja el atributo metadata
    private List<String> tags = new ArrayList<>();
    private List<Object> purchaseDiscounts = new ArrayList<>();

    public ProductPrice() {
    }

    // region GET-SET

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRegularAmount() {
        return regularAmount;
    }

    public void setRegularAmount(double regularAmount) {
        this.regularAmount = regularAmount;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<ProductPriceCondition> getConditions() {
        return conditions;
    }

    public void setConditions(List<ProductPriceCondition> conditions) {
        this.conditions = conditions;
    }

    public String getExchangeRateContext() {
        return exchangeRateContext;
    }

    public void setExchangeRateContext(String exchangeRateContext) {
        this.exchangeRateContext = exchangeRateContext;
    }

    public Object getMetadata() {
        return metadata;
    }

    public void setMetadata(Object metadata) {
        this.metadata = metadata;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Object> getPurchaseDiscounts() {
        return purchaseDiscounts;
    }

    public void setPurchaseDiscounts(List<Object> purchaseDiscounts) {
        this.purchaseDiscounts = purchaseDiscounts;
    }

    // endregion

}// End Class
