package com.liuzhenli.app.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.liuzhenli.app.R;
import com.liuzhenli.app.base.BaseActivity;
import com.liuzhenli.app.network.AppComponent;
import com.liuzhenli.app.ui.contract.SettingContract;
import com.liuzhenli.app.ui.presenter.SettingPresenter;

/**
 * Description:
 *
 * @author liuzhenli 2021/2/25
 * Email: 848808263@qq.com
 */
public class SettingActivity extends BaseActivity<SettingPresenter> implements SettingContract.View {

    public static void start(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getContextViewId() {
        return R.layout.act_setting;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void configViews() {

    }

    @Override
    public void showError(Exception e) {

    }

    @Override
    public void complete() {

    }
}
