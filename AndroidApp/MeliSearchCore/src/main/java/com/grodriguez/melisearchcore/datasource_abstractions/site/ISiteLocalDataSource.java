package com.grodriguez.melisearchcore.datasource_abstractions.site;

import com.grodriguez.melisearchcore.model.dtos.SiteMetadataDTO;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface ISiteLocalDataSource {

    Single<SiteMetadataDTO> getSiteMetadata();

    Completable saveSiteMetadata(SiteMetadataDTO metadata);

    Completable deleteSiteMetadata();

}// End interface
