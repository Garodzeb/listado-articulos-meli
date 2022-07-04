package com.grodriguez.melichallenge.framework.datasource_implementations.local;

import com.grodriguez.melisearchcore.datasource_abstractions.site.ISiteLocalDataSource;
import com.grodriguez.melisearchcore.model.dtos.SiteMetadataDTO;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class SiteLocalDataSourceImpl implements ISiteLocalDataSource {

    @Override
    public Single<SiteMetadataDTO> getSiteMetadata() {
        return null;
    }

    @Override
    public Completable saveSiteMetadata(SiteMetadataDTO metadata) {
        return null;
    }

    @Override
    public Completable deleteSiteMetadata() {
        return null;
    }

}// End Class
