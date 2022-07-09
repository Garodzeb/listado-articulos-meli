package com.grodriguez.melichallenge.framework.datasource_implementations.remote;

import com.grodriguez.melichallenge.BuildConfig;
import com.grodriguez.melichallenge.framework.network.retrofit.APIException;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.common.responses.CategoryGSonResponseEntity;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.items.IMeliItemsAPIService;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.items.responses.ItemDetailsGSonResponseEntity;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.items.responses.ItemPictureGSonResponseEntity;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.reviews.IMeliReviewsAPIService;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.reviews.responses.ItemReviewGSonResponseEntity;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.search.IMeliSearchAPIService;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.search.responses.FilterGSonResponseEntity;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.search.responses.FilterValueGSonResponseEntity;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.search.responses.SearchItemGSonResponseEntity;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.search.responses.SearchResultGSonResponseEntity;
import com.grodriguez.melichallenge.framework.network.retrofit.apis.search.responses.SortTypeGSonResponseEntity;
import com.grodriguez.melichallenge.framework.network.retrofit.clients.MeliApiRetrofitClient;
import com.grodriguez.melisearchcore.datasource_abstractions.items.IItemsRemoteDataSource;
import com.grodriguez.melisearchcore.model.domain.site.SiteCategory;
import com.grodriguez.melisearchcore.model.domain.search.Filter;
import com.grodriguez.melisearchcore.model.domain.search.FilterValue;
import com.grodriguez.melisearchcore.model.domain.items.ItemPicture;
import com.grodriguez.melisearchcore.model.domain.search.PagingMetadata;
import com.grodriguez.melisearchcore.model.domain.items.Shipping;
import com.grodriguez.melisearchcore.model.domain.search.SortType;
import com.grodriguez.melisearchcore.model.dtos.ItemDetailDTO;
import com.grodriguez.melisearchcore.model.dtos.ItemRatingDTO;
import com.grodriguez.melisearchcore.model.dtos.SearchItemDTO;
import com.grodriguez.melisearchcore.model.dtos.SearchResultDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Single;

public class ItemsRemoteDataSourceImpl implements IItemsRemoteDataSource {

    private IMeliItemsAPIService itemsAPIService;
    private IMeliReviewsAPIService reviewsAPIService;
    private IMeliSearchAPIService searchAPIService;

    public ItemsRemoteDataSourceImpl() {
        this.itemsAPIService = MeliApiRetrofitClient.getInstance().create(IMeliItemsAPIService.class);
        this.reviewsAPIService = MeliApiRetrofitClient.getInstance().create(IMeliReviewsAPIService.class);
        this.searchAPIService = MeliApiRetrofitClient.getInstance().create(IMeliSearchAPIService.class);
    }

