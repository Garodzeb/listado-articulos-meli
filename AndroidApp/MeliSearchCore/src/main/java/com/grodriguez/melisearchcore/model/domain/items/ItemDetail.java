package com.grodriguez.melisearchcore.model.domain.items;

import com.grodriguez.melisearchcore.model.dtos.ItemDetailDTO;

import java.util.ArrayList;
import java.util.List;

public class ItemDetail extends BaseItem{

    private List<ItemPicture> pictures = new ArrayList<>();
    private float availableQty = -1;
    private String warranty = "";
    private ItemRating itemRating = new ItemRating();

    public ItemDetail() {
    }

    public ItemDetail(ItemDetailDTO dto) {
        this.id = dto.getId();
        this.title = dto.getTitle();
        this.currencyId = dto.getCurrencyId();
        this.shipping = dto.getShipping();
        this.categoryId = dto.getCategoryId();
        this.condition = dto.getCondition();
        this.price = dto.getPrice();
        this.availableQty = dto.getAvailableQty();
        this.warranty = dto.getWarranty();
        this.pictures = dto.getPictures();
    }

    // region GET-SET

    public List<ItemPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<ItemPicture> pictures) {
        this.pictures = pictures;
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

    public ItemRating getItemRating() {
        return itemRating;
    }

    public void setItemRating(ItemRating itemRating) {
        this.itemRating = itemRating;
    }

    // endregion

}// End Class
