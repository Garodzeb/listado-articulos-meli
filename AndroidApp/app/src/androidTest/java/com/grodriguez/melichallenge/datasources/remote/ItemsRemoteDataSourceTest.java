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
    public void searchItemsNoParametersTest() {
        SearchQuery query = SearchQueryMockupFactory.createValidNoParamsSearchQuery();
        evaluateSearchQuery(query);
    }

    // Realiza una prueba buscando una búsqueda de artículos con parámetros de búsqueda
    @Test
    public void searchItemsWithParametersTest() {
        // Crea la consulta a hacer a la API
        SearchQuery query = SearchQueryMockupFactory.createValidSearchQueryWithParams();
        evaluateSearchQuery(query);
    }

    // Valida que se devuelva un objeto válido del dominio en el caso de que la consulta
    // no retorne ningún resultado y no tenga ningún filtro aplicado
    @Test
    public void searchItemsNoResultsNoParametersQuery() {
        // Crea la consulta a hacer a la API
        SearchQuery query = SearchQueryMockupFactory.createInvalidNoParamsSearchQuery();
        evaluateSearchQuery(query);
    }

    @Test
    public void searchItemsNoResultsWithParametersQuery() {
        // Crea la consulta a hacer a la API
        SearchQuery query = SearchQueryMockupFactory.createInvalidSearchQueryWithParams();
        evaluateSearchQuery(query);
    }

    private void evaluateSearchQuery(SearchQuery query) {
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
    public void getItemDetailsTest() {
        ItemDetailDTO item = remoteDataSource.getItemDetails(TestConstants.TEST_VALID_ITEM_ID).blockingGet();
        ItemDetailValidator.evaluateGetItemDetailsResult(item);
    }

    // Valida que se lance una excepción al buscar los datos de un artículo no existente
    @Test
    public void getNonExistentItemDetailsTest() {
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
    public void getItemRatingsTest() {
        ItemRatingDTO rating = remoteDataSource.getItemRatings("MLU600098464").blockingGet();
        ItemRatingsValidator.evaluateItemRatingsResult(rating);
    }

    // Valida la excepción que se lanza cuando no se puede recuperar el rating de un artículo
    @Test
    public void getNonExistentItemRatingsTest() {
        try {
            remoteDataSource.getItemRatings("La-Li-Lu-Le-Lo");
        } catch (Exception ex) {
            APIExceptionValidator.evaluateAPIExceptionData(ex);
        }
    }

    // endregion

}//End Class
