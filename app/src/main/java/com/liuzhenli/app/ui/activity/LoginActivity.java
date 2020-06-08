package com.liuzhenli.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.liuzhenli.app.R;
import com.liuzhenli.app.bean.UserInfo;
import com.liuzhenli.app.manager.PreferenceManager;
import com.liuzhenli.app.network.AppComponent;
import com.liuzhenli.app.base.BaseActivity;
import com.liuzhenli.app.base.BaseBean;
import com.liuzhenli.app.ui.contract.LoginContract;
import com.liuzhenli.app.ui.presenter.LoginPresenter;
import com.liuzhenli.app.utils.SharedPreferencesUtil;
import com.liuzhenli.app.utils.ToastUtil;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Liuzhenli
 * @since 2019-07-07 10:25
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {


    @BindView(R.id.et_username)
    EditText mEtUserName;
    @BindView(R.id.et_password)
    EditText mEtPassword;

    public static void start(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    public int getContextViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initData() {
        String[] userNameAndPwd = PreferenceManager.getInstance(this).getUserNameAndPwd();
        if (userNameAndPwd != null && userNameAndPwd.length == 2) {
            mEtUserName.setText(userNameAndPwd[0]);
            mEtPassword.setText(userNameAndPwd[1]);
        }
    }

    @Override
    public void configViews() {

    }

    @Override
    public void showLoginResult(UserInfo data) {
        if (data.errorCode == 0) {
            ToastUtil.showCenter("登录成功");
            finish();
        }

    }

    @Override
    public void showError(Exception e) {

    }

    @Override
    public void complete() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.tv_login)
    public void login() {
        showDialog();
        mPresenter.login(mEtUserName.getText().toString(), mEtPassword.getText().toString());
    }
}
