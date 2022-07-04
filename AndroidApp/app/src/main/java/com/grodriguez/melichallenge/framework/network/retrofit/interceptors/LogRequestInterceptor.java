package com.grodriguez.melichallenge.framework.network.retrofit.interceptors;

import android.util.Log;

import androidx.annotation.NonNull;

import com.grodriguez.melichallenge.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

// Clase que se encarga de loguear todos los request a las APIs en el caso de que la aplicación se
// encuentre en modo "debug"
public class LogRequestInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        if (BuildConfig.DEBUG)
        {
            Request request = chain.request();

            String msg = "Llamada a la API: " + request.url().toString();
            Log.d(BuildConfig.APP_LOG_TAG, msg);
        }

        return chain.proceed(chain.request());
    }

}// End Class
