package com.grodriguez.melisearchdatasource.model.product;

public class ProductInstallment {
    private double qty = 0;
    private double amount = 0;
    private double rate = 0;
    private String currencyId = "";

    public ProductInstallment() {
    }

    // region GET-SET

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    // endregion

}//End Class
