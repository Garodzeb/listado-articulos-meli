package com.grodriguez.melichallenge.repositories;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.grodriguez.melichallenge.TestConstants;
import com.grodriguez.melichallenge.validators.SiteMetadataValidator;
import com.grodriguez.melisearchcore.model.dtos.SiteMetadataDTO;
import com.grodriguez.melisearchcore.repositories.SiteRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.rxjava3.core.Completable;

@RunWith(AndroidJUnit4.class)
public class SiteRepositoryTest extends BaseRepositoryTest {

    SiteRepository testRepository;

    @Before
    public void getTestRepository() {
        testRepository = dependenciesContainer.getSiteRepository();
    }

    // Valida que se pueda obtener la metadata del sitio
    @Test
    public void getSiteMetadataTest() {
        SiteMetadataDTO siteMetadata = testRepository.getSiteMetadata(TestConstants.TEST_VALID_SITE).blockingGet();
        SiteMetadataValidator.validateSiteMetadataDTO(siteMetadata);
    }

    // Valida el proceso de obtener la metadata del sitio, guardarlo en la base local y buscar la
    // información guardada en al base
    @Test
    public void saveSiteMetadataTest() {
        // Obtiene los datos del sitio
        SiteMetadataDTO siteMetadata = testRepository.getSiteMetadata(TestConstants.TEST_VALID_SITE).blockingGet();

        // Guarda los datos del sitio
        testRepository.saveSiteMetadata(siteMetadata).blockingAwait();

        // Busca los datos guardados y valida si es igual a los datos obtenidos originalmente
        SiteMetadataDTO localData = testRepository.getSiteMetadata(TestConstants.TEST_VALID_SITE).blockingGet();
        SiteMetadataValidator.compareSiteData(siteMetadata, localData);
    }

}// End Class
