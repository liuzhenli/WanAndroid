package com.liuzhenli.app.ui.activity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import com.liuzhenli.app.R;
import com.liuzhenli.app.base.BaseTabActivity;
import com.liuzhenli.app.network.AppComponent;
import com.liuzhenli.app.ui.fragment.TestFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuzhenli
 */
public class TabActivity extends BaseTabActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, TabActivity.class));
    }

    @Override
    public int getContextViewId() {
        return R.layout.activity_tab;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected void initToolBar() {
        mTvTitle.setText("TabTest详情");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected List<Fragment> createTabFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new TestFragment());
        fragments.add(new TestFragment());
        fragments.add(new TestFragment());
        fragments.add(new TestFragment());
        return fragments;
    }

    @Override
    protected List<String> createTabTitles() {
        ArrayList<String> titles = new ArrayList<>();
        titles.add("张三");
        titles.add("李四");
        titles.add("王二");
        titles.add("麻子");
        return titles;
    }
}
