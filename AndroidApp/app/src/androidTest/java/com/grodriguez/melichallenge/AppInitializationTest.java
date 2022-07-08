package com.grodriguez.melichallenge;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AppInitializationTest {

    // Valida que se haya seteado la variable de entorno con la URL de la API a consultar
    @Test
    public void get_envVar_MeliApiURL_returnsString() {
        Assert.assertNotEquals("Variable de entorno 'MELI_API_URL' no puede ser vacía",
                "",
                BuildConfig.MELI_API_URL);
    }

    // Valida que se haya seteado el sitio por defecto a utilizar para consultar a la API
    @Test
    public void get_envVar_MeliApiSite_returnsString() {
        Assert.assertNotEquals("Variable de entorno 'MELI_API_SITE' no puede ser vacía",
                "",
                BuildConfig.MELI_API_SITE);
    }

    @Test
    public void get_envVar_TestValidSite_returnsString() {
        Assert.assertNotEquals("Variable de entorno 'TEST_VALID_API_SITE' no puede ser vacía",
                "",
                BuildConfig.TEST_VALID_API_SITE);
    }

    @Test
    public void get_envVar_TestInvalidSite_returnsString() {
        Assert.assertNotEquals("Variable de entorno 'TEST_INVALID_API_SITE' no puede ser vacía",
                "",
                BuildConfig.TEST_INVALID_API_SITE);
    }

    @Test
    public void get_envVar_TestValidItemId_returnsString() {
        Assert.assertNotEquals("Variable de entorno 'TEST_VALID_ITEM_ID' no puede ser vacía",
                "",
                BuildConfig.TEST_VALID_ITEM_ID);
    }

    @Test
    public void get_envVar_TestInvalidItemId_returnsString() {
        Assert.assertNotEquals("Variable de entorno 'TEST_INVALID_ITEM_ID' no puede ser vacía",
                "",
                BuildConfig.TEST_INVALID_ITEM_ID);
    }

    @Test
    public void get_envVar_ValidSearchQuery_returnsString() {
        Assert.assertNotEquals("Variable de entorno 'TEST_VALID_SEARCH_QUERY' no puede ser vacía",
                "",
                BuildConfig.TEST_VALID_SEARCH_QUERY);
    }

    @Test
    public void get_envVar_InvalidSearchQuery_returnsString() {
        Assert.assertNotEquals("Variable de entorno 'TEST_INVALID_SEARCH_QUERY' no puede ser vacía",
                "",
                BuildConfig.TEST_INVALID_SEARCH_QUERY);
    }

    @Test
    public void get_envVar_ValidFilterName1_returnsString() {
        Assert.assertNotEquals("Variable de entorno 'TEST_VALID_FILTER_NAME_1' no puede ser vacía",
                "",
                BuildConfig.TEST_VALID_FILTER_NAME_1);
    }

    @Test
    public void get_envVar_ValidFilterName2_returnsString() {
        Assert.assertNotEquals("Variable de entorno 'TEST_VALID_FILTER_NAME_2' no puede ser vacía",
                "",
                BuildConfig.TEST_VALID_FILTER_NAME_2);
    }

    @Test
    public void get_envVar_ValidFilterValue2_returnsString() {
        Assert.assertNotEquals("Variable de entorno 'TEST_VALID_FILTER_VALUE_2' no puede ser vacía",
                "",
                BuildConfig.TEST_VALID_FILTER_VALUE_2);
    }

}// End Class
