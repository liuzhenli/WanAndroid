package com.liuzhenli.app.ui.activity;


import android.content.Intent;

import com.liuzhenli.app.R;
import com.liuzhenli.app.network.AppComponent;
import com.liuzhenli.app.base.BaseActivity;
import com.liuzhenli.app.view.CountDownView;

import butterknife.BindView;

/**
 * @author Liuzhenli
 * @since 2019-07-07 08:54
 */
public class SplashActivity extends BaseActivity {
    @BindView(R.id.countdown_view)
    CountDownView mCountDownView;

    @Override
    public int getContextViewId() {
        return R.layout.activity_splash;
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
        mCountDownView.startProgress(500);
        mCountDownView.setOnClickListener(v -> {
            mCountDownView.setHasClickClip(true);
            SplashActivity.this.startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            SplashActivity.this.finish();
        });
    }
}
