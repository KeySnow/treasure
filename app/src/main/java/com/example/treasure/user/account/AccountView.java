package com.example.treasure.user.account;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by 93432 on 2016/7/15.
 */
public interface AccountView extends MvpView {

    void showProgress();

    void hideProgress();

    void showMessage(String msg);

    /**更新头像*/
    void updatePhoto(String url);
}
