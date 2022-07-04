package com.grodriguez.melichallenge.framework.network.retrofit.apis.reviews.responses;

import com.google.gson.annotations.SerializedName;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.common.responses.PagingGSonResponseEntity;

public class ItemReviewGSonResponseEntity {

    @SerializedName("paging")
    private PagingGSonResponseEntity paging;

    @SerializedName("rating_average")
    private float ratingAvg;

    public ItemReviewGSonResponseEntity() {
    }

    // region GET-SET

    public PagingGSonResponseEntity getPaging() {
        return paging;
    }

    public void setPaging(PagingGSonResponseEntity paging) {
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
