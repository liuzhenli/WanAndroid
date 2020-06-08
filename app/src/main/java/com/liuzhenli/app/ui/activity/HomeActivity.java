package com.liuzhenli.app.ui.activity;


import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.tabs.TabLayout;
import com.liuzhenli.app.R;
import com.liuzhenli.app.base.BaseActivity;
import com.liuzhenli.app.network.AppComponent;
import com.liuzhenli.app.ui.adapter.MainTabAdapter;
import com.liuzhenli.app.view.NoAnimViewPager;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;


import butterknife.BindView;

/**
 * describe: 底部有tab的Activity
 *
 * @author Liuzhenli on 2020-03-12 11:37
 */
public class HomeActivity extends BaseActivity {
    @BindView(R.id.view_pager_main)
    NoAnimViewPager mViewPager;
    @BindView(R.id.tab_layout_main)
    TabLayout mTabLayout;
    private int mCurrentPosition;
    private QMUIPopup mGlobalAction;

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
        mViewPager.setAdapter(mainTabAdapter);
        mViewPager.setOffscreenPageLimit(5);
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            mTabLayout.getTabAt(i).setCustomView(mainTabAdapter.getTabView(i));
        }
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
