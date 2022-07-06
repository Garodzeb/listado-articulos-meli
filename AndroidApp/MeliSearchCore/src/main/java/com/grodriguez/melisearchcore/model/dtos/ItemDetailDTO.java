package com.grodriguez.melisearchcore.model.dtos;

import com.grodriguez.melisearchcore.model.domain.ItemPicture;
import com.grodriguez.melisearchcore.model.domain.Shipping;

import java.util.ArrayList;
import java.util.List;

public class ItemDetailDTO {

    private String id = "";
    private String title = "";
    private String currencyId = "";
    private Shipping shipping = new Shipping();
    private String categoryId = "";
    private String condition = "";
    private double price = -1;
    private List<ItemPicture> pictures = new ArrayList<>();
    private float availableQty = -1;
    private String warranty = "";
    private ItemRatingDTO itemRating = new ItemRatingDTO();

    public ItemDetailDTO() {
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

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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

    public List<ItemPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<ItemPicture> pictures) {
        this.pictures = (pictures != null) ? pictures : new ArrayList<>();
    }

    public float getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(float availableQty) {
        this.availableQty = availableQty;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public ItemRatingDTO getItemRating() {
        return itemRating;
    }

    public void setItemRating(ItemRatingDTO itemRating) {
        this.itemRating = itemRating;
    }

    // endregion

}//End Class
