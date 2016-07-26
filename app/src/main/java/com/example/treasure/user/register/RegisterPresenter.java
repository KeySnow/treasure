package com.example.treasure.user.register;

import com.example.treasure.net.NetClient;
import com.example.treasure.user.User;
import com.example.treasure.user.UserApi;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 注册视图业务
 * Created by 93432 on 2016/7/12.
 */
public class RegisterPresenter extends MvpNullObjectBasePresenter<RegisterView> {

    private Call<RegisterResult> registerCall;

    //核心业务
    public void register(User user) {
        UserApi userApi = NetClient.getInstance().getUserApi();
        registerCall = userApi.register(user);
        registerCall.enqueue(callback);
    }

    Callback<RegisterResult> callback = new Callback<RegisterResult>() {
        @Override
        public void onResponse(Call<RegisterResult> call, Response<RegisterResult> response) {
            getView().hideProgress();
            //成功得到响应(200-299)
            if (response != null && response.isSuccessful()) {
                final RegisterResult result = response.body();
                if (result == null) {
                    getView().showMessage("unknown error");
                    return;
                }
                //注册成功
                if (result.getCode() == 1) {
                    getView().navigateToHome();
                    return;
                }
                getView().showMessage(result.getMsg());
            }
        }

        @Override
        public void onFailure(Call<RegisterResult> call, Throwable t) {
            getView().hideProgress();
            ;
            getView().showMessage(t.getMessage());
        }
    };

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (registerCall != null) {
            registerCall.cancel();
        }
    }
}
