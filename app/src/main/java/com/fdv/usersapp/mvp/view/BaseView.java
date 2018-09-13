package com.fdv.usersapp.mvp.view;

import android.content.Context;
import android.content.Intent;

import com.fdv.usersapp.base.BaseActivity;
import com.fdv.usersapp.base.FragmentController;
import com.squareup.otto.Bus;

import java.lang.ref.WeakReference;

public class BaseView<T extends BaseActivity> {

    private WeakReference<T> activityReference;
    protected Bus bus;

    public BaseView(T activity, Bus bus) {
        this.activityReference = new WeakReference<>(activity);
        this.bus = bus;
    }

    public T getActivity() {
        return this.activityReference.get();
    }

    public Context getContext() {
        return this.getActivity();
    }

    public FragmentController getFragmentController() {
        return activityReference.get();
    }

    public void startActivity(Intent intent) {
        if (this.getContext() != null) {
            this.getContext().startActivity(intent);
        }
    }

    public void finishActivity() {
        if (this.getActivity() != null) {
            this.getActivity().finish();
        }
    }

    public void post(Object event) {
        bus.post(event);
    }
}