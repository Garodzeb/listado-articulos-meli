package com.grodriguez.melisearchcore.model.dtos;

import com.grodriguez.melisearchcore.model.PagingMetadata;

public class ItemRatingDTO {

    private PagingMetadata paging = new SearchPaging();
    private float ratingAvg = 0;

    public ItemRatingDTO() {
    }

    // region GET-SET

    public PagingMetadata getPaging() {
        return paging;
    }

    public void setPaging(PagingMetadata paging) {
        this.paging = paging;
    }

    public float getRatingAvg() {
        return ratingAvg;
    }

    public void setRatingAvg(float ratingAvg) {
        this.ratingAvg = ratingAvg;
    }

    // endregion

}// End class
