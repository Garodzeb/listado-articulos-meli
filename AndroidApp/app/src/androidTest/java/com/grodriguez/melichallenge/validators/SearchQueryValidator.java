package com.grodriguez.melichallenge.validators;

import com.grodriguez.melisearchcore.model.domain.search.PagingMetadata;
import com.grodriguez.melisearchcore.model.domain.search.SearchQuery;
import com.grodriguez.melisearchcore.model.dtos.SearchResultDTO;

import org.junit.Assert;

public class SearchQueryValidator {

    // Evalua el resultado obtenido por la búsqueda de artículos realizada
    public static void evaluateSearchResult(SearchResultDTO searchResult) {

        // Valida que ninguno de los campos del DTO sean nulos, las listas pueden estar vacías pero
        // nunca deben ser nulos
        Assert.assertNotNull(searchResult);
        Assert.assertNotNull(searchResult.getResults());
        Assert.assertNotNull(searchResult.getPaging());
        Assert.assertNotNull(searchResult.getAvailableFilters());
        Assert.assertNotNull(searchResult.getSort());
        Assert.assertNotNull(searchResult.getAvailableSorts());

        // Valida que se hayan traído los datos de paginación
        PagingMetadata pagingMeta = searchResult.getPaging();
        Assert.assertNotEquals(-1, pagingMeta.getLimit());
        Assert.assertNotEquals(-1, pagingMeta.getOffset());
        Assert.assertNotEquals(-1, pagingMeta.getPrimaryResults());
        Assert.assertNotEquals(-1, pagingMeta.getTotal());
    }

    // Valida que dos queries de búsqueda sean iguales
    public static void compareSearchQueries(SearchQuery queryA, SearchQuery queryB) {
        Assert.assertEquals(queryA.getQuery(), queryB.getQuery());
        Assert.assertEquals(queryA.getParameters().size(), queryB.getParameters().size());
    }

    // Valida que dos resultados de búsqueda sean iguales
    public static void compareSearchResults(SearchResultDTO resultA, SearchResultDTO resultB) {
        // Valida que los dos resultados sean válidos
        evaluateSearchResult(resultA);
        evaluateSearchResult(resultB);

        // Compara los datos de paginación de los dos resultados
        PagingMetadata resultAPaging = resultA.getPaging();
        PagingMetadata resultBPaging = resultB.getPaging();

        Assert.assertEquals(resultAPaging.getLimit(), resultBPaging.getLimit());
        Assert.assertEquals(resultAPaging.getPrimaryResults(), resultBPaging.getPrimaryResults());
        Assert.assertEquals(resultAPaging.getTotal(), resultBPaging.getTotal());
        Assert.assertEquals(resultAPaging.getOffset(), resultBPaging.getOffset());

        // Compara los datos del ordenamiento aplicado
        Assert.assertEquals(resultA.getSort().getId(), resultB.getSort().getId());
        Assert.assertEquals(resultA.getSort().getName(), resultB.getSort().getName());

        // Compara que se hayan traido la misma cantidad de filtros, ordenamientos, etc
        Assert.assertEquals(resultA.getResults().size(), resultB.getResults().size());
        Assert.assertEquals(resultA.getAvailableSorts().size(), resultB.getAvailableSorts().size());
        Assert.assertEquals(resultA.getFilters().size(), resultB.getFilters().size());
        Assert.assertEquals(resultA.getAvailableFilters().size(), resultB.getAvailableFilters().size());
    }

}// End Class
