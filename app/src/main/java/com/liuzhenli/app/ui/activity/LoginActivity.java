package com.liuzhenli.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;

import com.liuzhenli.app.R;
import com.liuzhenli.app.base.BaseActivity;
import com.liuzhenli.app.bean.UserInfo;
import com.liuzhenli.app.databinding.ActivityLoginBinding;
import com.liuzhenli.app.events.LoginSuccessEvent;
import com.liuzhenli.app.manager.PreferenceManager;
import com.liuzhenli.app.network.AppComponent;
import com.liuzhenli.app.ui.contract.LoginContract;
import com.liuzhenli.app.ui.presenter.LoginPresenter;
import com.liuzhenli.app.utils.ClickUtils;
import com.liuzhenli.app.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;


/**
 * @author Liuzhenli
 * @since 2019-07-07 10:25
 */
public class LoginActivity extends BaseActivity<LoginPresenter, ActivityLoginBinding> implements LoginContract.View {

    public static void start(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    protected ActivityLoginBinding inflateView(LayoutInflater inflater) {
        return ActivityLoginBinding.inflate(inflater);
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
        mTvTitle.setText("登录");
    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {
        ClickUtils.click(binding.tvLogin, o -> login());

        String[] userNameAndPwd = PreferenceManager.getInstance(this).getUserNameAndPwd();
        if (userNameAndPwd != null && userNameAndPwd.length == 2) {
            binding.etUsername.setText(userNameAndPwd[0]);
            binding.etPassword.setText(userNameAndPwd[1]);
        }
    }

    @Override
    public void showLoginResult(UserInfo data) {
        hideDialog();
        if (data.errorCode == 0) {
            ToastUtil.showCenter("登录成功");
            EventBus.getDefault().post(new LoginSuccessEvent());
            finish();
        }

    }

    @Override
    public void showError(Exception e) {
        hideDialog();
    }

    @Override
    public void complete() {
        hideDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void login() {
        showDialog();
        mPresenter.login(binding.etUsername.getText().toString(), binding.etPassword.getText().toString());
    }
}
