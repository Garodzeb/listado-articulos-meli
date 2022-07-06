package com.grodriguez.melichallenge.framework.datasource_implementations.local;

import android.content.Context;

import com.grodriguez.melichallenge.framework.room.AppRoomDatabase;
import com.grodriguez.melichallenge.framework.room.daos.SiteCategoriesDAO;
import com.grodriguez.melichallenge.framework.room.daos.SiteCurrenciesDAO;
import com.grodriguez.melichallenge.framework.room.daos.SitesMetadataDAO;
import com.grodriguez.melichallenge.framework.room.entities.site.SiteCategoriesRoomEntity;
import com.grodriguez.melichallenge.framework.room.entities.site.SiteCurrenciesRoomEntity;
import com.grodriguez.melichallenge.framework.room.entities.site.SiteMetadataRoomEntity;
import com.grodriguez.melichallenge.framework.utils.AppConstants;
import com.grodriguez.melisearchcore.datasource_abstractions.site.ISiteLocalDataSource;
import com.grodriguez.melisearchcore.model.domain.SiteCategory;
import com.grodriguez.melisearchcore.model.domain.SiteCurrency;
import com.grodriguez.melisearchcore.model.dtos.SiteMetadataDTO;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class SiteLocalDataSourceImpl implements ISiteLocalDataSource {

    private final SitesMetadataDAO _siteMetadataDao;
    private final SiteCurrenciesDAO _siteCurrenciesDao;
    private final SiteCategoriesDAO _siteCategoriesDao;

    public SiteLocalDataSourceImpl(Context context, AppRoomDatabase roomDB) {
        _siteMetadataDao = roomDB.getSitesMetadataDao();
        _siteCategoriesDao = roomDB.getSiteCategoriesDao();
        _siteCurrenciesDao = roomDB.getSiteCurrenciesDao();
    }

    @Override
    public Single<SiteMetadataDTO> getSiteMetadata(String siteId) {
        return _siteMetadataDao.getSiteMetadata(siteId).map(siteMetadataEntity ->
        {
            if (siteMetadataEntity == null) {
                RoomNoDataException exception = new RoomNoDataException();
                exception.setTable(AppConstants.SITE_METADATA_ROOM_TABLE_NAME);
                exception.setParameters(siteId);
                throw exception;
            } else {
                SiteMetadataDTO metadata = new SiteMetadataDTO();
                metadata.setId(siteMetadataEntity.id);
                metadata.setName(siteMetadataEntity.name);
                metadata.setCountryId(siteMetadataEntity.countryId);
                metadata.setDefaultCurrencyId(siteMetadataEntity.defaultCurrencyId);

                // Busca las categorías del sitio
                List<SiteCategoriesRoomEntity> catEntities = _siteCategoriesDao.getSiteCategories(siteId);
                if (catEntities != null && catEntities.size() > 0) {

                    List<SiteCategory> siteCategories = new ArrayList<>();
                    for (SiteCategoriesRoomEntity catEntity : catEntities) {
                        SiteCategory cat = new SiteCategory();
                        cat.setId(catEntity.id);
                        cat.setName(catEntity.name);

                        siteCategories.add(cat);
                    }
                    metadata.setCategories(siteCategories);

                } else {
                    // Si no pudo encontrar las categorías lanza una excepción
                    RoomNoDataException catEexception = new RoomNoDataException();
                    catEexception.setTable(AppConstants.SITE_CATEGORIES_ROOM_TABLE_NAME);
                    catEexception.setParameters(siteId);
                    throw catEexception;
                }

                // Busca las monedas aceptadas del sitio
                List<SiteCurrenciesRoomEntity> currEntities = _siteCurrenciesDao.getSiteCurrencies(siteId);
                if (currEntities != null && currEntities.size() > 0) {

                    List<SiteCurrency> siteCurrencies = new ArrayList<>();
                    for (SiteCurrenciesRoomEntity currEntity : currEntities) {
                        SiteCurrency curr = new SiteCurrency();
                        curr.setId(currEntity.id);
                        curr.setSymbol(currEntity.symbol);
                        siteCurrencies.add(curr);
                    }
                    metadata.setCurrencies(siteCurrencies);
                } else {
                    // Si no pudo encontrar las categorías lanza una excepción
                    RoomNoDataException currException = new RoomNoDataException();
                    currException.setTable(AppConstants.SITE_CURRENCIES_ROOM_TABLE_NAME);
                    currException.setParameters(siteId);
                    throw currException;
                }

                return metadata;
            }
        });
    }

    @Override
    public Completable saveSiteMetadata(SiteMetadataDTO metadata) {

        if (metadata != null) {
            SiteMetadataRoomEntity siteEntity = new SiteMetadataRoomEntity(
                    metadata.getId(),
                    metadata.getName(),
                    metadata.getCountryId(),
                    metadata.getDefaultCurrencyId()
            );

            return _siteMetadataDao.insert(siteEntity)
                    .andThen(saveSiteCategories(metadata))
                    .andThen(saveSiteCurrencies(metadata));
        } else
            throw new IllegalArgumentException();
    }

    private Completable saveSiteCategories(SiteMetadataDTO metadata) {
        List<SiteCategory> siteCategories = metadata.getCategories();

        if (siteCategories.size() > 0) {
            List<SiteCategoriesRoomEntity> catEntities = new ArrayList<>();

            for(SiteCategory cat : siteCategories)
            {
                SiteCategoriesRoomEntity catEntity = new SiteCategoriesRoomEntity(
                        cat.getId(),
                        cat.getName(),
                        metadata.getId());

                catEntities.add(catEntity);
            }

            return _siteCategoriesDao.insert(catEntities);

        } else
            throw new IllegalArgumentException();
    }

    private Completable saveSiteCurrencies(SiteMetadataDTO metadata) {
        List<SiteCurrency> siteCurrencies = metadata.getCurrencies();

        if(siteCurrencies.size() > 0) {

            List<SiteCurrenciesRoomEntity> currEntities = new ArrayList<>();
            for(SiteCurrency curr : siteCurrencies) {
                SiteCurrenciesRoomEntity currEntity =  new SiteCurrenciesRoomEntity(
                        curr.getId(),
                        curr.getSymbol(),
                        metadata.getId());

                currEntities.add(currEntity);
            }

            return _siteCurrenciesDao.insert(currEntities);
        }
        else
            throw new IllegalArgumentException();
    }

}// End Class
