package com.grodriguez.melichallenge.application;

import android.content.Context;

import androidx.room.RoomDatabase;

import com.grodriguez.melichallenge.framework.datasource_implementations.local.ItemsLocalDataSourceImpl;
import com.grodriguez.melichallenge.framework.datasource_implementations.local.SiteLocalDataSourceImpl;
import com.grodriguez.melichallenge.framework.datasource_implementations.remote.ItemsRemoteDataSourceImpl;
import com.grodriguez.melichallenge.framework.datasource_implementations.remote.SiteRemoteDataSourceImpl;
import com.grodriguez.melichallenge.framework.room.AppRoomDatabase;
import com.grodriguez.melisearchcore.datasource_abstractions.items.IItemsRemoteDataSource;
import com.grodriguez.melisearchcore.datasource_abstractions.items.IItemsLocalDataSource;
import com.grodriguez.melisearchcore.datasource_abstractions.site.ISiteLocalDataSource;
import com.grodriguez.melisearchcore.datasource_abstractions.site.ISiteRemoteDataSource;
import com.grodriguez.melisearchcore.repositories.ItemsRepository;
import com.grodriguez.melisearchcore.repositories.SiteRepository;

// En esta clase se declaran todas las referencias a los repositorios con el objetivo de tenerlos
// centralizados en un solo lugar
public class AppDependenciesContainer {

    // Se mantiene una única instancia de esta clase para la aplicación
    private static AppDependenciesContainer instance;

    private final SiteRepository siteRepository;
    private final ItemsRepository itemsRepository;

    private AppDependenciesContainer(Context context, AppRoomDatabase roomDB) {
        // Crea el repositorio del sitio
        ISiteLocalDataSource siteLocalDataSource = new SiteLocalDataSourceImpl(context, roomDB);
        ISiteRemoteDataSource siteRemoteDataSource = new SiteRemoteDataSourceImpl();
        siteRepository = new SiteRepository(siteLocalDataSource, siteRemoteDataSource);

        // Crea el repositorio de los artículos
        IItemsLocalDataSource searchQueryLocalDataSource = new ItemsLocalDataSourceImpl(context, roomDB);
        IItemsRemoteDataSource itemsRemoteDataSource = new ItemsRemoteDataSourceImpl();
        itemsRepository = new ItemsRepository(searchQueryLocalDataSource, itemsRemoteDataSource);
    }

    // region GET

    public static AppDependenciesContainer getInstance(Context context, AppRoomDatabase roomDB) {
        if (instance == null) {
            // Se asegura de que haya un único thread tratando de crear la instancia de esta clase
            synchronized (AppDependenciesContainer.class) {
                instance = new AppDependenciesContainer(context, roomDB);
            }
        }
        return instance;
    }

    public SiteRepository getSiteRepository() {
        return siteRepository;
    }

    public ItemsRepository getItemsRepository() {
        return itemsRepository;
    }

    // endregion

}// End Class
