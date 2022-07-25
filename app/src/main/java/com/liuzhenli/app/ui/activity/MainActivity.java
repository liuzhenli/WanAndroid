package com.liuzhenli.app.ui.activity;

import android.view.View;

import com.liuzhenli.app.R;
import com.liuzhenli.app.base.BaseActivity;
import com.liuzhenli.app.databinding.ActivityMainBinding;
import com.liuzhenli.app.network.AppComponent;


/**
 * @author liuzhenli
 */
public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;


    @Override
    protected View bindContentView() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public int getContextViewId() {
        return R.layout.activity_main;
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
