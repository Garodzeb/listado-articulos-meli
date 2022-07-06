package com.grodriguez.melichallenge.datasources.local;

import android.content.Context;

import androidx.room.Room;
import androidx.room.rxjava3.EmptyResultSetException;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.grodriguez.melichallenge.framework.datasource_implementations.local.ItemsLocalDataSourceImpl;
import com.grodriguez.melichallenge.framework.room.AppRoomDatabase;
import com.grodriguez.melichallenge.mockups.SearchQueryMockupFactory;
import com.grodriguez.melichallenge.mockups.SiteMetadataMockupFactory;
import com.grodriguez.melichallenge.validators.SearchQueryValidator;
import com.grodriguez.melichallenge.validators.SiteMetadataValidator;
import com.grodriguez.melisearchcore.model.domain.QueryParameter;
import com.grodriguez.melisearchcore.model.domain.SearchQuery;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ItemsLocalDataSourceTest {

    private AppRoomDatabase testDatabase;
    private ItemsLocalDataSourceImpl localDataSource;

    @Before
    public void createDb() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        testDatabase = Room.inMemoryDatabaseBuilder(appContext, AppRoomDatabase.class).build();
        localDataSource = new ItemsLocalDataSourceImpl(appContext, testDatabase);
    }

    @After
    public void closeDb() throws IOException {
        testDatabase.close();
    }

    // Prueba que se pueda guardar una query en la base de datos y que se esta se pueda recuperar
    @Test
    public void readWriteTest() throws Exception {
        SearchQuery testData = SearchQueryMockupFactory.createValidSearchQueryWithParams();
        SearchQuery result = localDataSource.saveSearchItemQuery(testData)
                .andThen(localDataSource.getSearchItemQuery()).blockingGet();

        Assert.assertNotNull(result);
        SearchQueryValidator.compareSearchQueries(testData, result);
    }

    // Valida que después de eliminar una query de la base de datos esta ya no puede ser leída
    @Test(expected = EmptyResultSetException.class)
    public void deleteTest() {
        SearchQuery testData = SearchQueryMockupFactory.createValidSearchQueryWithParams();

        SearchQuery result = localDataSource.saveSearchItemQuery(testData)
                .andThen(localDataSource.deleteSearchItemQuery())
                .andThen(localDataSource.getSearchItemQuery()).blockingGet();
    }

}// End Class
