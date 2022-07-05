package com.grodriguez.melichallenge.datasources.local;

import android.content.Context;

import androidx.room.Room;
import androidx.room.rxjava3.EmptyResultSetException;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.grodriguez.melichallenge.framework.datasource_implementations.local.RoomDataEmptyException;
import com.grodriguez.melichallenge.framework.datasource_implementations.local.SearchQueryLocalDataSourceImpl;
import com.grodriguez.melichallenge.framework.room.AppRoomDatabase;
import com.grodriguez.melichallenge.framework.room.daos.QueryParametersDAO;
import com.grodriguez.melichallenge.framework.room.daos.SearchQueriesDAO;
import com.grodriguez.melichallenge.framework.utils.AppConstants;
import com.grodriguez.melisearchcore.model.domain.QueryParameter;
import com.grodriguez.melisearchcore.model.domain.SearchQuery;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class SearchQueryLocalDataSourceTest {

    private AppRoomDatabase testDatabase;
    private SearchQueryLocalDataSourceImpl localDataSource;

    @Before
    public void createDb() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        testDatabase = Room.inMemoryDatabaseBuilder(appContext, AppRoomDatabase.class).build();
        localDataSource = new SearchQueryLocalDataSourceImpl(appContext, testDatabase);
    }

    @After
    public void closeDb() throws IOException {
        testDatabase.close();
    }

    // Prueba que se pueda guardar una query en la base de datos y que se esta se pueda recuperar
    @Test
    public void readWriteTest() throws Exception {
        SearchQuery testData = getTestSearchQuery();
        SearchQuery result = localDataSource.saveSearchItemQuery(testData)
                .andThen(localDataSource.getSearchItemQuery()).blockingGet();

        Assert.assertNotNull(result);
        Assert.assertEquals(testData.getQuery(), result.getQuery());
        Assert.assertEquals(testData.getParameters().size(), result.getParameters().size());
    }

    // Valida que después de eliminar una query de la base de datos esta ya no puede ser leída
    @Test(expected = EmptyResultSetException.class)
    public void deleteTest() {
        SearchQuery testData = getTestSearchQuery();

        SearchQuery result = localDataSource.saveSearchItemQuery(testData)
                .andThen(localDataSource.deleteSearchItemQuery())
                .andThen(localDataSource.getSearchItemQuery()).blockingGet();
    }

    private SearchQuery getTestSearchQuery() {

        SearchQuery query = new SearchQuery();
        query.setQuery("test");

        List<QueryParameter> parameters = new ArrayList<>();
        parameters.add(new QueryParameter("category", "MLU195437"));
        parameters.add(new QueryParameter("official_store", "628"));
        query.setParameters(parameters);

        return query;
    }

}// End Class
