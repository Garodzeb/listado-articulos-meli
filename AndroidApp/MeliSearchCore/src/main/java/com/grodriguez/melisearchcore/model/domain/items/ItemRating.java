package com.grodriguez.melisearchcore.model.domain.items;

import com.grodriguez.melisearchcore.model.domain.search.PagingMetadata;
import com.grodriguez.melisearchcore.model.dtos.ItemRatingDTO;

public class ItemRating {

    private PagingMetadata paging = new PagingMetadata();
    private float ratingAvg = 0;

    public ItemRating() {
    }

    public ItemRating(ItemRatingDTO dto) {
        this.paging = dto.getPaging();
        this.ratingAvg = dto.getRatingAvg();
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

}// End Class
