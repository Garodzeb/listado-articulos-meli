package com.grodriguez.melichallenge.framework.network.retrofit.apis.search;

import com.grodriguez.melichallenge.framework.network.retrofit.apis.search.responses.SearchResultGSonResponseEntity;

import java.util.Map;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface IMeliSearchAPIService {

    @Headers("Content-Type: application/json")
    @GET("sites/{siteId}/search")
    Single<Response<SearchResultGSonResponseEntity>> searchItem(@Path("siteId") String siteId,
                                                                @Query("q") String query,
                                                                @QueryMap Map<String, String> queryParameters);

}// End Interface
