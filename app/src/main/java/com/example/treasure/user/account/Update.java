package com.example.treasure.user.account;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 93432 on 2016/7/15.
 */
public class Update {
//    {
//        "Tokenid":3,"
//        "HeadPic": "05a1a7e18ab940679dbd0e506be31add.jpg"
//    }

    @SerializedName("Tokenid")
    private int tokenid;
    @SerializedName("HeadPic")
    private String photoUrl;

    public Update(int tokenid, String photoUrl){
        this.tokenid = tokenid;
        this.photoUrl = photoUrl;
    }

}
