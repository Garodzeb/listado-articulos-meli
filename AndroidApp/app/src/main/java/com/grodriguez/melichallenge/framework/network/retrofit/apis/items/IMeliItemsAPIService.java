package com.grodriguez.melichallenge.framework.network.retrofit.apis.items;

import com.grodriguez.melichallenge.framework.network.retrofit.apis.items.responses.ItemDetailsGSonResponseEntity;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface IMeliItemsAPIService {

    @Headers("Content-Type: application/json")
    @GET("items/{itemCode}")
    Single<Response<ItemDetailsGSonResponseEntity>> getItemDetails(@Path("itemCode") String itemCode);

}// End Interface
