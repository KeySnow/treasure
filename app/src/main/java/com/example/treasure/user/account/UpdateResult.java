package com.example.treasure.user.account;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 93432 on 2016/7/15.
 */
public class UpdateResult {
//    {
//        "errcode":1,             //状态值
//            "errmsg":"修改成功!"     //返回信息
//    }

    @SerializedName("errcode")
    private int code;
    @SerializedName("errmsg")
    private String msg;

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

}
