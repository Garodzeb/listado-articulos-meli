package com.grodriguez.melisearchcore.datasource_abstractions.site;

import com.grodriguez.melisearchcore.model.dtos.SiteMetadataDTO;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface ISiteLocalDataSource {

    Single<SiteMetadataDTO> getSiteMetadata(String siteId);

    Completable saveSiteMetadata(SiteMetadataDTO metadata);

}// End interface
