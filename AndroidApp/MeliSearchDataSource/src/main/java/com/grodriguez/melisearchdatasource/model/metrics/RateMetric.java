package com.grodriguez.melisearchdatasource.model.metrics;

public class RateMetric {
    private String period = "";
    private float rate = 0;
    private double value = 0;

    public RateMetric() {
    }

    // region GET-SET

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    // endregion

}// End Class
