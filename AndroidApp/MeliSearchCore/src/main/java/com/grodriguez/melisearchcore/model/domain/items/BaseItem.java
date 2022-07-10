package com.grodriguez.melisearchcore.model.domain.items;

import com.grodriguez.melisearchcore.model.domain.site.SiteCurrency;

public abstract class BaseItem {

    protected String id = "";
    protected String title = "";
    protected String currencyId = "";
    protected SiteCurrency currency = new SiteCurrency();
    protected Shipping shipping = new Shipping();
    protected String categoryId = "";
    protected String condition = "";
    protected double price = -1;
    protected boolean bestSeller = false;

    // region GET-SET

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public SiteCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(SiteCurrency currency) {
        this.currency = currency;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(boolean bestSeller) {
        this.bestSeller = bestSeller;
    }

    // endregion

}// End Class
