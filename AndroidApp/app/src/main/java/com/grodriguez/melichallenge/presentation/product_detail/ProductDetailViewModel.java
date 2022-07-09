package com.grodriguez.melichallenge.presentation.product_detail;

import com.grodriguez.melichallenge.presentation.ui.BaseViewModel;
import com.grodriguez.melisearchcore.repositories.ItemsRepository;
import com.grodriguez.melisearchcore.repositories.SiteRepository;

public class ProductDetailViewModel extends BaseViewModel {

    private SiteRepository siteRepository;
    private ItemsRepository itemsRepository;

    // region LiveData

    // endregion

    public ProductDetailViewModel(SiteRepository siteRepository, ItemsRepository itemsRepository) {
        super();
        this.siteRepository = siteRepository;
        this.itemsRepository = itemsRepository;
    }

}// End Class
