package com.liuzhenli.app.ui.fragment;

import android.view.LayoutInflater;

import com.liuzhenli.app.base.BaseContract;
import com.liuzhenli.app.base.BaseFragment;
import com.liuzhenli.app.databinding.FragmentAboutBinding;
import com.liuzhenli.app.network.AppComponent;

/**
 * describe:about page
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/6/10 5:03 PM
 */
public class AboutFragment extends BaseFragment<BaseContract.BasePresenter, FragmentAboutBinding> {

    public static AboutFragment getInstance() {
        return new AboutFragment();
    }

    @Override
    public FragmentAboutBinding inflateView(LayoutInflater inflater) {
        return FragmentAboutBinding.inflate(inflater);
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
