package com.grodriguez.melichallenge.framework.datasource_implementations.remote;

import com.grodriguez.melichallenge.framework.network.retrofit.APIException;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.common.responses.CategoryGSonResponseEntity;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.site.IMeliSiteAPIService;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.site.response.CurrencyGSonResponseEntity;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.site.response.SiteMetadataGSonResponseEntity;
import com.grodriguez.melichallenge.framework.network.retrofit.clients.MeliApiRetrofitClient;
import com.grodriguez.melisearchcore.datasource_abstractions.site.ISiteRemoteDataSource;
import com.grodriguez.melisearchcore.model.domain.site.SiteCategory;
import com.grodriguez.melisearchcore.model.domain.site.SiteCurrency;
import com.grodriguez.melisearchcore.model.dtos.SiteMetadataDTO;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class SiteRemoteDataSourceImpl implements ISiteRemoteDataSource {

    private IMeliSiteAPIService meliSiteService;

    public SiteRemoteDataSourceImpl() {
        this.meliSiteService = MeliApiRetrofitClient.getInstance().create(IMeliSiteAPIService.class);
    }

    @Override
    public Single<SiteMetadataDTO> getSiteMetadata(String siteId) {
        return meliSiteService.getSiteMetadata(siteId).map(response -> {
            // Controla si la consulta fue exitosa
            if (response.isSuccessful()) {
                SiteMetadataGSonResponseEntity body = response.body();

                if (body != null) {
                    SiteMetadataDTO result = new SiteMetadataDTO();

                    result.setId(body.getId());
                    result.setName(body.getName());
                    result.setCountryId(body.getCountryId());
                    result.setDefaultCurrencyId(body.getDefaultCurrencyId());

                    List<SiteCategory> categories = new ArrayList<>();
                    for(CategoryGSonResponseEntity responseCategory : body.getCategories())
                    {
                        SiteCategory cat = new SiteCategory();
                        cat.setId(responseCategory.getId());
                        cat.setName(responseCategory.getName());

                        categories.add(cat);
                    }
                    result.setCategories(categories);

                    List<SiteCurrency> currencies = new ArrayList<>();
                    for(CurrencyGSonResponseEntity responseCurr : body.getCurrencies())
                    {
                        SiteCurrency curr = new SiteCurrency();
                        curr.setId(responseCurr.getId());
                        curr.setSymbol(responseCurr.getSymbol());

                        currencies.add(curr);
                    }
                    result.setCurrencies(currencies);

                    return result;
                } else
                {
                    // Si el cuerpo de la consulta es vac??o retorna una excepci??n
                    APIException exception = new APIException();
                    exception.setResponseCode(response.code());
                    exception.setErrorMessage("NULL BODY");

                    throw exception;
                }
            } else {
                // Sino, dispara una excepci??n con el c??digo y mensaje de error retornados
                APIException exception = new APIException();
                exception.setResponseCode(response.code());
                exception.setErrorMessage(response.message());

                throw exception;
            }
        });
    }

}// End Class
