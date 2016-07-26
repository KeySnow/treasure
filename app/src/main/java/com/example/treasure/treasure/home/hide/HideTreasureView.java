package com.example.treasure.treasure.home.hide;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by 93432 on 2016/7/20.
 */
public interface HideTreasureView extends MvpView {

    void showProgress();
    void hideProgress();
    void showMessage(String msg);
    void navigateToHome();
}
