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

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.grodriguez.melichallenge", appContext.getPackageName());
    }

    // Valida que se haya seteado la variable de entorno con la URL de la API a consultar
    @Test
    public void MeliApiURLSet()
    {
        Assert.assertNotEquals("Variable de entorno 'MELI_API_URL' no puede ser vacía",
                "",
                BuildConfig.MELI_API_URL);
    }

    // Valida que se haya seteado el sitio por defecto a utilizar para consultar a la API
    @Test
    public void MeliApiDefaultSiteSet()
    {
        Assert.assertNotEquals("Variable de entorno 'MELI_API_SITE' no puede ser vacía",
                "",
                BuildConfig.MELI_API_SITE);
    }



}// End Class
