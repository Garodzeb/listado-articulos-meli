package com.grodriguez.melichallenge.validators;

import com.grodriguez.melichallenge.framework.network.retrofit.APIException;

import org.junit.Assert;

public class APIExceptionValidator {

    // Valia que una excepción de tipo APIException tenga toda la información referente al error
    public static void evaluateAPIExceptionData(Exception ex) {
        // Evalua que la excepción devuelta sea del tipo APIException
        Assert.assertEquals(APIException.class, ex.getClass());

        // Valida que se hayan seteado todos los datos del error en la excepción
        APIException apiError = (APIException) ex;
        Assert.assertNotEquals(0, apiError.getResponseCode());
        Assert.assertNotEquals("", apiError.getErrorMessage());
    }

}// End Class
