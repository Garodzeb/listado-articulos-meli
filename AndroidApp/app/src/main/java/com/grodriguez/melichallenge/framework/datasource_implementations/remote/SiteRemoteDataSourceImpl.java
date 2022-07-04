package com.grodriguez.melichallenge.framework.datasource_implementations.remote;

import com.grodriguez.melichallenge.BuildConfig;
import com.grodriguez.melichallenge.framework.network.retrofit.APIException;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.common.responses.CategoryGSonResponseEntity;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.site.IMeliSiteAPIService;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.site.response.CurrencyGSonResponseEntity;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.site.response.SiteMetadataGSonResponseEntity;
import com.grodriguez.melichallenge.framework.network.retrofit.clients.MeliApiRetrofitClient;
import com.grodriguez.melisearchcore.datasource_abstractions.site.ISiteRemoteDataSource;
import com.grodriguez.melisearchcore.model.Category;
import com.grodriguez.melisearchcore.model.Currency;
import com.grodriguez.melisearchcore.model.dtos.SiteMetadataDTO;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Function;
import retrofit2.Response;

public class SiteRemoteDataSourceImpl implements ISiteRemoteDataSource {

    private IMeliSiteAPIService meliSiteService;

    public SiteRemoteDataSourceImpl() {
        this.meliSiteService = MeliApiRetrofitClient.getInstance().create(IMeliSiteAPIService.class);
    }

    @Override
    public Single<SiteMetadataDTO> getSiteMetadata() {
        return meliSiteService.getSiteMetadata(BuildConfig.MELI_API_SITE).map(response -> {
            // Controla si la consulta fue exitosa
            if (response.isSuccessful()) {
                SiteMetadataGSonResponseEntity body = response.body();

                if (body != null) {
                    SiteMetadataDTO result = new SiteMetadataDTO();

                    result.setId(body.getId());
                    result.setName(body.getName());
                    result.setCountryId(body.getCountryId());
                    result.setDefaultCurrencyId(body.getDefaultCurrencyId());

                    List<Category> categories = new ArrayList<>();
                    for(CategoryGSonResponseEntity responseCategory : body.getCategories())
                    {
                        Category cat = new Category();
                        cat.setId(responseCategory.getId());
                        cat.setName(responseCategory.getName());

                        categories.add(cat);
                    }
                    result.setCategories(categories);

                    List<Currency> currencies = new ArrayList<>();
                    for(CurrencyGSonResponseEntity responseCurr : body.getCurrencies())
                    {
                        Currency curr = new Currency();
                        curr.setId(responseCurr.getId());
                        curr.setSymbol(responseCurr.getSymbol());

                        currencies.add(curr);
                    }
                    result.setCurrencies(currencies);

                    return result;
                } else
                {
                    // Si el cuerpo de la consulta es vacío retorna una excepción
                    APIException exception = new APIException();
                    exception.setResponseCode(response.code());
                    exception.setErrorMessage("NULL BODY");

                    throw exception;
                }
            } else {
                // Sino, dispara una excepción con el código y mensaje de error retornados
                APIException exception = new APIException();
                exception.setResponseCode(response.code());
                exception.setErrorMessage(response.message());

                throw exception;
            }
        });
    }

}// End Class
