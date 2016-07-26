package com.example.treasure.treasure;

import com.example.treasure.treasure.home.detail.TreasureDetail;
import com.example.treasure.treasure.home.detail.TreasureDetailResult;
import com.example.treasure.treasure.home.hide.HideTreasure;
import com.example.treasure.treasure.home.hide.HideTreasureResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by 93432 on 2016/7/19.
 */
public interface TreasureApi {

    @POST("/Handler/TreasureHandler.ashx?action=show")
    Call<List<Treasure>> getTreasureInArea(@Body Area body);

    @POST("/Handler/TreasureHandler.ashx?action=hide")
    Call<HideTreasureResult> hideTreasure(@Body HideTreasure body);

    @POST("/Handler/TreasureHandler.ashx?action=tdetails")
    Call<List<TreasureDetailResult>> getTreasureDetail(@Body TreasureDetail body);
}
