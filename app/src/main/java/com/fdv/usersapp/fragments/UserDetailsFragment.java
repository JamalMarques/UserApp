package com.fdv.usersapp.fragments;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fdv.usersapp.R;
import com.fdv.usersapp.base.BaseFragment;
import com.fdv.usersapp.models.dataShare.UserDataContainer;
import com.fdv.usersapp.mvp.contract.UserDetailsContract;
import com.fdv.usersapp.mvp.model.UserDetailsModel;
import com.fdv.usersapp.mvp.presenter.UserDetailsPresenter;
import com.fdv.usersapp.mvp.view.UserDetailsView;
import com.fdv.usersapp.utils.BusProvider;

public class UserDetailsFragment extends BaseFragment<UserDetailsContract.Presenter> {

    public static final String TAG = "UserDetailsFragment";

    public static final String USER_DATA = "UserData";

    /**
     * This method must be called to create any fragment instance of this class type
     */
    public static UserDetailsFragment getInstance(@NonNull UserDataContainer dataContainer) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(USER_DATA, dataContainer);
        UserDetailsFragment fragment = new UserDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_details_layout, container, false);
    }

    @Override
    protected UserDetailsContract.Presenter getPresenter() {
        UserDataContainer data = getArguments().getParcelable(USER_DATA);
        return presenter = new UserDetailsPresenter(
                new UserDetailsView(getBaseActivity(), BusProvider.getInstance()),
                new UserDetailsModel(BusProvider.getInstance(), data.getUserLargePicture(), data.getUserName(), data.getUserFirstName(),
                        data.getUserLastName(), data.getUserEmail(), data.getUserAddress()));
    }

    @Override
    public void onStart() {
        super.onStart();
        BusProvider.register(presenter);
        presenter.onStart();
    }

    @Override
    public void onStop() {
        BusProvider.unregister(presenter);
        super.onStop();
    }
}
