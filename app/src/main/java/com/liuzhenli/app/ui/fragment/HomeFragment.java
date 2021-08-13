package com.liuzhenli.app.ui.fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuzhenli.app.R;
import com.liuzhenli.app.base.BaseRVFragment;
import com.liuzhenli.app.bean.ArticleBean;
import com.liuzhenli.app.bean.ArticleListBean;
import com.liuzhenli.app.databinding.FragmentHomeBinding;
import com.liuzhenli.app.network.AppComponent;
import com.liuzhenli.app.ui.activity.JumpActivity;
import com.liuzhenli.app.ui.adapter.ArticleAdapter;
import com.liuzhenli.app.ui.contract.HomeContract;
import com.liuzhenli.app.ui.presenter.HomePagePresenter;

/**
 * @author liuzhenli
 */
public class HomeFragment extends BaseRVFragment<HomePagePresenter, ArticleBean> implements HomeContract.View {

    private FragmentHomeBinding binding;

    public static HomeFragment getInstance() {
        HomeFragment instance = new HomeFragment();
        return instance;
    }

    @Override
    public View bindContentView(LayoutInflater inflater, ViewGroup container, boolean attachParent) {
        binding = FragmentHomeBinding.inflate(inflater, container, attachParent);
        return binding.getRoot();
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
        initAdapter(ArticleAdapter.class, true, true, true);
    }

    @Override
    public void showData(ArticleListBean data) {
        mAdapter.addAll(data.data.datas);
    }

    @Override
    public void showError(Exception e) {
        mRecyclerView.setRefreshing(false);
    }

    @Override
    public void complete() {
        mRecyclerView.setRefreshing(false);
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        mPage++;
        mPresenter.getHomePageData(mPage);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mRecyclerView.setRefreshing(false);
        mPage = 0;
        mPresenter.getHomePageData(mPage);
    }

    @Override
    public void onItemClick(int position) {
        ArticleBean item = mAdapter.getItem(position);
        startActivity(JumpActivity.createWebExplorerIntent(mContext, item.link, item.title));
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible && mAdapter.getCount() == 0) {
            onRefresh();
        }
    }
}
