package com.grodriguez.melisearchdatasource.model.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductPriceCondition {
    private List<String> contextRestrictions = new ArrayList<>();
    private Date startTime = new Date();
    private Date endTime = new Date();
    private boolean eligible = false;

    public ProductPriceCondition() {
    }

    // region GET-SET

    public List<String> getContextRestrictions() {
        return contextRestrictions;
    }

    public void setContextRestrictions(List<String> contextRestrictions) {
        this.contextRestrictions = contextRestrictions;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public boolean isEligible() {
        return eligible;
    }

    public void setEligible(boolean eligible) {
        this.eligible = eligible;
    }


    // endregion

}// End Class
