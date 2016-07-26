package com.example.treasure.user.register;

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
import com.example.treasure.components.AlertDialogFragment;
import com.example.treasure.treasure.home.HomeActivity;
import com.example.treasure.user.User;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注册视图
 */
public class RegisterActivity extends MvpActivity<RegisterView, RegisterPresenter> implements RegisterView {

    @Bind(R.id.et_Password)
    EditText etPassword;
    @Bind(R.id.et_Username)
    EditText etUsername;
    @Bind(R.id.et_Confirm)
    EditText etConfirm;
    @Bind(R.id.btn_Register)
    Button btnRegister;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private String password;//用来保存编辑框内的mima
    private String username;//用来保存编辑框内的用户名
    private ActivityUtils activityUtils;//Activity常用工具类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);//实例化工具类
        setContentView(R.layout.activity_register);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);//绑定
        //用Toolbar替换以前的Actionbar
        setSupportActionBar(toolbar);
        //激活Home（左上角，内部使用的选项菜单处理），设置其title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getTitle());
        }
        //添加监听
        etConfirm.addTextChangedListener(mTextWatcher);
        etPassword.addTextChangedListener(mTextWatcher);
        etUsername.addTextChangedListener(mTextWatcher);
    }

    @NonNull
    @Override
    public RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    //选项菜单处理
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
            String confirm = etConfirm.getText().toString();
            boolean canRegister = !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && password.equals(confirm);
            btnRegister.setEnabled(canRegister);//布局内btnRegister按钮默认不可用
        }
    };

    @OnClick(R.id.btn_Register)
    public void register() {
        //正则判断输入的用户名是否有效
        if (RegexUtils.verifyUsername(username) != RegexUtils.VERIFY_SUCCESS) {
            showUsernameError();
            return;
        }
        //正则判断输入的密码是否有效
        if (RegexUtils.verifyPassword(password) != RegexUtils.VERIFY_SUCCESS) {
            showPasswordError();
            return;
        }
        getPresenter().register(new User(username, password));
    }

    //输入用户名错误dialog
    private void showUsernameError() {
        String msg = getString(R.string.username_rules);
        AlertDialogFragment fragment = AlertDialogFragment.newInstance(R.string.username_error, msg);
        fragment.show(getSupportFragmentManager(), "showUsernameError");
    }

    //输入密码错误dialog
    private void showPasswordError() {
        String msg = getString(R.string.password_rules);
        AlertDialogFragment fragment = AlertDialogFragment.newInstance(R.string.password_error, msg);
        fragment.show(getSupportFragmentManager(), "showPasswordError");
    }

    private ProgressDialog progressDialog;

    @Override
    public void showProgress() {
        activityUtils.hideSoftKeyboard();
        progressDialog = ProgressDialog.show(this, "", "注册中，请稍候...");
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }

    }

    @Override
    public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }

    @Override
    public void navigateToHome() {
        //跳转到Home页面
        activityUtils.startActivity(HomeActivity.class);
        //关闭当前页面
        finish();
        //关闭入口Main页面（发动一个广播出去，是本地广播）
        Intent intent = new Intent(MainActivity.ACTION_ENTER_HOME);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);//解绑
    }
}
