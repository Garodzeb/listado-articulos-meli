package com.grodriguez.melichallenge.mockups;

import com.grodriguez.melichallenge.TestConstants;
import com.grodriguez.melisearchcore.model.domain.search.QueryParameter;
import com.grodriguez.melisearchcore.model.domain.search.SearchQuery;

import java.util.ArrayList;
import java.util.List;

public class SearchQueryMockupFactory {

    // Crea una query de búsqueda válida sin parámetros
    public static SearchQuery createValidNoParamsSearchQuery() {
        SearchQuery query = new SearchQuery();
        query.setQuery(TestConstants.TEST_VALID_SEARCH_QUERY);

        return query;
    }

    // Crea una query de búsqueda válida con parámetros
    public static SearchQuery createValidSearchQueryWithParams() {
        SearchQuery query = new SearchQuery();
        query.setQuery(TestConstants.TEST_VALID_SEARCH_QUERY);

        // Crea los parámetros de la consulta
        List<QueryParameter> parameters = new ArrayList<>();
        parameters.add(new QueryParameter(TestConstants.TEST_VALID_FILTER_NAME_1, TestConstants.TEST_VALID_FILTER_VALUE_1));
        parameters.add(new QueryParameter(TestConstants.TEST_VALID_FILTER_NAME_2, TestConstants.TEST_VALID_FILTER_VALUE_2));
        query.setParameters(parameters);

        return query;
    }

    // Crea una query de búsqueda inválida sin parámetros
    public static SearchQuery createInvalidNoParamsSearchQuery() {
        SearchQuery query = new SearchQuery();
        query.setQuery(TestConstants.TEST_INVALID_SEARCH_QUERY);

        return query;
    }

    // Crea una query de búsqueda inválida con parámetros
    public static SearchQuery createInvalidSearchQueryWithParams() {
        SearchQuery query = new SearchQuery();
        query.setQuery(TestConstants.TEST_INVALID_SEARCH_QUERY);

        // Crea los parámetros de la consulta
        List<QueryParameter> parameters = new ArrayList<>();
        parameters.add(new QueryParameter(TestConstants.TEST_VALID_FILTER_NAME_1, TestConstants.TEST_VALID_FILTER_VALUE_1));
        parameters.add(new QueryParameter(TestConstants.TEST_VALID_FILTER_NAME_2, TestConstants.TEST_VALID_FILTER_VALUE_2));
        query.setParameters(parameters);

        return query;
    }

}// End Class
