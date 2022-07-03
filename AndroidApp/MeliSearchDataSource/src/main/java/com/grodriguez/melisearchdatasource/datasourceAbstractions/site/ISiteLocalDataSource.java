package com.grodriguez.melisearchdatasource.datasourceAbstractions.site;

import com.grodriguez.melisearchdatasource.model.dtos.SiteMetadataDTO;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface ISiteLocalDataSource {

    Single<SiteMetadataDTO> getSiteMetadata();

    Completable saveSiteMetadata(SiteMetadataDTO metadata);

    Completable deleteSiteMetadata();

}// End interface
