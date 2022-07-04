package com.grodriguez.melisearchcore.datasource_abstractions.site;

import com.grodriguez.melisearchcore.model.dtos.SiteMetadataDTO;

import io.reactivex.rxjava3.core.Single;

public interface ISiteRemoteDataSource {

    // Obtiene la metadata del sitio (categorías, monedas, etc)
    Single<SiteMetadataDTO> getSiteMetadata();

}// End Interface
