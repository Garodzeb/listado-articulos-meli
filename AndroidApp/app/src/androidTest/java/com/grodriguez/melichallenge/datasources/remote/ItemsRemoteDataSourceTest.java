package com.grodriguez.melichallenge.datasources.remote;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.grodriguez.melichallenge.TestConstants;
import com.grodriguez.melichallenge.framework.datasource_implementations.remote.ItemsRemoteDataSourceImpl;
import com.grodriguez.melichallenge.framework.network.retrofit.APIException;
import com.grodriguez.melichallenge.mockups.SearchQueryMockupFactory;
import com.grodriguez.melichallenge.validators.APIExceptionValidator;
import com.grodriguez.melichallenge.validators.ItemDetailValidator;
import com.grodriguez.melichallenge.validators.ItemRatingsValidator;
import com.grodriguez.melichallenge.validators.SearchQueryValidator;
import com.grodriguez.melisearchcore.datasource_abstractions.items.IItemsRemoteDataSource;
import com.grodriguez.melisearchcore.model.domain.PagingMetadata;
import com.grodriguez.melisearchcore.model.domain.SearchQuery;
import com.grodriguez.melisearchcore.model.dtos.ItemDetailDTO;
import com.grodriguez.melisearchcore.model.dtos.ItemRatingDTO;
import com.grodriguez.melisearchcore.model.dtos.SearchResultDTO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ItemsRemoteDataSourceTest {

    private IItemsRemoteDataSource remoteDataSource;

    @Before
    public void createDataSource() {
        remoteDataSource = new ItemsRemoteDataSourceImpl();
    }

    // region SearchItems

    // Realiza una prueba buscando una búsqueda de artículos sin parámetros de búsqueda
    @Test
    public void searchItems_NoParameters_returnsSearhResultDTO() {
        SearchQuery query = SearchQueryMockupFactory.createValidNoParamsSearchQuery();

        SearchResultDTO searchResult = remoteDataSource.searchItems(
                        query.getQuery(),
                        query.buildSearchQuery())
                .blockingGet();

        SearchQueryValidator.evaluateSearchResult(searchResult);
    }

    // Realiza una prueba buscando una búsqueda de artículos con parámetros de búsqueda
    @Test
    public void searchItems_WithParameters_returnsSearchResultDTO() {
        // Crea la consulta a hacer a la API
        SearchQuery query = SearchQueryMockupFactory.createValidSearchQueryWithParams();
        SearchResultDTO searchResult = remoteDataSource.searchItems(
                        query.getQuery(),
                        query.buildSearchQuery())
                .blockingGet();

        SearchQueryValidator.evaluateSearchResult(searchResult);
    }

    // Valida que se devuelva un objeto válido del dominio en el caso de que la consulta
    // no retorne ningún resultado y no tenga ningún filtro aplicado
    @Test
    public void searchItems_NoResultsNoParameters_returnsSearchResultDTO() {
        // Crea la consulta a hacer a la API
        SearchQuery query = SearchQueryMockupFactory.createInvalidNoParamsSearchQuery();
        SearchResultDTO searchResult = remoteDataSource.searchItems(
                        query.getQuery(),
                        query.buildSearchQuery())
                .blockingGet();

        SearchQueryValidator.evaluateSearchResult(searchResult);
    }

    @Test
    public void searchItems_NoResultsWithParameters_returnsSearchResultDTO() {
        // Crea la consulta a hacer a la API
        SearchQuery query = SearchQueryMockupFactory.createInvalidSearchQueryWithParams();
        SearchResultDTO searchResult = remoteDataSource.searchItems(
                        query.getQuery(),
                        query.buildSearchQuery())
                .blockingGet();

        SearchQueryValidator.evaluateSearchResult(searchResult);
    }

    // endregion

    // region GetItemDetails

    // Valida obtener los detalles de un artículo
    @Test
    public void get_ItemDetails_returnsItemDetailDTO() {
        ItemDetailDTO item = remoteDataSource.getItemDetails(TestConstants.TEST_VALID_ITEM_ID).blockingGet();
        ItemDetailValidator.evaluateGetItemDetailsResult(item);
    }

    // Valida que se lance una excepción al buscar los datos de un artículo no existente
    @Test
    public void get_InvalidItemDetails_throwsAPIException() {
        try {
            remoteDataSource.getItemDetails(TestConstants.TEST_INVALID_ITEM_ID);
        } catch (Exception ex) {
            APIExceptionValidator.evaluateAPIExceptionData(ex);
        }
    }

    // endregion

    // region GetItemRatings

    // Valida que se puedan recuperar las reseñas de un artículo
    @Test
    public void get_ItemRatings_returnsItemRatingDTO() {
        ItemRatingDTO rating = remoteDataSource.getItemRatings(TestConstants.TEST_VALID_ITEM_ID).blockingGet();
        ItemRatingsValidator.evaluateItemRatingsResult(rating);
    }

    // Valida la excepción que se lanza cuando no se puede recuperar el rating de un artículo
    @Test
    public void get_InvalidItemRatings_throwsAPIException() {
        try {
            remoteDataSource.getItemRatings(TestConstants.TEST_INVALID_ITEM_ID);
        } catch (Exception ex) {
            APIExceptionValidator.evaluateAPIExceptionData(ex);
        }
    }

    // endregion

}//End Class
