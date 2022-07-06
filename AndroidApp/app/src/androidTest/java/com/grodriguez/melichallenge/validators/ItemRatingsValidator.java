package com.grodriguez.melichallenge.validators;

import com.grodriguez.melisearchcore.model.domain.PagingMetadata;
import com.grodriguez.melisearchcore.model.dtos.ItemRatingDTO;

import org.junit.Assert;

public class ItemRatingsValidator {

    // Evalua los datos de las reseñas de un artículo
    public static void evaluateItemRatingsResult(ItemRatingDTO rating) {
        Assert.assertNotNull(rating);
        Assert.assertNotNull(rating.getPaging());
        Assert.assertNotEquals(-1, rating.getRatingAvg());

        // Valida que se hayan traído los datos de paginación
        PagingMetadata pagingMeta = rating.getPaging();
        Assert.assertNotEquals(-1, pagingMeta.getLimit());
        Assert.assertNotEquals(-1, pagingMeta.getOffset());
        Assert.assertNotEquals(-1, pagingMeta.getPrimaryResults());
        Assert.assertNotEquals(-1, pagingMeta.getTotal());
    }

}// End Class
