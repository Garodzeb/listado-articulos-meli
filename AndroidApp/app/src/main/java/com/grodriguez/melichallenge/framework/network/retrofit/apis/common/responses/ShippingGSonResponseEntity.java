package com.grodriguez.melichallenge.framework.network.retrofit.apis.common.responses;

import com.google.gson.annotations.SerializedName;

public class ShippingGSonResponseEntity {

    @SerializedName("mode")
    private String mode;

    @SerializedName("free_shipping")
    private boolean freeShipping;

    @SerializedName("local_pick_up")
    private boolean localPickUp;

    @SerializedName("store_pick_up")
    private boolean storePickUp;

    public ShippingGSonResponseEntity() {
    }

    // region GET-SET

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public boolean isFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public boolean isLocalPickUp() {
        return localPickUp;
    }

    public void setLocalPickUp(boolean localPickUp) {
        this.localPickUp = localPickUp;
    }

    public boolean isStorePickUp() {
        return storePickUp;
    }

    public void setStorePickUp(boolean storePickUp) {
        this.storePickUp = storePickUp;
    }

    // endregion

}// End Class
