package com.grodriguez.melichallenge.framework.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.grodriguez.melichallenge.BuildConfig;
import com.grodriguez.melichallenge.framework.room.daos.QueryParametersDAO;
import com.grodriguez.melichallenge.framework.room.daos.SearchQueriesDAO;
import com.grodriguez.melichallenge.framework.room.daos.SiteCategoriesDAO;
import com.grodriguez.melichallenge.framework.room.daos.SiteCurrenciesDAO;
import com.grodriguez.melichallenge.framework.room.daos.SitesMetadataDAO;
import com.grodriguez.melichallenge.framework.room.entities.search.QueryParametersRoomEntity;
import com.grodriguez.melichallenge.framework.room.entities.search.SearchQueriesRoomEntity;
import com.grodriguez.melichallenge.framework.room.entities.site.SiteCategoriesRoomEntity;
import com.grodriguez.melichallenge.framework.room.entities.site.SiteCurrenciesRoomEntity;
import com.grodriguez.melichallenge.framework.room.entities.site.SiteMetadataRoomEntity;

@Database(version = BuildConfig.APP_ROOM_DB_VERSION,
        entities = {
                SiteCategoriesRoomEntity.class,
                SiteMetadataRoomEntity.class,
                SiteCurrenciesRoomEntity.class,
                QueryParametersRoomEntity.class,
                SearchQueriesRoomEntity.class
        })
public abstract class AppRoomDatabase extends RoomDatabase {

    private static volatile AppRoomDatabase dbInstance;

    public static synchronized AppRoomDatabase getInstance(final Context context) {
        if (dbInstance == null) {
            dbInstance = create(context);
        }

        return dbInstance;
    }

    private static AppRoomDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                AppRoomDatabase.class,
                BuildConfig.APP_ROOM_DB_NAME).build();
    }

    // region DAOs

    public abstract SitesMetadataDAO getSitesMetadataDao();
    public abstract SiteCategoriesDAO getSiteCategoriesDao();
    public abstract SiteCurrenciesDAO getSiteCurrenciesDao();
    public abstract SearchQueriesDAO getSearchQueriesDao();
    public abstract QueryParametersDAO getQueryParametersDao();

    // endregion

}// End Class
