package com.grodriguez.melichallenge.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.grodriguez.melichallenge.application.AppDependenciesContainer;
import com.grodriguez.melichallenge.presentation.initial.InitialViewModel;
import com.grodriguez.melichallenge.presentation.product_detail.ProductDetailViewModel;
import com.grodriguez.melichallenge.presentation.search_product.MainViewModel;
import com.grodriguez.melisearchcore.repositories.ItemsRepository;
import com.grodriguez.melisearchcore.repositories.SiteRepository;

public class ViewModelsFactory implements ViewModelProvider.Factory {

    ItemsRepository itemsRepository;
    SiteRepository siteRepository;

    public ViewModelsFactory(ItemsRepository itemsRepository, SiteRepository siteRepository) {
        this.itemsRepository = itemsRepository;
        this.siteRepository = siteRepository;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(InitialViewModel.class))
            return (T) new InitialViewModel(siteRepository, itemsRepository);

        if (modelClass.isAssignableFrom(MainViewModel.class))
            return (T) new MainViewModel(itemsRepository, siteRepository);

        if (modelClass.isAssignableFrom(ProductDetailViewModel.class))
            return (T) new ProductDetailViewModel(siteRepository, itemsRepository);

        throw new IllegalArgumentException("Invalid ViewModel");
    }

}//End Class
