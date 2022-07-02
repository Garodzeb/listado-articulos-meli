package com.grodriguez.melisearchdatasource.model.metrics;

public class TransactionRatingStats {
    private float negative = 0;
    private float neutral = 0;
    private float positive = 0;

    public TransactionRatingStats() {
    }

    // region GET-SET

    public float getNegative() {
        return negative;
    }

    public void setNegative(float negative) {
        this.negative = negative;
    }

    public float getNeutral() {
        return neutral;
    }

    public void setNeutral(float neutral) {
        this.neutral = neutral;
    }

    public float getPositive() {
        return positive;
    }

    public void setPositive(float positive) {
        this.positive = positive;
    }

    // endregion

}// End Class
