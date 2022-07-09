package com.grodriguez.melichallenge;

import com.grodriguez.melisearchcore.model.domain.search.QueryParameter;
import com.grodriguez.melisearchcore.model.domain.search.SearchQuery;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BuildSearchQueryTest {

    // Valida que el método 'buildSearchQuery' retorne correctamente los parámetros que se solicitaron
    @Test
    public void buildSearchQueryTest()
    {
        SearchQuery query = new SearchQuery();
        query.setQuery("test");

        String param1Name = "param1";
        String param1Value = "param1Value";

        String param2Name = "param2";
        String param2Value = "param2Value";

        List<QueryParameter> parameters = new ArrayList<>();
        parameters.add(new QueryParameter(param1Name, param1Value));
        parameters.add(new QueryParameter(param2Name, param2Value));
        query.setParameters(parameters);

        Map<String, String> buildResult = query.buildSearchQuery();
        Assert.assertNotNull(buildResult);
        Assert.assertEquals(buildResult.size(), parameters.size());

        String param1Result = buildResult.get(param1Name);
        Assert.assertNotNull(param1Result);
        Assert.assertEquals(param1Result, param1Value);

        String param2Result = buildResult.get(param2Name);
        Assert.assertNotNull(param2Result);
        Assert.assertEquals(param2Result, param2Value);
    }

}// End Class
