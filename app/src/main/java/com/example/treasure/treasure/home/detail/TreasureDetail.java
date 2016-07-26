package com.example.treasure.treasure.home.detail;

import com.google.gson.annotations.SerializedName;

/**
 * 请求数据
 * Created by 93432 on 2016/7/21.
 */
public class TreasureDetail {

    @SerializedName("TreasureID")
    private final int treasureId;

    @SerializedName("PagerSize")
    private final int pageSize;

    @SerializedName("currentPage")
    private final int currentPage;

    public TreasureDetail(int treasureId) {
        this.treasureId = treasureId;
        this.pageSize = 20;
        this.currentPage = 1;
    }
}
