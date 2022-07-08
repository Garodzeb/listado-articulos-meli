package com.grodriguez.melichallenge.datasources.remote;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.grodriguez.melichallenge.TestConstants;
import com.grodriguez.melichallenge.framework.datasource_implementations.remote.SiteRemoteDataSourceImpl;
import com.grodriguez.melichallenge.mockups.SiteMetadataMockupFactory;
import com.grodriguez.melichallenge.validators.APIExceptionValidator;
import com.grodriguez.melichallenge.validators.SiteMetadataValidator;
import com.grodriguez.melisearchcore.datasource_abstractions.site.ISiteRemoteDataSource;
import com.grodriguez.melisearchcore.model.dtos.SiteMetadataDTO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SiteRemoteDataSourceTest {

    private ISiteRemoteDataSource remoteDataSource;

    @Before
    public void createDataSource() {
        remoteDataSource = new SiteRemoteDataSourceImpl();
    }

    // Prueba obtener la metadata de un sitio válido
    @Test
    public void get_ValidSiteMetadata_returnsSiteMetadataDTO() {
        SiteMetadataDTO siteData = remoteDataSource
                .getSiteMetadata(TestConstants.TEST_VALID_SITE)
                .blockingGet();
        SiteMetadataValidator.validateSiteMetadataDTO(siteData);
    }

    @Test
    public void get_InvalidSiteMetadata_throwsAPIException() {
        try {
            remoteDataSource.getSiteMetadata(TestConstants.TEST_INVALID_SITE);
        }
        catch (Exception ex) {
            APIExceptionValidator.evaluateAPIExceptionData(ex);
        }
    }

}// End Class
