package com.example.treasure;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.treasure.commons.ActivityUtils;
import com.example.treasure.user.login.LoginActivity;
import com.example.treasure.user.register.RegisterActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private ActivityUtils activityUtils;//Activity常用工具类
    public static final String ACTION_ENTER_HOME = "action.enter.home";
    //广播接收器（当登陆和注册成功后，将发出广播）
    //接收到后，关闭当前页面
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);//实例化工具类
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);//绑定
        //注册本地广播接收器
        IntentFilter intentFilter = new IntentFilter(ACTION_ENTER_HOME);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, intentFilter);
    }

    //按钮监听
    @OnClick({R.id.btn_Login, R.id.btn_Register})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_Login:
                activityUtils.startActivity(LoginActivity.class);
                break;
            case R.id.btn_Register:
                activityUtils.startActivity(RegisterActivity.class);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);//解绑
    }
}
