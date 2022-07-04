package com.grodriguez.melichallenge.framework.network.retrofit.apis.reviews;

import com.grodriguez.melichallenge.framework.network.retrofit.apis.reviews.responses.ItemReviewGSonResponseEntity;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface IMeliReviewsAPIService {

    @Headers("Content-Type: application/json")
    @GET("reviews/item/{itemCode}")
    Single<Response<ItemReviewGSonResponseEntity>> getItemReview(@Path("itemCode") String itemCode);

}// End Interface
