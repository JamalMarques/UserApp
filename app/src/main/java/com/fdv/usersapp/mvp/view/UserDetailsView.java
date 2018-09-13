package com.fdv.usersapp.mvp.view;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fdv.usersapp.R;
import com.fdv.usersapp.base.BaseActivity;
import com.fdv.usersapp.mvp.contract.UserDetailsContract;
import com.fdv.usersapp.utils.TextHelper;
import com.squareup.otto.Bus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDetailsView extends BaseView<BaseActivity> implements UserDetailsContract.View {

    @BindView(R.id.iv_user)
    public ImageView ivUser;
    @BindView(R.id.tv_user_first_name)
    public TextView tvUserFirstName;
    @BindView(R.id.tv_user_last_name)
    public TextView tvUserLastName;
    @BindView(R.id.tv_email)
    public TextView tvEmail;
    @BindView(R.id.tv_address)
    public TextView tvAddress;

    public UserDetailsView(BaseActivity activity, Bus bus) {
        super(activity, bus);

        ButterKnife.bind(this, activity);
    }

    @Override
    public void showUserData(String image, String username, String firstName, String lastName, String email, String address) {

        getActivity().setToolBarTitle(username);

        Glide.with(getContext())
                .load(image)
                .into(ivUser);

        tvUserFirstName.setText(TextHelper.textToUpperCase(firstName));
        tvUserLastName.setText(TextHelper.textToUpperCase(lastName));
        tvEmail.setText(email);
        tvAddress.setText(address);
    }
}
