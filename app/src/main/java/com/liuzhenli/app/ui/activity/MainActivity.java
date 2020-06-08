package com.liuzhenli.app.ui.activity;

import android.content.Intent;

import com.liuzhenli.app.R;
import com.liuzhenli.app.network.AppComponent;
import com.liuzhenli.app.base.BaseActivity;

import butterknife.OnClick;

/**
 * @author liuzhenli
 */
public class MainActivity extends BaseActivity {

    @OnClick(R.id.tv_login_main)
    public void login() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tv_login_tab)
    public void tabTest() {
        Intent intent = new Intent(this, TabActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_photo)
    public void requestPhotoPermision() {


    }

    @Override
    public int getContextViewId() {
        return R.layout.activity_main_container;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {

    }
}
