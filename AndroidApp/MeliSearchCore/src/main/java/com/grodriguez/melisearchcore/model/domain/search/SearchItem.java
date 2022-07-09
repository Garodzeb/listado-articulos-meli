package com.grodriguez.melisearchcore.model.domain.search;

import com.grodriguez.melisearchcore.model.domain.items.BaseItem;
import com.grodriguez.melisearchcore.model.domain.items.Shipping;
import com.grodriguez.melisearchcore.model.domain.site.SiteCurrency;
import com.grodriguez.melisearchcore.model.dtos.SearchItemDTO;

// TODO: reemplazar el resultado de buscar un artículo de SearchItemDTO a SearchItem
public class SearchItem extends BaseItem {

    private String thumbnailUrl = "";
    private boolean isFavorite = false;

    public SearchItem() {
    }

    public SearchItem(SearchItemDTO dto) {
        this.id = dto.getId();
        this.title = dto.getTitle();
        this.currencyId = dto.getCurrencyId();
        this.shipping = dto.getShipping();
        this.categoryId = dto.getCategoryId();
        this.condition = dto.getCondition();
        this.price = dto.getPrice();
        this.thumbnailUrl = dto.getThumbnailUrl();
        this.isFavorite = false;
    }

    // region GET-SET

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    // endregion

}// End Class
