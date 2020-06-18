package com.liuzhenli.app.ui.fragment;

import com.liuzhenli.app.R;
import com.liuzhenli.app.base.BaseFragment;
import com.liuzhenli.app.network.AppComponent;

/**
 * describe:about page
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/6/10 5:03 PM
 */
public class AboutFragment extends BaseFragment {

    public static AboutFragment getInstance() {
        AboutFragment instance = new AboutFragment();
        return instance;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_about;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {

    }
}
