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

// Cliente de Retrofit encargado de comunicarse con la API de MercadoLibre
public class MeliApiRetrofitClient {

    // Timeout por defecto para esperar una respuesta de la API
    private static final int READ_TIMEOUT = 60;
    // Unidad de tiempo utilizada en conjunt con la variable READ_TIMEOUT
    private static final TimeUnit READ_TIMEOUT_UNIT = TimeUnit.SECONDS;

    // Instancia única del cliente de Retrofit
    private static Retrofit retrofitInstance;

    // URL a utilizar para comunicarse con la API, por defecto toma la URL declarada por variable
    // de entorno al momento de compilar la aplicación
    private static String apiUrl = BuildConfig.MELI_API_URL;

    // region GET-SET

    public static Retrofit getInstance() {
        if (retrofitInstance == null) {
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
                retrofitBuilder.baseUrl(getApiUrl());
                retrofitBuilder.addConverterFactory(gsonFactory);
                retrofitBuilder.addCallAdapterFactory(rxJava3CallAdapter);
                retrofitBuilder.client(okHttpClient);

                retrofitInstance = retrofitBuilder.build();
            }
        }

        return retrofitInstance;
    }

    public static String getApiUrl() {
        return apiUrl;
    }

    public static void setApiUrl(String apiUrl) {
        // En el caso de que se pase una URL vacía, utiliza la conexión por defecto a la API
        if (!apiUrl.trim().isEmpty())
            MeliApiRetrofitClient.apiUrl = apiUrl;
        else
            MeliApiRetrofitClient.apiUrl = BuildConfig.MELI_API_URL;
    }

    // endregion

}// End Class
