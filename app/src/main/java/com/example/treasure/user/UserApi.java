package com.example.treasure.user;

import com.example.treasure.user.account.Update;
import com.example.treasure.user.account.UpdateResult;
import com.example.treasure.user.account.UploadRestult;
import com.example.treasure.user.login.LoginResult;
import com.example.treasure.user.register.RegisterResult;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * 将用户模块API，转为java接口
 * Created by 93432 on 2016/7/14.
 */
public interface UserApi {

    @POST("/Handler/UserHandler.ashx?action=register")
    Call<RegisterResult> register(@Body User user);

    @POST("/Handler/UserHandler.ashx?action=login")
    Call<LoginResult> login(@Body User user);

    //头像上传（是一个多部分请求）
    @Multipart
    @POST("/Handler/UserLoadPicHandler1.ashx")
    Call<UploadRestult> upload(@Part MultipartBody.Part part);

    //头像更新
    @POST("/Handler/UserHandler.ashx?action=update")
    Call<UpdateResult> update(@Body Update update);
}
