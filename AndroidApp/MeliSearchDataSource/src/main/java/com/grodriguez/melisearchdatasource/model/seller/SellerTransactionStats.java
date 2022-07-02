package com.grodriguez.melisearchdatasource.model.seller;

import com.grodriguez.melisearchdatasource.model.metrics.TransactionRatingStats;

public class SellerTransactionStats {
    private double canceled = 0;
    private String period = "";
    private double total = 0;
    private TransactionRatingStats ratings = new TransactionRatingStats();
    private double completed = 0;

    public SellerTransactionStats() {
    }

    // region GET-SET

    public double getCanceled() {
        return canceled;
    }

    public void setCanceled(double canceled) {
        this.canceled = canceled;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public TransactionRatingStats getRatings() {
        return ratings;
    }

    public void setRatings(TransactionRatingStats ratings) {
        this.ratings = ratings;
    }

    public double getCompleted() {
        return completed;
    }

    public void setCompleted(double completed) {
        this.completed = completed;
    }

    // endregion

}// End Class
