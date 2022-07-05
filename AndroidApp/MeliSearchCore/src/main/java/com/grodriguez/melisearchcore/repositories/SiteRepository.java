package com.grodriguez.melisearchcore.repositories;

import com.grodriguez.melisearchcore.datasource_abstractions.site.ISiteLocalDataSource;
import com.grodriguez.melisearchcore.datasource_abstractions.site.ISiteRemoteDataSource;
import com.grodriguez.melisearchcore.model.dtos.SiteMetadataDTO;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class SiteRepository {

    private ISiteLocalDataSource siteLocalDS;
    private ISiteRemoteDataSource siteRemoteDS;

    public SiteRepository(ISiteLocalDataSource siteLocalDataSource, ISiteRemoteDataSource siteRemoteDataSource) {
        this.siteLocalDS = siteLocalDataSource;
        this.siteRemoteDS = siteRemoteDataSource;
    }

    // region public methods

    // Busca la metadata del sitio, primero en el repositorio local y si no la encuentra en el
    // repositorio remoto
    public Single<SiteMetadataDTO> getSiteMetadata(String siteId) {
        return siteLocalDS.getSiteMetadata(siteId).onErrorResumeWith(siteRemoteDS.getSiteMetadata(siteId));
    }

    // Guarda la metadata del sitio en el repositorio local
    public Completable saveSiteMetadata(SiteMetadataDTO metadata) {
        return siteLocalDS.saveSiteMetadata(metadata);
    }

    // endregion

}// End Class
