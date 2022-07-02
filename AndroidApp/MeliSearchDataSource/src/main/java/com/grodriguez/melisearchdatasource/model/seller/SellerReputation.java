package com.grodriguez.melisearchdatasource.model.seller;

public class SellerReputation {
    private String powerSellerStatus = "";
    private String levelId = "";
    private SellerMetrics metrics = new SellerMetrics();
    private SellerTransactionStats transactions = new SellerTransactionStats();

    public SellerReputation() {
    }

    // region GET-SET

    public String getPowerSellerStatus() {
        return powerSellerStatus;
    }

    public void setPowerSellerStatus(String powerSellerStatus) {
        this.powerSellerStatus = powerSellerStatus;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public SellerMetrics getMetrics() {
        return metrics;
    }

    public void setMetrics(SellerMetrics metrics) {
        this.metrics = metrics;
    }

    public SellerTransactionStats getTransactions() {
        return transactions;
    }

    public void setTransactions(SellerTransactionStats transactions) {
        this.transactions = transactions;
    }

    // endregion

}// End Class
