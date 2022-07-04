package com.grodriguez.melichallenge.framework.network.retrofit.apis.search.responses;

import com.google.gson.annotations.SerializedName;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.common.responses.ShippingGSonResponseEntity;

public class SearchItemGSonResponseEntity {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("currency_id")
    private String currencyId;

    @SerializedName("category_id")
    private String categoryId;

    @SerializedName("shipping")
    private ShippingGSonResponseEntity shipping;

    @SerializedName("condition")
    private String condition;

    @SerializedName("price")
    private double price;

    @SerializedName("thumbnail")
    private String thumbnailUrl;

    public SearchItemGSonResponseEntity() {
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

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public ShippingGSonResponseEntity getShipping() {
        return shipping;
    }

    public void setShipping(ShippingGSonResponseEntity shipping) {
        this.shipping = shipping;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    // endregion

}// End Class
