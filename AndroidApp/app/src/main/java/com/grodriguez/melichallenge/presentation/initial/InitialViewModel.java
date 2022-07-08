package com.grodriguez.melichallenge.presentation.initial;

import androidx.lifecycle.MutableLiveData;

import com.grodriguez.melichallenge.BuildConfig;
import com.grodriguez.melichallenge.presentation.ui.BaseViewModel;
import com.grodriguez.melichallenge.presentation.ui.UIState;
import com.grodriguez.melisearchcore.model.dtos.SiteMetadataDTO;
import com.grodriguez.melisearchcore.repositories.SiteRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class InitialViewModel extends BaseViewModel {

    SiteRepository siteRepository;

    // region LiveData
    MutableLiveData<Boolean> savedMetadata;

    // region GET-SET

    public MutableLiveData<Boolean> getSavedMetadata() {

        if (savedMetadata == null)
            savedMetadata = new MutableLiveData<>();

        return savedMetadata;
    }

    // endregion

    // endregion

    public InitialViewModel(SiteRepository siteRepository) {
        super();
        this.siteRepository = siteRepository;
    }

    public void getSiteMetadata() {
        // Busca la metadata del sitio en la API y guarda el resultado en memoria
        Disposable observer = siteRepository.getSiteMetadata(BuildConfig.MELI_API_SITE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<SiteMetadataDTO>() {
                    @Override
                    public void onSuccess(@NonNull SiteMetadataDTO metadata) {
                        // Si encuentra la metadata, la guarda en memoria
                        saveSiteMetadata(metadata);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getUIState().setValue(new UIState(e));
                    }
                });

        addDisposableObserver(observer);
    }

    private void saveSiteMetadata(SiteMetadataDTO metadata) {
        Disposable observable = siteRepository.saveSiteMetadata(metadata)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        getSavedMetadata().postValue(true);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getUIState().setValue(new UIState(e));
                    }
                });

        addDisposableObserver(observable);
    }

}// End Class
