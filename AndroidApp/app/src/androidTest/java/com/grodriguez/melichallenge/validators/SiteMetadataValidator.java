package com.grodriguez.melichallenge.validators;

import com.grodriguez.melisearchcore.model.dtos.SiteMetadataDTO;

import org.junit.Assert;

public class SiteMetadataValidator {

    public static void validateSiteMetadataDTO(SiteMetadataDTO siteData) {
        Assert.assertNotNull(siteData);
        Assert.assertNotNull(siteData.getCurrencies());
        Assert.assertNotNull(siteData.getCategories());

        // Valida que se hayan traido los datos del sitio
        Assert.assertNotEquals("", siteData.getId());
        Assert.assertNotEquals("", siteData.getName());
        Assert.assertNotEquals("", siteData.getCountryId());
        Assert.assertNotEquals("", siteData.getDefaultCurrencyId());

        // Valida que se hayan traido las categorías asociadas al sitio
        Assert.assertFalse(siteData.getCategories().isEmpty());

        // Valida que se hayan traido las monedas asociadas al sitio
        Assert.assertFalse(siteData.getCurrencies().isEmpty());
    }

    // Valida que los datos del sitio A sean iguales a los datos del sitio B
    public static void compareSiteData(SiteMetadataDTO siteA, SiteMetadataDTO siteB) {
        Assert.assertNotNull(siteA);
        Assert.assertEquals(siteA.getId(), siteB.getId());
        Assert.assertEquals(siteA.getName(), siteB.getName());
        Assert.assertEquals(siteA.getCountryId(), siteB.getCountryId());
        Assert.assertEquals(siteA.getDefaultCurrencyId(), siteB.getDefaultCurrencyId());
        Assert.assertEquals(siteA.getCategories().size(), siteB.getCategories().size());
        Assert.assertEquals(siteA.getCurrencies().size(), siteB.getCurrencies().size());
    }

}// End Class
