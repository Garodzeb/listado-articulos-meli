package com.grodriguez.melisearchdatasource.model.metrics;

public class QuantityMetric {
    private String period = "";
    private double qty = 0;

    public QuantityMetric() {
    }

    // region GET-SET

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    // endregion

}// End Class
