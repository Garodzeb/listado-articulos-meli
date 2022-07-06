package com.grodriguez.melichallenge.validators;

import com.grodriguez.melisearchcore.model.dtos.ItemDetailDTO;

import org.junit.Assert;

public class ItemDetailValidator {

    // Evalua los datos de un artículo
    public static void evaluateGetItemDetailsResult(ItemDetailDTO item) {
        // Valida que el artículo no sea nulo
        Assert.assertNotNull(item);

        // Valida que se hayan traido los datos del artículo
        Assert.assertNotEquals("", item.getId());
        Assert.assertNotEquals("", item.getTitle());
        Assert.assertNotEquals("", item.getCategoryId());
        Assert.assertNotEquals("", item.getCondition());
        Assert.assertNotEquals("", item.getCurrencyId());
        Assert.assertNotEquals("", item.getWarranty());
        Assert.assertNotEquals(-1, item.getAvailableQty());
        Assert.assertNotEquals(-1, item.getPrice());

        // Valida que se hayan traido los datos de envío
        Assert.assertNotNull(item.getShipping());

        // Valida que se hayan traido las fotos del artículo, solo se valida que la lista no sea nula
        Assert.assertNotNull(item.getPictures());
    }


}// End Class
