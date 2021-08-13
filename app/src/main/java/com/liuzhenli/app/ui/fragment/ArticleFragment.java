package com.liuzhenli.app.ui.fragment;


import android.os.Bundle;
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
import com.liuzhenli.app.ui.contract.ArticleContract;
import com.liuzhenli.app.ui.presenter.ArticlePresenter;

/**
 * @author liuzhenli
 */
public class ArticleFragment extends BaseRVFragment<ArticlePresenter, ArticleBean> implements ArticleContract.View {

    /***公众号(人物)id*/
    public static final String ID = "id";
    private String mId;
    private FragmentHomeBinding binding;

    public static ArticleFragment getInstance(String id) {
        ArticleFragment instance = new ArticleFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ID, id);
        instance.setArguments(bundle);
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
        if (getArguments() != null) {
            mId = getArguments().getString(ID);
        }
    }

    @Override
    public void configViews() {
        initAdapter(ArticleAdapter.class, true, true, true);
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
        mPresenter.getArticleList(mId, mPage);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mRecyclerView.setRefreshing(false);
        mPage = 0;
        mPresenter.getArticleList(mId, mPage);
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible) {
            if (mAdapter.getCount() == 0) {
                onRefresh();
            }
        }
    }

    @Override
    public void onItemClick(int position) {
        ArticleBean item = mAdapter.getItem(position);
        startActivity(JumpActivity.createWebExplorerIntent(mContext, item.link, item.title));
    }

    @Override
    public void showArticleList(ArticleListBean data) {
        mAdapter.addAll(data.data.datas);
    }
}
