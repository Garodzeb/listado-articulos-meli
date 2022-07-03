package com.grodriguez.melisearchdatasource.repositories;

import com.grodriguez.melisearchdatasource.datasourceAbstractions.site.ISiteLocalDataSource;
import com.grodriguez.melisearchdatasource.datasourceAbstractions.site.ISiteRemoteDataSource;
import com.grodriguez.melisearchdatasource.model.dtos.SiteMetadataDTO;

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
    public Single<SiteMetadataDTO> getSiteMetadata(String url) {
        return siteLocalDS.getSiteMetadata().onErrorResumeWith(siteRemoteDS.getSiteMetadata());
    }

    // Guarda la metadata del sitio en el repositorio local
    public Completable saveSiteMetadata(SiteMetadataDTO metadata) {
        return siteLocalDS.saveSiteMetadata(metadata);
    }

    // Elimina la metadata del sitio en el repositorio local
    public Completable deleteSiteMetadata(SiteMetadataDTO metadata) {
        return siteLocalDS.deleteSiteMetadata();
    }

    // endregion

}// End Class
