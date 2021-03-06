package com.fdv.usersapp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fdv.usersapp.base.BaseActivity;
import com.fdv.usersapp.fragments.UserDetailsFragment;

public class GenericActivity extends BaseActivity {

    public static final String FRAGMENT_TAG = "FragmentTag";

    /**
     * This activity waits for {@link #FRAGMENT_TAG} in order to load the specific fragment on it
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();

        /*Default toolbar setup*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadSpecificFragment(getIntent().getStringExtra(FRAGMENT_TAG));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void loadSpecificFragment(String fragmentTag) {
        try {
            switch (fragmentTag) {
                case UserDetailsFragment.TAG:
                    changeFragmentContainedNoBackStack(
                            UserDetailsFragment.getInstance(getIntent().getParcelableExtra(UserDetailsFragment.USER_DATA)),
                            UserDetailsFragment.TAG);
                    break;

                /*Add more fragments if needed*/
            }
        } catch (NullPointerException e) {
            Log.e("GenericActivity", "DataRequired: " + e.getMessage());
        }
    }
}
