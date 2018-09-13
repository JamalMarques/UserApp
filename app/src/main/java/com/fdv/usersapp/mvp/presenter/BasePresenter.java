package com.fdv.usersapp.mvp.presenter;

import com.fdv.usersapp.mvp.contract.PresenterCoreInterface;
import com.fdv.usersapp.mvp.contract.RetrofitManager;

public class BasePresenter<V, M extends RetrofitManager> implements PresenterCoreInterface {

    protected final V view;
    protected final M model;

    public BasePresenter(V view, M model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onDestroy() {
        /*Stop all cancellable requests*/
        model.stopCancellableRetrofitRequest();
    }
}