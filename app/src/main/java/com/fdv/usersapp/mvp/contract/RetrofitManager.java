package com.fdv.usersapp.mvp.contract;

public interface RetrofitManager {

    /**
     * Stop all cancelable request
     * Must be called in stop or destroy lifecycle fragment/activity methods
     */
    void stopCancellableRetrofitRequest();

}
