package com.liuzhenli.app.ui.fragment;


import com.liuzhenli.app.R;
import com.liuzhenli.app.base.BaseRVFragment;
import com.liuzhenli.app.bean.ArticleBean;
import com.liuzhenli.app.bean.ArticleListBean;
import com.liuzhenli.app.network.AppComponent;
import com.liuzhenli.app.ui.activity.JumpActivity;
import com.liuzhenli.app.ui.adapter.ArticleAdapter;
import com.liuzhenli.app.ui.contract.UserArticleContract;
import com.liuzhenli.app.ui.presenter.UserArticlePresenter;

/**
 * @author liuzhenli
 */
public class UserArticleFragment extends BaseRVFragment<UserArticlePresenter, ArticleBean> implements UserArticleContract.View {

    public static UserArticleFragment getInstance() {
        UserArticleFragment instance = new UserArticleFragment();
        return instance;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
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
        mPresenter.getUserArticle(mPage);
    }

    @Override
    public void showUserArticle(ArticleListBean data) {

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
        mPresenter.getUserArticle(mPage);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mRecyclerView.setRefreshing(false);
        mPage = 0;
        mPresenter.getUserArticle(mPage);
    }

    @Override
    public void onItemClick(int position) {
        ArticleBean item = mAdapter.getItem(position);
        startActivity(JumpActivity.createWebExplorerIntent(mContext, item.link, item.title));
    }

}
