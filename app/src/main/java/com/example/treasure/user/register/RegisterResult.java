package com.example.treasure.user.register;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 93432 on 2016/7/13.
 */
public class RegisterResult {
//    {
//        "errcode": 1,                  //状态值
//            "errmsg": "登录成功！",        //返回信息
//            "tokenid": 171                 //用户令牌
//    }

    @SerializedName("errcode")
    private int code;
    @SerializedName("errmsg")
    private String msg;
    @SerializedName("tokenid")
    private String tokenId;

    public int getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }

    public String getTokenId(){
        return tokenId;
    }


}
