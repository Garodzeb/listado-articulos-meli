package com.grodriguez.melichallenge.repositories;

import android.content.Context;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.grodriguez.melichallenge.TestConstants;
import com.grodriguez.melichallenge.application.AppDependenciesContainer;
import com.grodriguez.melichallenge.framework.room.AppRoomDatabase;
import com.grodriguez.melichallenge.mockups.SearchQueryMockupFactory;
import com.grodriguez.melichallenge.validators.ItemDetailValidator;
import com.grodriguez.melichallenge.validators.ItemRatingsValidator;
import com.grodriguez.melichallenge.validators.SearchQueryValidator;
import com.grodriguez.melisearchcore.model.dtos.ItemDetailDTO;
import com.grodriguez.melisearchcore.model.dtos.ItemRatingDTO;
import com.grodriguez.melisearchcore.model.dtos.SearchResultDTO;
import com.grodriguez.melisearchcore.repositories.ItemsRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ItemsRepositoryTest {

    ItemsRepository testRepository;
    protected AppRoomDatabase testDatabase;
    protected AppDependenciesContainer dependenciesContainer;

    @Before
    public void createDependencies() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        // Crea una base de datos Room en memoria
        testDatabase = Room.inMemoryDatabaseBuilder(appContext, AppRoomDatabase.class).build();

        // Crea el contenedor de dependencias a utilizar en las pruebas
        dependenciesContainer = AppDependenciesContainer.getInstance(appContext, testDatabase);

        testRepository = dependenciesContainer.getItemsRepository();
    }

    @Test
    public void searchItems_returnsSearchResultDTO() {
        SearchResultDTO result = testRepository.searchItems(SearchQueryMockupFactory.createValidSearchQueryWithParams()).blockingGet();
        SearchQueryValidator.evaluateSearchResult(result);
    }

    @Test
    public void get_ItemDetails_returnsItemDetailDTO() {
        ItemDetailDTO result = testRepository.getItemDetails(TestConstants.TEST_VALID_ITEM_ID).blockingGet();
        ItemDetailValidator.evaluateGetItemDetailsResult(result);
    }

    @Test
    public void get_ItemRatings_returnsItemRatingDTO() {
        ItemRatingDTO result = testRepository.getItemRatings(TestConstants.TEST_VALID_ITEM_ID).blockingGet();
        ItemRatingsValidator.evaluateItemRatingsResult(result);
    }

    @Test
    public void recover_ItemsSearch_returnsSearchResultDTO() throws Exception {
        SearchResultDTO original = testRepository.searchItems(SearchQueryMockupFactory.createValidSearchQueryWithParams()).blockingGet();
        SearchResultDTO refreshedData = testRepository.recoverItemsSearch().blockingGet();
        SearchQueryValidator.compareSearchResults(original, refreshedData);
    }

    @Test
    public void clear_ItemsSearchQuery_throwsEmptyResultSetException() throws Exception {
        SearchResultDTO original = testRepository.searchItems(SearchQueryMockupFactory.createValidSearchQueryWithParams()).blockingGet();
        testRepository.clearItemsSearchQuery().blockingAwait();
    }

}// End Class
