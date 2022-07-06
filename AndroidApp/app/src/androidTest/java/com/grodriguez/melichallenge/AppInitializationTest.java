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
    public void envVarMeliApiURLTest() {
        Assert.assertNotEquals("Variable de entorno 'MELI_API_URL' no puede ser vacía",
                "",
                BuildConfig.MELI_API_URL);
    }

    // Valida que se haya seteado el sitio por defecto a utilizar para consultar a la API
    @Test
    public void envVarMeliApiSiteTest() {
        Assert.assertNotEquals("Variable de entorno 'MELI_API_SITE' no puede ser vacía",
                "",
                BuildConfig.MELI_API_SITE);
    }

    @Test
    public void envVarTestValidSiteTest() {
        Assert.assertNotEquals("Variable de entorno 'TEST_VALID_API_SITE' no puede ser vacía",
                "",
                BuildConfig.TEST_VALID_API_SITE);
    }

    @Test
    public void envVarTestInvalidSiteTest() {
        Assert.assertNotEquals("Variable de entorno 'TEST_INVALID_API_SITE' no puede ser vacía",
                "",
                BuildConfig.TEST_INVALID_API_SITE);
    }

    @Test
    public void envVarTestValidItemIdTest() {
        Assert.assertNotEquals("Variable de entorno 'TEST_VALID_ITEM_ID' no puede ser vacía",
                "",
                BuildConfig.TEST_VALID_ITEM_ID);
    }

    @Test
    public void envVarTestInvalidItemIdTest() {
        Assert.assertNotEquals("Variable de entorno 'TEST_INVALID_ITEM_ID' no puede ser vacía",
                "",
                BuildConfig.TEST_INVALID_ITEM_ID);
    }

    @Test
    public void envVarValidSearchQueryTest() {
        Assert.assertNotEquals("Variable de entorno 'TEST_VALID_SEARCH_QUERY' no puede ser vacía",
                "",
                BuildConfig.TEST_VALID_SEARCH_QUERY);
    }

    @Test
    public void envVarInvalidSearchQueryTest() {
        Assert.assertNotEquals("Variable de entorno 'TEST_INVALID_SEARCH_QUERY' no puede ser vacía",
                "",
                BuildConfig.TEST_INVALID_SEARCH_QUERY);
    }

    @Test
    public void envVarValidFilterName1Test() {
        Assert.assertNotEquals("Variable de entorno 'TEST_VALID_FILTER_NAME_1' no puede ser vacía",
                "",
                BuildConfig.TEST_VALID_FILTER_NAME_1);
    }

    @Test
    public void envVarValidFilterName2Test() {
        Assert.assertNotEquals("Variable de entorno 'TEST_VALID_FILTER_NAME_2' no puede ser vacía",
                "",
                BuildConfig.TEST_VALID_FILTER_NAME_2);
    }

    @Test
    public void envVarValidFilterValue2Test() {
        Assert.assertNotEquals("Variable de entorno 'TEST_VALID_FILTER_VALUE_2' no puede ser vacía",
                "",
                BuildConfig.TEST_VALID_FILTER_VALUE_2);
    }

}// End Class
