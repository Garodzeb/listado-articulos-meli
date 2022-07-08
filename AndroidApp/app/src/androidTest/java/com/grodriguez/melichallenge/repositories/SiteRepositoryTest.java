package com.grodriguez.melichallenge.repositories;

import android.content.Context;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.grodriguez.melichallenge.TestConstants;
import com.grodriguez.melichallenge.application.AppDependenciesContainer;
import com.grodriguez.melichallenge.framework.room.AppRoomDatabase;
import com.grodriguez.melichallenge.validators.SiteMetadataValidator;
import com.grodriguez.melisearchcore.model.dtos.SiteMetadataDTO;
import com.grodriguez.melisearchcore.repositories.SiteRepository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import io.reactivex.rxjava3.core.Completable;

@RunWith(AndroidJUnit4.class)
public class SiteRepositoryTest {

    SiteRepository testRepository;
    protected AppRoomDatabase testDatabase;
    protected AppDependenciesContainer dependenciesContainer;

    @Before
    public void createDependencies() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        // Crea una base de datos Room en memoria
        testDatabase = Room.inMemoryDatabaseBuilder(appContext, AppRoomDatabase.class).build();

        // Crea el contenedor de dependencias a utilizar en las pruebas
        dependenciesContainer = AppDependenciesContainer.getInstance(appContext, testDatabase);

        testRepository = dependenciesContainer.getSiteRepository();
    }

    // Valida que se pueda obtener la metadata del sitio
    @Test
    public void get_SiteMetadata_returnsSiteMetadataDTO() {
        SiteMetadataDTO siteMetadata = testRepository.getSiteMetadata(TestConstants.TEST_VALID_SITE).blockingGet();
        SiteMetadataValidator.validateSiteMetadataDTO(siteMetadata);
    }

    // Valida el proceso de obtener la metadata del sitio, guardarlo en la base local y buscar la
    // información guardada en al base
    @Test
    public void save_SiteMetadata_returnsSiteMetadataDTO() {
        // Obtiene los datos del sitio
        SiteMetadataDTO siteMetadata = testRepository.getSiteMetadata(TestConstants.TEST_VALID_SITE).blockingGet();

        // Guarda los datos del sitio
        testRepository.saveSiteMetadata(siteMetadata).blockingAwait();

        // Busca los datos guardados y valida si es igual a los datos obtenidos originalmente
        SiteMetadataDTO localData = testRepository.getSiteMetadata(TestConstants.TEST_VALID_SITE).blockingGet();
        SiteMetadataValidator.compareSiteData(siteMetadata, localData);
    }

}// End Class
