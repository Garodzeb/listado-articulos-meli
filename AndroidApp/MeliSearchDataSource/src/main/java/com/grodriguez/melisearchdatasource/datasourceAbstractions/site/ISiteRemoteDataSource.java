package com.grodriguez.melisearchdatasource.datasourceAbstractions.site;

import com.grodriguez.melisearchdatasource.model.dtos.SiteMetadataDTO;

import io.reactivex.rxjava3.core.Single;

public interface ISiteRemoteDataSource {

    // Obtiene la metadata del sitio (categorías, monedas, etc)
    Single<SiteMetadataDTO> getSiteMetadata();

}// End Interface
