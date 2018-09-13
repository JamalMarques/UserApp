package com.fdv.usersapp.activities;

import android.os.Bundle;

import com.fdv.usersapp.fragments.UsersFragment;
import com.fdv.usersapp.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private final String seed = "seed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();

        changeFragmentContainedNoBackStack(UsersFragment.getInstance(seed), UsersFragment.TAG);
    }
}
