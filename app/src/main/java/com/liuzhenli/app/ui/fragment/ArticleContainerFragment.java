package com.liuzhenli.app.ui.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.liuzhenli.app.R;
import com.liuzhenli.app.base.BaseFragment;
import com.liuzhenli.app.bean.ArticleChapters;
import com.liuzhenli.app.databinding.FragmentArticleContainerBinding;
import com.liuzhenli.app.network.AppComponent;
import com.liuzhenli.app.ui.contract.ArticleContainerContract;
import com.liuzhenli.app.ui.presenter.ArticleContainerPresenter;
import com.liuzhenli.app.view.ScaleTransitionPagerTitleView;

import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * describe: 公众号tab页面
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/5/26 7:27 AM
 */
public class ArticleContainerFragment extends BaseFragment<ArticleContainerPresenter, FragmentArticleContainerBinding> implements ArticleContainerContract.View {

    private FragmentPagerAdapter fragmentPagerAdapter;
    private CommonNavigatorAdapter mCommonNavigationAdapter;
    /***子Fragment容器**/
    private final List<BaseFragment> mFragmentList = new ArrayList<>();
    /**
     * 页面标题
     */
    private final List<String> mTitleList = new ArrayList<>();

    public static ArticleContainerFragment getInstance() {
        return new ArticleContainerFragment();
    }

    @Override
    public FragmentArticleContainerBinding inflateView(LayoutInflater inflater) {
        return FragmentArticleContainerBinding.inflate(inflater);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {
        fragmentPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitleList.get(position);
            }
        };

        binding.tabVp.setOffscreenPageLimit(3);
        binding.tabVp.setAdapter(fragmentPagerAdapter);


        binding.magicIndicator.setBackgroundColor(getResources().getColor(R.color.white));
        CommonNavigator commonNavigator7 = new CommonNavigator(mContext);
        //这个控制左右滑动的时候,选中文字的位置,0.5表示在中间
        commonNavigator7.setScrollPivotX(0.5f);
        mCommonNavigationAdapter = new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mFragmentList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ScaleTransitionPagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mTitleList.get(index));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.text_color_99));
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.main));
                simplePagerTitleView.setOnClickListener(v -> binding.tabVp.setCurrentItem(index));
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 4));
                indicator.setLineWidth(UIUtil.dip2px(context, 10));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(getResources().getColor(R.color.main));
                return indicator;
            }
        };
        commonNavigator7.setAdapter(mCommonNavigationAdapter);
        binding.magicIndicator.setNavigator(commonNavigator7);
        ViewPagerHelper.bind(binding.magicIndicator, binding.tabVp);

        mPresenter.getArticleTabInfo();
    }

    @Override
    public void showError(Exception e) {

    }

    @Override
    public void complete() {

    }

    @Override
    public void showArticleTabInfo(ArticleChapters data) {
        hideDialog();
        if (data == null || data.data == null) {
            return;
        }
        mTitleList.clear();
        mFragmentList.clear();
        mTitleList.add("最新");
        mTitleList.add("广场");
        mTitleList.add("问答");
        mFragmentList.add(HomeFragment.getInstance());
        mFragmentList.add(UserArticleFragment.getInstance());
        mFragmentList.add(DailyQuestionFragment.getInstance());
        for (int i = 0; i < data.data.size(); i++) {
            ArticleChapters.Module module = data.data.get(i);
            mTitleList.add(module.name);
            mFragmentList.add(ArticleFragment.getInstance(module.id));
        }
        mCommonNavigationAdapter.notifyDataSetChanged();
        fragmentPagerAdapter.notifyDataSetChanged();
        binding.tabVp.setOffscreenPageLimit(mFragmentList.size());
    }
}

