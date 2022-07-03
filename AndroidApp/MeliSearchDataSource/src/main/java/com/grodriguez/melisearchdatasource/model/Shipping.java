package com.grodriguez.melisearchdatasource.model;

import java.util.ArrayList;
import java.util.List;

public class Shipping {
    private Object dimensions = null;//TODO: validar que tipo de objeto se necesita
    private String mode = "";
    private String logisticType = "";
    private boolean storePickup = false;
    private boolean localPickup = false;
    private boolean freeShipping = false;
    private List<String> tags = new ArrayList<>();

    public Shipping() {
    }

    // region GET-SET

    public Object getDimensions() {
        return dimensions;
    }

    public void setDimensions(Object dimensions) {
        this.dimensions = dimensions;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
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

    public boolean isLocalPickup() {
        return localPickup;
    }

    public void setLocalPickup(boolean localPickup) {
        this.localPickup = localPickup;
    }

    public boolean isFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    // endregion

}// End Class
