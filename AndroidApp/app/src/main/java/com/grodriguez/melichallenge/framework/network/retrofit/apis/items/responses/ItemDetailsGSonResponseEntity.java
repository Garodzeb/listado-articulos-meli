package com.grodriguez.melichallenge.framework.network.retrofit.apis.items.responses;

import com.google.gson.annotations.SerializedName;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.common.responses.ShippingGSonResponseEntity;

import java.util.List;

public class ItemDetailsGSonResponseEntity {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("category_id")
    private String categoryId;

    @SerializedName("price")
    private double price;

    @SerializedName("currency_id")
    private String currencyId;

    @SerializedName("available_quantity")
    private float availableQty;

    @SerializedName("sold_quantity")
    private float soldQty;

    @SerializedName("condition")
    private String condition;

    @SerializedName("pictures")
    private List<ItemPictureGSonResponseEntity> pictures;

    @SerializedName("shipping")
    private ShippingGSonResponseEntity shipping;

    @SerializedName("warranty")
    private String warranty;

    public ItemDetailsGSonResponseEntity() {
    }

    // region GET-SET

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public float getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(float availableQty) {
        this.availableQty = availableQty;
    }

    public float getSoldQty() {
        return soldQty;
    }

    public void setSoldQty(float soldQty) {
        this.soldQty = soldQty;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public List<ItemPictureGSonResponseEntity> getPictures() {
        return pictures;
    }

    public void setPictures(List<ItemPictureGSonResponseEntity> pictures) {
        this.pictures = pictures;
    }

    public ShippingGSonResponseEntity getShipping() {
        return shipping;
    }

    public void setShipping(ShippingGSonResponseEntity shipping) {
        this.shipping = shipping;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    // endregion

}// End Class
