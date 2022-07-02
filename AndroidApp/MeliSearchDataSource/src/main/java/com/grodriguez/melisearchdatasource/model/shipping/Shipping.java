package com.grodriguez.melisearchdatasource.model.shipping;

import java.util.ArrayList;
import java.util.List;

public class Shipping {
    private boolean freeShipping = false;
    private String mode = "";
    private List<String> tags = new ArrayList<>();
    private String logisticType = "";
    private boolean storePickup = false;

    public Shipping() {
    }

    // region GET-SET

    public boolean isFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getLogisticType() {
        return logisticType;
    }

    public void setLogisticType(String logisticType) {
        this.logisticType = logisticType;
    }

    public boolean isStorePickup() {
        return storePickup;
    }

    public void setStorePickup(boolean storePickup) {
        this.storePickup = storePickup;
    }

    // endregion

}// End Class
