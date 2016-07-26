package com.example.treasure.treasure.home.detail;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

/**
 * 宝藏详情业务视图
 * Created by 93432 on 2016/7/21.
 */
public interface TreasureDetailView extends MvpView{

    void showMessage(String msg);

    void setData(List<TreasureDetailResult> results);
}
