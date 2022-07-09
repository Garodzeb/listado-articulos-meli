package com.grodriguez.melichallenge.datasources.local;

import android.content.Context;

import androidx.room.Room;
import androidx.room.rxjava3.EmptyResultSetException;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.grodriguez.melichallenge.framework.datasource_implementations.local.ItemsLocalDataSourceImpl;
import com.grodriguez.melichallenge.framework.room.AppRoomDatabase;
import com.grodriguez.melichallenge.mockups.SearchQueryMockupFactory;
import com.grodriguez.melichallenge.validators.SearchQueryValidator;
import com.grodriguez.melisearchcore.model.domain.search.SearchQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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

    // Prueba que se pueda guardar una query en la base de datos y que se esta se pueda recuperar
    @Test
    public void read_savedItemQuery_returnsSearchQuery() throws Exception {
        // Crea un query con datos de prueba
        SearchQuery testData = SearchQueryMockupFactory.createValidSearchQueryWithParams();

        // Guarda la query en la base y después la recupera
        SearchQuery result = localDataSource.saveSearchItemQuery(testData)
                .andThen(localDataSource.getSearchItemQuery()).blockingGet();

        // Valida que el resultado no sea null y que el contenido leido de la base sea igual
        // al contenido del query de prueba
        Assert.assertNotNull(result);
        SearchQueryValidator.compareSearchQueries(testData, result);
    }

    // Valida que después de eliminar una query de la base de datos esta ya no puede ser leída
    @Test(expected = EmptyResultSetException.class)
    public void delete_searchQuery_throwsEmptyResultSetException() {
        SearchQuery testData = SearchQueryMockupFactory.createValidSearchQueryWithParams();

        // Guarda un dato de prueba, lo elimina de la base y trata de recuperarlo, esto debe generar
        // una excepción de tipo EmptyResultSetException
        SearchQuery result = localDataSource.saveSearchItemQuery(testData)
                .andThen(localDataSource.deleteSearchItemQuery())
                .andThen(localDataSource.getSearchItemQuery()).blockingGet();
    }

}// End Class
