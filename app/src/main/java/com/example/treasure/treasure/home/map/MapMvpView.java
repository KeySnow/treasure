package com.example.treasure.treasure.home.map;

import com.example.treasure.treasure.Treasure;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

/**
 * Created by 93432 on 2016/7/19.
 */
public interface MapMvpView extends MvpView{

    void showMessage(String msg);

    void setData(List<Treasure> datas);
}
