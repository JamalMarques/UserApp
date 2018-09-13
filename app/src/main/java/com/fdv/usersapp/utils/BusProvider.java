package com.fdv.usersapp.utils;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class BusProvider {

    private static final Bus busInstance = new MainPostingBus();

    public static Bus getInstance() {
        return busInstance;
    }

    public static void register(Object... objects) {
        for (Object o : objects) {
            if (o != null) {
                busInstance.register(o);
            }
        }
    }

    public static void unregister(Object... objects) {
        for (Object o : objects) {
            if (o != null) {
                busInstance.unregister(o);
            }
        }
    }


    public static class MainPostingBus extends Bus {

        private final Handler handler = new Handler(Looper.getMainLooper());

        public MainPostingBus() {
            super(ThreadEnforcer.ANY);
        }

        @Override
        public void post(final Object event) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                super.post(event);
            } else {
                handler.post(() -> super.post(event));
            }
        }
    }
}
