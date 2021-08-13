package com.liuzhenli.app.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuzhenli.app.R;
import com.liuzhenli.app.base.BaseFragment;
import com.liuzhenli.app.databinding.FragmentAboutBinding;
import com.liuzhenli.app.network.AppComponent;

/**
 * describe:about page
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/6/10 5:03 PM
 */
public class AboutFragment extends BaseFragment {

    private FragmentAboutBinding binding;

    public static AboutFragment getInstance() {
        AboutFragment instance = new AboutFragment();
        return instance;
    }

    @Override
    public View bindContentView(LayoutInflater inflater, ViewGroup container, boolean attachParent) {
        binding = FragmentAboutBinding.inflate(getLayoutInflater(), container, attachParent);
        return binding.getRoot();
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
