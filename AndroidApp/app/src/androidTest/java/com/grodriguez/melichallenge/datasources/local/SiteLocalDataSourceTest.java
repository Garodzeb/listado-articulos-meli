package com.grodriguez.melichallenge.datasources.local;

import android.content.Context;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.grodriguez.melichallenge.framework.datasource_implementations.local.SiteLocalDataSourceImpl;
import com.grodriguez.melichallenge.framework.room.AppRoomDatabase;
import com.grodriguez.melichallenge.mockups.SiteMetadataMockupFactory;
import com.grodriguez.melichallenge.validators.SiteMetadataValidator;
import com.grodriguez.melisearchcore.model.dtos.SiteMetadataDTO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;

import java.io.IOException;

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

    // Valida que se pueda guardar y leer la metadata del sitio desde la base de datos de Room
    @Test
    public void read_savedSiteMetadata_returnsSiteMetadataDTO() {
        SiteMetadataDTO testData = SiteMetadataMockupFactory.createTestMetadata();

        SiteMetadataDTO result = localDataSource.saveSiteMetadata(testData)
                .andThen(localDataSource.getSiteMetadata(testData.getId())).blockingGet();

        // Compara que los datos traidos de la BD sean iguales a los datos de prueba generados
        SiteMetadataValidator.compareSiteData(testData, result);
    }

}// End Class
