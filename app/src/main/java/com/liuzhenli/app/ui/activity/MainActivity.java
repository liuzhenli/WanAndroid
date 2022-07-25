package com.liuzhenli.app.ui.activity;

import android.view.LayoutInflater;

import com.liuzhenli.app.R;
import com.liuzhenli.app.base.BaseActivity;
import com.liuzhenli.app.base.BaseContract;
import com.liuzhenli.app.databinding.ActivityMainBinding;
import com.liuzhenli.app.network.AppComponent;


/**
 * @author liuzhenli
 */
public class MainActivity extends BaseActivity<BaseContract.BasePresenter, ActivityMainBinding> {


    @Override
    protected ActivityMainBinding inflateView(LayoutInflater inflater) {
        return ActivityMainBinding.inflate(inflater);
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
