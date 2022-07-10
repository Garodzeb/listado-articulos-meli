package com.grodriguez.melichallenge.presentation.product_detail;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.grodriguez.melichallenge.BuildConfig;
import com.grodriguez.melichallenge.framework.utils.AppConstants;
import com.grodriguez.melichallenge.framework.utils.AppUtils;
import com.grodriguez.melichallenge.framework.utils.UIStatus;
import com.grodriguez.melichallenge.presentation.ui.BaseViewModel;
import com.grodriguez.melichallenge.presentation.ui.UIState;
import com.grodriguez.melisearchcore.model.domain.items.ItemDetail;
import com.grodriguez.melisearchcore.model.domain.items.ItemPicture;
import com.grodriguez.melisearchcore.model.domain.items.ItemRating;
import com.grodriguez.melisearchcore.model.domain.site.SiteCurrency;
import com.grodriguez.melisearchcore.model.dtos.ItemDetailDTO;
import com.grodriguez.melisearchcore.model.dtos.ItemRatingDTO;
import com.grodriguez.melisearchcore.model.dtos.SiteMetadataDTO;
import com.grodriguez.melisearchcore.repositories.ItemsRepository;
import com.grodriguez.melisearchcore.repositories.SiteRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProductDetailViewModel extends BaseViewModel {

    private SiteRepository siteRepository;
    private ItemsRepository itemsRepository;

    private MutableLiveData<Integer> currentPictureIndex;
    private MutableLiveData<ItemPicture> currentPicture;
    private MutableLiveData<ItemDetail> currentItem;

    // region LiveData

    public MutableLiveData<Integer> getCurrentPictureIndex() {
        if (currentPictureIndex == null)
            currentPictureIndex = new MutableLiveData<>(0);

        return currentPictureIndex;
    }

    public MutableLiveData<ItemPicture> getcurrentPicture() {
        if (currentPicture == null)
            currentPicture = new MutableLiveData<>();

        return currentPicture;
    }

    public MutableLiveData<ItemDetail> getCurrentItem() {
        if (currentItem == null)
            currentItem = new MutableLiveData<>();

        return currentItem;
    }

    // endregion

    public ProductDetailViewModel(SiteRepository siteRepository, ItemsRepository itemsRepository) {
        super();
        this.siteRepository = siteRepository;
        this.itemsRepository = itemsRepository;
    }

    // region public methods

    public void loadItemDetails(String itemId) {
        Disposable disposable = itemsRepository.getItemDetails(itemId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ItemDetailDTO>() {
                    @Override
                    public void onSuccess(@NonNull ItemDetailDTO itemDetailDTO) {
                        ItemDetail item = new ItemDetail(itemDetailDTO);
                        loadItemRatings(item);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getUIState().setValue(new UIState(e));
                    }
                });

        addDisposableObserver(disposable);
    }

    // Retorna la siguiente foto para mostrar del artículo
    public void getNextPicture() {
        // Valida que el artículo no sea nulo
        if (getCurrentItem().getValue() != null) {
            List<ItemPicture> pictures = getCurrentItem().getValue().getPictures();

            // Valida que el artículo tenga fotos para mostrar
            if (pictures != null && pictures.size() > 0) {
                // Busca el siguiente índice para mostrar
                int index = (getCurrentPictureIndex().getValue() != null) ? getCurrentPictureIndex().getValue() : 0;
                index = index + 1;

                // Si ya se estaba mostrando la última foto del artículp, vuelve a mostrar la primera foto
                if (index >= pictures.size())
                    index = 0;

                getcurrentPicture().setValue(pictures.get(index));
                getCurrentPictureIndex().setValue(index);
            }
        }
    }

    public void getPreviousPicture() {
        // Valida que el artículo no sea nulo
        if (getCurrentItem().getValue() != null) {
            List<ItemPicture> pictures = getCurrentItem().getValue().getPictures();

            // valida que el artículo tenga fotos para mostrar
            if (pictures != null) {
                // Busca el siguiente índice para mostrar
                int index = (getCurrentPictureIndex().getValue() != null) ? getCurrentPictureIndex().getValue() : 0;
                index = index - 1;

                // Si se llego a la primera foto del artículo, muestra la última foto del artículo
                if (index < 0)
                    index = pictures.size() - 1;

                getcurrentPicture().setValue(pictures.get(index));
                getCurrentPictureIndex().setValue(index);
            }
        }
    }

    // endregion

    // region private methods

    // Busca las reseñas sobre el artículo seleccionado
    private void loadItemRatings(ItemDetail item) {
        Disposable disposable = itemsRepository.getItemRatings(item.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ItemRatingDTO>() {
                    @Override
                    public void onSuccess(@NonNull ItemRatingDTO itemRatingDTO) {
                        item.setItemRating(new ItemRating(itemRatingDTO));
                        loadItemsCurrencyInfo(item);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getUIState().setValue(new UIState(e));
                    }
                });

        addDisposableObserver(disposable);
    }

    // Agrega la información sobre la moneda tomando los datos
    // guardados en memoria cuando se inicializó la aplicación
    private void loadItemsCurrencyInfo(ItemDetail item) {

        Disposable observer = siteRepository.getSiteMetadata(BuildConfig.MELI_API_SITE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<SiteMetadataDTO>() {
                    @Override
                    public void onSuccess(@NonNull SiteMetadataDTO siteMetadata) {

                        // Busca la moneda asociada al artículo
                        int i = 0;
                        int currenciesQty = siteMetadata.getCurrencies().size();
                        SiteCurrency currency = null;

                        while (i < currenciesQty && currency == null) {
                            SiteCurrency curr = siteMetadata.getCurrencies().get(i);

                            if (curr.getId().equals(item.getCurrencyId()))
                                currency = curr;

                            i++;
                        }

                        // Si encuentra la moneda la asocia al artículo
                        if (currency != null)
                            item.setCurrency(currency);

                        // Informa a la interfaz que se terminaron de cargar los datos
                        getUIState().setValue(new UIState(UIStatus.UI_IDLE));
                        getCurrentItem().setValue(item);
                        getNextPicture();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        AppUtils.logError(e);
                    }
                });

        addDisposableObserver(observer);
    }

    // endregion

}// End Class
