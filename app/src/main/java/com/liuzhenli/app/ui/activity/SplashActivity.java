package com.liuzhenli.app.ui.activity;


import android.content.Intent;
import android.view.LayoutInflater;

import com.liuzhenli.app.R;
import com.liuzhenli.app.base.BaseActivity;
import com.liuzhenli.app.base.BaseContract;
import com.liuzhenli.app.databinding.ActivitySplashBinding;
import com.liuzhenli.app.network.AppComponent;

/**
 * @author Liuzhenli
 * @since 2019-07-07 08:54
 */
public class SplashActivity extends BaseActivity<BaseContract.BasePresenter,ActivitySplashBinding> {

    @Override
    protected ActivitySplashBinding inflateView(LayoutInflater inflater) {
        return ActivitySplashBinding.inflate(inflater);
    }

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
        binding.countdownView.startProgress(500);
        binding.countdownView.setOnClickListener(v -> {
            binding.countdownView.setHasClickClip(true);
            SplashActivity.this.startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            SplashActivity.this.finish();
        });
    }
}