    @Override
    public Single<SearchResultDTO> searchItems(String query, Map<String, String> queryParameters) {
        return searchAPIService.searchItem(BuildConfig.MELI_API_SITE, query, queryParameters)
                .map(response -> {
                    // Controla si la consulta fue exitosa
                    if (response.isSuccessful()) {
                        SearchResultGSonResponseEntity body = response.body();

                        // Valida que el body del mensaje no sea vacío
                        if (body != null) {
                            // Mapea el objeto retornado por la API con el objeto del dominio
                            // esperado por la app
                            SearchResultDTO result = new SearchResultDTO();

                            // Mapea los datos de paginación
                            PagingMetadata pagingMeta = new PagingMetadata();
                            pagingMeta.setLimit(body.getPaging().getLimit());
                            pagingMeta.setOffset(body.getPaging().getOffset());
                            pagingMeta.setTotal(body.getPaging().getTotal());
                            pagingMeta.setPrimaryResults(body.getPaging().getPrimary_results());
                            result.setPaging(pagingMeta);

                            // Mapea el sort aplicado
                            SortType sort = new SortType();
                            sort.setId(body.getSort().getId());
                            sort.setName(body.getSort().getName());
                            result.setSort(sort);

                            // Mapea todos los tipos de ordenamiento disponibles
                            List<SortType> availableSorts = new ArrayList<>();
                            for (SortTypeGSonResponseEntity respSort : body.getAvailableSorts()) {
                                SortType auxSort = new SortType();
                                auxSort.setId(respSort.getId());
                                auxSort.setName(respSort.getName());
                                availableSorts.add(auxSort);
                            }
                            result.setAvailableSorts(availableSorts);

                            // Mapea todos los resultados obtenidos
                            List<SearchItemDTO> searchResults = new ArrayList<>();

                            for (SearchItemGSonResponseEntity respItem : body.getResults()) {
                                // Mapea los datos del artículo
                                SearchItemDTO item = new SearchItemDTO();
                                item.setId(respItem.getId());
                                item.setTitle(respItem.getTitle());
                                item.setCategoryId(respItem.getCategoryId());
                                item.setCondition(respItem.getCondition());
                                item.setPrice(respItem.getPrice());
                                item.setCurrencyId(respItem.getCurrencyId());
                                item.setThumbnailUrl(respItem.getThumbnailUrl());

                                // Mapea los datos de envío
                                Shipping itemShipping = new Shipping();
                                itemShipping.setMode(respItem.getShipping().getMode());
                                itemShipping.setLocalPickup(respItem.getShipping().isLocalPickUp());
                                itemShipping.setStorePickup(respItem.getShipping().isStorePickUp());
                                itemShipping.setFreeShipping(respItem.getShipping().isFreeShipping());
                                item.setShipping(itemShipping);

                                searchResults.add(item);
                            }
                            result.setResults(searchResults);

                            // Mapea todos los filtros aplicados
                            List<Filter> appliedFilters = new ArrayList<>();
                            for (FilterGSonResponseEntity respFilter : body.getFilters()) {
                                Filter filter = new Filter();
                                filter.setId(respFilter.getId());
                                filter.setName(respFilter.getName());
                                filter.setType(respFilter.getType());

                                // Mapea todos los valores posibles para el filtro
                                List<FilterValue> values = new ArrayList<>();
                                for (FilterValueGSonResponseEntity respValue : respFilter.getValues()) {
                                    FilterValue fValue = new FilterValue();
                                    fValue.setId(respValue.getId());
                                    fValue.setName(respValue.getName());
                                    fValue.setResults(respValue.getResults());

                                    if (respValue.getPathFromRoot() != null && respValue.getPathFromRoot().size() > 0) {
                                        for (CategoryGSonResponseEntity respCat : respValue.getPathFromRoot()) {
                                            SiteCategory cat = new SiteCategory();
                                            cat.setId(respCat.getId());
                                            cat.setName(respCat.getName());
                                            fValue.getPathFromRoot().add(cat);
                                        }
                                    }
                                    values.add(fValue);
                                }
                                filter.setValues(values);
                                appliedFilters.add(filter);
                            }
                            result.setFilters(appliedFilters);

                            // Mapea todos los filtros disponibles
                            List<Filter> availableFilters = new ArrayList<>();
                            for (FilterGSonResponseEntity respFilter : body.getAvailableFilters()) {
                                Filter filter = new Filter();
                                filter.setId(respFilter.getId());
                                filter.setName(respFilter.getName());
                                filter.setType(respFilter.getType());

                                // Mapea todos los valores posibles para el filtro
                                List<FilterValue> values = new ArrayList<>();
                                for (FilterValueGSonResponseEntity respValue : respFilter.getValues()) {
                                    FilterValue fValue = new FilterValue();
                                    fValue.setId(respValue.getId());
                                    fValue.setName(respValue.getName());
                                    fValue.setResults(respValue.getResults());

                                    // Mapea la ruta a la categoría raíz de la categoría asociada al artículo
                                    if (respValue.getPathFromRoot() != null && respValue.getPathFromRoot().size() > 0) {
                                        for (CategoryGSonResponseEntity respCat : respValue.getPathFromRoot()) {
                                            SiteCategory cat = new SiteCategory();
                                            cat.setId(respCat.getId());
                                            cat.setName(respCat.getName());
                                            fValue.getPathFromRoot().add(cat);
                                        }
                                    }
                                    values.add(fValue);
                                }
                                filter.setValues(values);
                                availableFilters.add(filter);
                            }
                            result.setAvailableFilters(availableFilters);

                            return result;
                        } else {
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

    @Override
    public Single<ItemDetailDTO> getItemDetails(String itemCode) {
        return itemsAPIService.getItemDetails(itemCode).map(response -> {
            // Controla si la consulta fue exitosa
            if (response.isSuccessful()) {
                ItemDetailsGSonResponseEntity body = response.body();

                // Valida que el cuerpo del mensaje no sea vacío
                if (body != null) {
                    // Mapea los datos del artículo
                    ItemDetailDTO result = new ItemDetailDTO();
                    result.setId(body.getId());
                    result.setTitle(body.getTitle());
                    result.setWarranty(body.getWarranty());
                    result.setCategoryId(body.getCategoryId());
                    result.setCurrencyId(body.getCurrencyId());
                    result.setPrice(body.getPrice());
                    result.setCondition(body.getCondition());
                    result.setAvailableQty(body.getAvailableQty());

                    // Mapea los datos de envío asociados al artículo
                    Shipping shipping = new Shipping();
                    shipping.setMode(body.getShipping().getMode());
                    shipping.setFreeShipping(body.getShipping().isFreeShipping());
                    shipping.setLocalPickup(body.getShipping().isLocalPickUp());
                    shipping.setStorePickup(body.getShipping().isStorePickUp());

                    result.setShipping(shipping);

                    // Mapea todas las fotos asociadas al artículo
                    List<ItemPicture> pictures = new ArrayList<>();
                    for (ItemPictureGSonResponseEntity responsePic : body.getPictures()) {
                        ItemPicture picture = new ItemPicture();
                        picture.setId(responsePic.getId());
                        picture.setMaxSize(responsePic.getMaxSize());
                        picture.setQuality(responsePic.getQuality());
                        picture.setSize(responsePic.getSize());
                        picture.setUrl(responsePic.getUrl());
                        picture.setSecureUrl(responsePic.getSecureUrl());

                        pictures.add(picture);
                    }
                    result.setPictures(pictures);

                    return result;
                } else {
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

    @Override
    public Single<ItemRatingDTO> getItemRatings(String itemCode) {
        return reviewsAPIService.getItemReview(itemCode).map(response -> {
            // Controla si la consulta fue exitosa
            if (response.isSuccessful()) {
                ItemReviewGSonResponseEntity body = response.body();

                // Valida que el cuerpo del mensaje no sea nulo
                if (body != null) {
                    // Mapea el puntaje promedio de las reseñas del artículo
                    ItemRatingDTO result = new ItemRatingDTO();
                    result.setRatingAvg(body.getRatingAvg());

                    // Mapea los datos de paginación
                    PagingMetadata pagingMeta = new PagingMetadata();
                    pagingMeta.setLimit(body.getPaging().getLimit());
                    pagingMeta.setOffset(body.getPaging().getOffset());
                    pagingMeta.setTotal(body.getPaging().getTotal());
                    pagingMeta.setPrimaryResults(body.getPaging().getPrimary_results());
                    result.setPaging(pagingMeta);

                    return result;
                } else {
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
