package com.grodriguez.melisearchdatasource.model.dtos;

import com.grodriguez.melisearchdatasource.model.SearchPaging;
import com.grodriguez.melisearchdatasource.model.PagingMetadata;

public class ProductRatingDTO {

    private PagingMetadata paging = new SearchPaging();
    private float ratingAvg = 0;

    public ProductRatingDTO() {
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
