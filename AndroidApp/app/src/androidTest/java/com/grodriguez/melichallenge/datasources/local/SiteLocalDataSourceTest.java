package com.grodriguez.melichallenge.datasources.local;

import android.content.Context;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.grodriguez.melichallenge.framework.datasource_implementations.local.SiteLocalDataSourceImpl;
import com.grodriguez.melichallenge.framework.room.AppRoomDatabase;
import com.grodriguez.melisearchcore.model.domain.SiteCategory;
import com.grodriguez.melisearchcore.model.domain.SiteCurrency;
import com.grodriguez.melisearchcore.model.dtos.SiteMetadataDTO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class SiteLocalDataSourceTest {

    private AppRoomDatabase testDatabase;
    private SiteLocalDataSourceImpl localDataSource;

    @Before
    public void createDb() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        testDatabase = Room.inMemoryDatabaseBuilder(appContext, AppRoomDatabase.class).build();
        localDataSource = new SiteLocalDataSourceImpl(appContext, testDatabase);
    }

    @After
    public void closeDb() throws IOException {
        testDatabase.close();
    }

    // Valida que se pueda guardar y leer la metadata del sitio desde la base de datos de Room
    @Test
    public void readWriteTest() {
        SiteMetadataDTO testData = createTestMetadata();

        SiteMetadataDTO result = localDataSource.saveSiteMetadata(testData)
                .andThen(localDataSource.getSiteMetadata(testData.getId())).blockingGet();

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), testData.getId());
        Assert.assertEquals(result.getName(), testData.getName());
        Assert.assertEquals(result.getCountryId(), testData.getCountryId());
        Assert.assertEquals(result.getDefaultCurrencyId(), testData.getDefaultCurrencyId());
        Assert.assertEquals(result.getCategories().size(), testData.getCategories().size());
        Assert.assertEquals(result.getCurrencies().size(), testData.getCurrencies().size());
    }

    private SiteMetadataDTO createTestMetadata() {
        SiteMetadataDTO dto = new SiteMetadataDTO();
        dto.setId("MLU");
        dto.setName("Uruguay");
        dto.setCountryId("Uy");
        dto.setDefaultCurrencyId("UYU");

        List<SiteCategory> categories = new ArrayList<>();
        categories.add(new SiteCategory("MLU5725", "Accesorios para Vehículos"));
        categories.add(new SiteCategory("MLU1512", "Agro"));
        dto.setCategories(categories);

        List<SiteCurrency> currencies = new ArrayList<>();
        currencies.add(new SiteCurrency("UYU", "$"));
        currencies.add(new SiteCurrency("USD", "U$S"));
        dto.setCurrencies(currencies);

        return dto;
    }


}// End Class
