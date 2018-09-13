package com.fdv.usersapp.mvp.presenter;

import com.fdv.usersapp.models.User;
import com.fdv.usersapp.models.dataShare.UserDataContainer;
import com.fdv.usersapp.mvp.contract.UsersContract;
import com.squareup.otto.Subscribe;

import static com.fdv.usersapp.mvp.model.BaseModel.RetrofitEvent.FAILURE;
import static com.fdv.usersapp.mvp.model.BaseModel.RetrofitEvent.SUCCESS;

public class UsersPresenter extends BasePresenter<UsersContract.View, UsersContract.Model> implements UsersContract.Presenter{

    public UsersPresenter(UsersContract.View view, UsersContract.Model model) {
        super(view, model);
    }

    @Override
    public void onStart() {
        if (!model.isFirstCallDone()){
            view.setLoadingState(true);
            model.callUserPagination(model.getLastPage(), model.getPageSize());
        }
    }

    /*Bus Notifications*/
    @Subscribe
    public void onPaginationEvent(UsersContract.Model.OnPaginationEvent event){
        switch (event.status){
            case SUCCESS:
                view.setLoadingState(false);
                view.addItemsGrid(event.response.body().getResults());
                break;

            case FAILURE:
                view.setLoadingState(false);
                view.showApiErrorSnackbar();
                break;
        }
        view.setLoadingStatePagination(false);
    }

    @Subscribe
    public void onLoadNextPageEvent(UsersContract.View.LoadNextPageEvent event){
        view.setLoadingStatePagination(true);
        model.setLastPage(model.getLastPage() + 1);
        model.callUserPagination(model.getLastPage(), model.getPageSize());
    }

    @Subscribe
    public void onItemClickEvent(UsersContract.View.OnItemClickEvent event){
        User user = event.user;
        view.showUserDetails(new UserDataContainer(user.getPicture().getLarge(),user.getLogin().getUsername(), user.getName().getFirst(),
                user.getName().getLast(), user.getEmail(), user.getLocation().toString()));
    }
}
