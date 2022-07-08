package com.grodriguez.melichallenge.presentation.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public abstract class BaseViewModel extends ViewModel {

    // region LiveData

    private MutableLiveData<UIState> uiState;
    private CompositeDisposable disposableObservers;

    public BaseViewModel() {
        disposableObservers = new CompositeDisposable();
    }

    // region GET-SET

    public MutableLiveData<UIState> getUIState() {
        if (uiState == null)
            uiState = new MutableLiveData<>();

        return uiState;
    }

    // endregion

    //endregion

    protected void addDisposableObserver(Disposable observer) {
        disposableObservers.add(observer);
    }

    public void dispose() {
        disposableObservers.dispose();
    }

}// End Class
