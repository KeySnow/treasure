package com.example.treasure.user.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.treasure.MainActivity;
import com.example.treasure.R;
import com.example.treasure.commons.ActivityUtils;
import com.example.treasure.commons.RegexUtils;
import com.example.treasure.treasure.home.HomeActivity;
import com.example.treasure.user.User;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登陆视图
 */
public class LoginActivity extends MvpActivity<LoginView, LoginPresenter> implements LoginView{

    @Bind(R.id.et_Password)
    EditText etPassword;
    @Bind(R.id.et_Username)
    EditText etUsername;
    @Bind(R.id.btn_Login)
    Button btnLogin;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private String password;//用来保存密码
    private String username;//用来保存用户名

    private ActivityUtils activityUtils;//Activity常用工具类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);//实例化工具类
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);//绑定
        //用toolbar替换以前的actionbar
        setSupportActionBar(toolbar);
        //激活Home，设置其title
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getTitle());
        }
        //添加监听
        etPassword.addTextChangedListener(mTextWatcher);
        etUsername.addTextChangedListener(mTextWatcher);
    }

    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private final TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            username = etUsername.getText().toString();
            password = etPassword.getText().toString();
            boolean canLogin = !(TextUtils.isEmpty(username) || TextUtils.isEmpty(password));
            //默认btnLogin是未激活，不可用的
            btnLogin.setEnabled(canLogin);
        }
    };

    @OnClick(R.id.btn_Login)
    public void login(){

        //判断用户名是否有效
        if(RegexUtils.verifyUsername(username) != RegexUtils.VERIFY_SUCCESS){
            activityUtils.showToast(R.string.username_rules);
            return;
        }
        //判断密码是否有效
        if(RegexUtils.verifyPassword(password) != RegexUtils.VERIFY_SUCCESS){
            activityUtils.showToast(R.string.password_rules);
            return;
        }

        getPresenter().login(new User(username, password));
    }

    private ProgressDialog progressDialog;
    @Override
    public void showProgress() {
        activityUtils.hideSoftKeyboard();//隐藏软键盘
        progressDialog = ProgressDialog.show(this,"","登陆中，请稍候...");

    }

    @Override
    public void hideProgress() {
        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }

    @Override
    public void navigateToHome() {
        //跳转到Home页面
        activityUtils.startActivity(HomeActivity.class);
        //关闭当前页面
        finish();
        //关闭入口Main页面（发送一个广播出去，是本地广播）
        Intent intent = new Intent(MainActivity.ACTION_ENTER_HOME);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);//解绑
    }
}
