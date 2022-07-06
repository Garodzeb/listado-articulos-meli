package com.grodriguez.melichallenge.repositories;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.grodriguez.melichallenge.TestConstants;
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
public class ItemsRepositoryTest extends BaseRepositoryTest {

    ItemsRepository testRepository;

    @Before
    public void getTestRepository() {
        testRepository = dependenciesContainer.getItemsRepository();
    }

    @Test
    public void searchItemsTest() {
        SearchResultDTO result = testRepository.searchItems(SearchQueryMockupFactory.createValidSearchQueryWithParams()).blockingGet();
        SearchQueryValidator.evaluateSearchResult(result);
    }

    @Test
    public void getItemDetailsTest() {
        ItemDetailDTO result = testRepository.getItemDetails(TestConstants.TEST_VALID_ITEM_ID).blockingGet();
        ItemDetailValidator.evaluateGetItemDetailsResult(result);
    }

    @Test
    public void getItemRatingTest() {
        ItemRatingDTO result = testRepository.getItemRatings(TestConstants.TEST_VALID_ITEM_ID).blockingGet();
        ItemRatingsValidator.evaluateItemRatingsResult(result);
    }

    @Test
    public void refreshItemsSearchTest() throws Exception {
        SearchResultDTO original = testRepository.searchItems(SearchQueryMockupFactory.createValidSearchQueryWithParams()).blockingGet();
        SearchResultDTO refreshedData = testRepository.refreshItemsSearch().blockingGet();
        SearchQueryValidator.compareSearchResults(original, refreshedData);
    }

    @Test
    public void clearItemsSearchQuery() throws Exception {
        testRepository.clearItemsSearchQuery().blockingAwait();
    }

}// End Class
