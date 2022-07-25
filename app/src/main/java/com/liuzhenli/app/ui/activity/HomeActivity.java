package com.liuzhenli.app.ui.activity;


import android.view.LayoutInflater;

import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.tabs.TabLayout;
import com.liuzhenli.app.R;
import com.liuzhenli.app.base.BaseActivity;
import com.liuzhenli.app.base.BaseContract;
import com.liuzhenli.app.databinding.ActivityHomeBinding;
import com.liuzhenli.app.network.AppComponent;
import com.liuzhenli.app.ui.adapter.MainTabAdapter;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;

/**
 * describe: 底部有tab的Activity
 *
 * @author Liuzhenli on 2020-03-12 11:37
 */
public class HomeActivity extends BaseActivity<BaseContract.BasePresenter, ActivityHomeBinding> {
    private int mCurrentPosition;
    private QMUIPopup mGlobalAction;


    @Override
    protected ActivityHomeBinding inflateView(LayoutInflater inflater) {
        return ActivityHomeBinding.inflate(inflater);
    }

    @Override
    public int getContextViewId() {
        return R.layout.activity_home;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void initToolBar() {
        mTvTitle.setText(getResources().getStringArray(R.array.main_tab_names)[mCurrentPosition]);
    }

    @Override
    public void initData() {
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void configViews() {
        MainTabAdapter mainTabAdapter = new MainTabAdapter(mContext, getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        binding.viewPagerMain.setAdapter(mainTabAdapter);
        binding.viewPagerMain.setOffscreenPageLimit(5);
        binding.tabLayoutMain.setupWithViewPager(binding.viewPagerMain);
        for (int i = 0; i < binding.tabLayoutMain.getTabCount(); i++) {
            binding.tabLayoutMain.getTabAt(i).setCustomView(mainTabAdapter.getTabView(i));
        }
        binding.tabLayoutMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mCurrentPosition = tab.getPosition();
                mTvTitle.setText(mainTabAdapter.getPageTitle(mCurrentPosition));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
