package com.fdv.usersapp.mvp.model;

import android.support.annotation.IntDef;

import com.fdv.usersapp.mvp.contract.RetrofitManager;
import com.fdv.usersapp.restApi.APIClient;
import com.fdv.usersapp.restApi.APIService;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseModel implements RetrofitManager {

    private List<Call> cancellableRetrofitCalls = new ArrayList<>();
    private APIService apiService;
    private Bus bus;

    public BaseModel(Bus bus) {
        this.bus = bus;
        apiService = APIClient.getRetrofitClient();
    }

    /**
     * Stop all cancelable request
     * Must be called in stop or destroy lifecycle fragment/activity methods
     */
    @Override
    public void stopCancellableRetrofitRequest() {
        for (Call call : cancellableRetrofitCalls) {
            if (!call.isCanceled()) {
                removeRetrofitCall(call);
                call.cancel();
            }
        }
    }

    /**
     * Using for retrofit callbacks
     * */
    public static class RetrofitEvent<T>{

        @IntDef({SUCCESS, FAILURE})
        @interface Result{}

        public static final int SUCCESS = 0;
        public static final int FAILURE = 1;

        public @Result int status;
        public Call call;
        public Response<T> response;
        public Throwable throwable;


        public RetrofitEvent(Call call, Response<T> response){
            this.status = SUCCESS;
            this.call = call;
            this.response = response;
        }

        public RetrofitEvent(Call call, Throwable throwable){
            this.status = FAILURE;
            this.call = call;
            this.throwable = throwable;
        }
    }

    protected void post(Object event){
        bus.post(event);
    }

    protected APIService getApiService() {
        return apiService;
    }

    private void addRetrofitCall(Call call) {
        cancellableRetrofitCalls.add(call);
    }

    private void removeRetrofitCall(Call call) {
        cancellableRetrofitCalls.remove(call);
    }

    /**
     * Call API endpoint with retrofit
     * Cancellable option enabled as default value
     */
    protected void callService(Call retrofitCall, Callback callback) {
        callService(retrofitCall, callback, true);
    }

    /**
     * Call API endpoint with retrofit
     *
     * @param isCancellable determines if the request can be cancellable from {@link #stopCancellableRetrofitRequest()}
     */
    protected void callService(Call retrofitCall, Callback callback, boolean isCancellable) {

        /*Check to add into cancellable list*/
        if (isCancellable) addRetrofitCall(retrofitCall);

        /*Make request*/
        retrofitCall.enqueue(callback);
    }

}
