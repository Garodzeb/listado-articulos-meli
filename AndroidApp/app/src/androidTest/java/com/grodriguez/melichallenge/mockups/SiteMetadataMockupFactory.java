package com.grodriguez.melichallenge.mockups;

import com.grodriguez.melisearchcore.model.domain.SiteCategory;
import com.grodriguez.melisearchcore.model.domain.SiteCurrency;
import com.grodriguez.melisearchcore.model.dtos.SiteMetadataDTO;

import java.util.ArrayList;
import java.util.List;

public class SiteMetadataMockupFactory {

    // Crea un mockup con datos de prueba de la metadata de un sitio
    public static SiteMetadataDTO createTestMetadata() {
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
