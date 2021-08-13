package com.liuzhenli.app.ui.activity;


import android.content.Intent;
import android.view.View;

import com.liuzhenli.app.R;
import com.liuzhenli.app.databinding.ActivitySplashBinding;
import com.liuzhenli.app.network.AppComponent;
import com.liuzhenli.app.base.BaseActivity;

/**
 * @author Liuzhenli
 * @since 2019-07-07 08:54
 */
public class SplashActivity extends BaseActivity {

    private ActivitySplashBinding binding;

    @Override
    protected View bindContentView() {
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        return binding.getRoot();
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
