package com.grodriguez.melisearchcore.datasource_abstractions.items;

import com.grodriguez.melisearchcore.model.dtos.ItemDetailDTO;
import com.grodriguez.melisearchcore.model.dtos.ItemRatingDTO;
import com.grodriguez.melisearchcore.model.dtos.SearchResultDTO;

import java.util.Map;

import io.reactivex.rxjava3.core.Single;

public interface IItemssRemoteDataSource {

    Single<SearchResultDTO> searchItems(Map<String, String> queryParameters);

    Single<ItemDetailDTO> getItemDetails(String itemCode);

    Single<ItemRatingDTO> getItemRatings(String itemCode);

}// End Interface
