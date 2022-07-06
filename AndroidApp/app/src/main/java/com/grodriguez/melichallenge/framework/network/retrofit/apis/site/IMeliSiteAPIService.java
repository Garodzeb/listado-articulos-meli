package com.grodriguez.melichallenge.framework.network.retrofit.apis.site;

import com.grodriguez.melichallenge.BuildConfig;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.site.response.SiteMetadataGSonResponseEntity;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface IMeliSiteAPIService {

    @Headers("Content-Type: application/json")
    @GET("sites/{siteId}")
    Single<Response<SiteMetadataGSonResponseEntity>> getSiteMetadata(@Path("siteId") String siteId);

}// End Interface
