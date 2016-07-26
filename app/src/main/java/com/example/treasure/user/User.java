package com.example.treasure.user;

import com.google.gson.annotations.SerializedName;

/**
 * 用户登陆信息实体类
 * Created by 93432 on 2016/7/13.
 */
public class User {

//    {
//        "UserName":"qjd",
//            "Password":"654321"
//    }

    @SerializedName("UserName")
    private String username;
    @SerializedName("Password")
    private String password;

    public User(String username, String password){
        this.password = password;
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }


}
