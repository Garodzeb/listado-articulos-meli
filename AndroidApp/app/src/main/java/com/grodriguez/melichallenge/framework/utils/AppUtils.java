package com.grodriguez.melichallenge.framework.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Arrays;

public class AppUtils {

    // Guarda un dato de tipo String en SharedPreferences
    public static void saveCustomStringPreference(Context context, String key, String value) {
        SharedPreferences sharedPref = context.getSharedPreferences(AppConstants.SHARED_PREFERENCE_FILE_ID, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    // Obtiene un dato de tipo String de SharedPreferences
    public static String getCustomStringPreference(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(AppConstants.SHARED_PREFERENCE_FILE_ID, Context.MODE_PRIVATE);
        return sharedPref.getString(key, "");
    }

    // Elimina un dato de SharedPreferences
    public static void removeCustomPreference(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(AppConstants.SHARED_PREFERENCE_FILE_ID, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(key);
        editor.apply();
    }

    // Genera un log en consola con el mensaje del error generado
    public static void logError(Throwable e) {
        Log.e(AppConstants.APP_LOG_TAG, "Error message: " + e.getMessage());
        Log.e(AppConstants.APP_LOG_TAG, "Stacktrace: " + Arrays.toString(e.getStackTrace()));
    }

}// End Class
