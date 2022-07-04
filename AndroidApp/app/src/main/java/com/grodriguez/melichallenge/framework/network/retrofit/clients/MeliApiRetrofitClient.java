package com.grodriguez.melichallenge.framework.network.retrofit.clients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grodriguez.melichallenge.BuildConfig;
import com.grodriguez.melichallenge.framework.network.retrofit.interceptors.LogRequestInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MeliApiRetrofitClient {

     private static final int READ_TIMEOUT = 60;
     private static final TimeUnit READ_TIMEOUT_UNIT = TimeUnit.SECONDS;

     private static Retrofit retrofitInstance;

     public static Retrofit getInstance()
     {
          if(retrofitInstance == null)
          {
               // Se asegura de que solo exista una único Thread tratando de crear la instancia del
               // cliente
               synchronized (MeliApiRetrofitClient.class) {
                    // Crea un Gson converter para mapear las request de las APIs de Json a un objeto de
                    // java
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();
                    GsonConverterFactory gsonFactory = GsonConverterFactory.create(gson);

                    // Crea un adaptador de llamadas de RxJava para crear observables cuando se realice
                    // un request
                    RxJava3CallAdapterFactory rxJava3CallAdapter = RxJava3CallAdapterFactory.create();

                    // Crea el cliente de Http a utilizar para las llamadas a las APIs
                    OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
                    httpBuilder.readTimeout(READ_TIMEOUT, READ_TIMEOUT_UNIT);
                    httpBuilder.addInterceptor(new LogRequestInterceptor());
                    OkHttpClient okHttpClient = httpBuilder.build();

                    // Crea la instancia única de este cliente de retrofit
                    Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
                    retrofitBuilder.baseUrl(BuildConfig.MELI_API_URL);
                    retrofitBuilder.addConverterFactory(gsonFactory);
                    retrofitBuilder.addCallAdapterFactory(rxJava3CallAdapter);
                    retrofitBuilder.client(okHttpClient);

                    retrofitInstance = retrofitBuilder.build();
               }
          }

          return retrofitInstance;
     }

}// End Class
