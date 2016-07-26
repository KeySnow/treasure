package com.example.treasure.user.login;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * 登陆业务相关视图
 * Created by 93432 on 2016/7/12.
 */
public interface LoginView extends MvpView{

    /**显示登陆中的Loading视图*/
    void showProgress();
    /**隐藏登陆中的Loading视图*/
    void hideProgress();
    /**显示信息*/
    void showMessage(String msg);
    /**导航到Home界面*/
    void navigateToHome();
}
