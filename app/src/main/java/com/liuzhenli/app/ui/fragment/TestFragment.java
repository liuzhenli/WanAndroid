package com.liuzhenli.app.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuzhenli.app.base.BaseFragment;
import com.liuzhenli.app.databinding.FragmentTestBinding;
import com.liuzhenli.app.network.AppComponent;

public class TestFragment extends BaseFragment {

    private FragmentTestBinding binding;

    @Override
    public View bindContentView(LayoutInflater inflater, ViewGroup container, boolean attachParent) {
        binding = FragmentTestBinding.inflate(inflater, container, attachParent);
        return binding.getRoot();
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void attachView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {

    }
}
