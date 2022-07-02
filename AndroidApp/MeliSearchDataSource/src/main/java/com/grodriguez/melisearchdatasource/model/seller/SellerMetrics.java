package com.grodriguez.melisearchdatasource.model.seller;

import com.grodriguez.melisearchdatasource.model.metrics.QuantityMetric;
import com.grodriguez.melisearchdatasource.model.metrics.RateMetric;

public class SellerMetrics {
    private RateMetric cancellations = new RateMetric();
    private RateMetric claims = new RateMetric();
    private RateMetric delayedHandlingTime = new RateMetric();
    private QuantityMetric salesCompleted = new QuantityMetric();

    public SellerMetrics() {
    }

    // region GET-SET

    public RateMetric getCancellations() {
        return cancellations;
    }

    public void setCancellations(RateMetric cancellations) {
        this.cancellations = cancellations;
    }

    public RateMetric getClaims() {
        return claims;
    }

    public void setClaims(RateMetric claims) {
        this.claims = claims;
    }

    public RateMetric getDelayedHandlingTime() {
        return delayedHandlingTime;
    }

    public void setDelayedHandlingTime(RateMetric delayedHandlingTime) {
        this.delayedHandlingTime = delayedHandlingTime;
    }

    public QuantityMetric getSalesCompleted() {
        return salesCompleted;
    }

    public void setSalesCompleted(QuantityMetric salesCompleted) {
        this.salesCompleted = salesCompleted;
    }

    // endregion

}// End Class
