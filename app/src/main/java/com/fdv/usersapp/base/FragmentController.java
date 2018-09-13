package com.fdv.usersapp.base;

public interface FragmentController<F> {
    void changeFragmentContained(F fragment, String TAG);

    void changeFragmentContainedNoBackStack(F fragment, String TAG);

    void changeFragmentContainedForceNew(F fragment, String TAG);

    void changeFragmentContainedForceNewNoBackStack(F fragment, String TAG);

    void changeFragmentContainedClearStack(F fragment, String TAG);
}
