package com.liuzhenli.app.ui.fragment;

import com.liuzhenli.app.R;
import com.liuzhenli.app.base.BaseRVFragment;
import com.liuzhenli.app.bean.ArticleBean;
import com.liuzhenli.app.bean.ArticleListBean;
import com.liuzhenli.app.network.AppComponent;
import com.liuzhenli.app.ui.activity.JumpActivity;
import com.liuzhenli.app.ui.adapter.ArticleAdapter;
import com.liuzhenli.app.ui.contract.DailyQuestionContract;
import com.liuzhenli.app.ui.presenter.DailyQuestionPresenter;
import com.liuzhenli.app.utils.DataDiffUtil;

/**
 * describe:问答
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/6/7 4:24 PM
 */
public class DailyQuestionFragment extends BaseRVFragment<DailyQuestionPresenter, ArticleBean> implements DailyQuestionContract.View {

    public static DailyQuestionFragment getInstance() {
        DailyQuestionFragment instance = new DailyQuestionFragment();
        return instance;
    }


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_dailyquestion;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public void initData() {
        initAdapter(ArticleAdapter.class, true, true, true);
    }

    @Override
    public void configViews() {
    }


    @Override
    public void showDailyQuestion(ArticleListBean data) {
        mRecyclerView.setRefreshing(false);
        hideDialog();
        if (mAdapter.getCount() > 0 && mPage == 0) {
            DataDiffUtil.diffResult(mAdapter, data.data.datas, new DataDiffUtil.ItemSameCallBack<ArticleBean>() {
                @Override
                public boolean isItemSame(ArticleBean oldItem, ArticleBean newItem) {
                    return oldItem.id == newItem.id;
                }

                @Override
                public boolean isContentSame(ArticleBean oldItem, ArticleBean newItem) {
                    return oldItem.link.equals(newItem.link) && oldItem.niceDate.equals(newItem.niceDate);
                }
            });
        } else {
            mAdapter.addAll(data.data.datas);
        }

    }

    @Override
    public void onItemClick(int position) {
        startActivity(JumpActivity.createWebExplorerIntent(mContext, mAdapter.getItem(position).link, mAdapter.getItem(position).title));

    }

    @Override
    public void showError(Exception e) {
        mRecyclerView.setRefreshing(false);
        hideDialog();
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        mPage++;
        mPresenter.getDailyQuestion(mPage);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPage = 0;
        mPresenter.getDailyQuestion(mPage);
    }

    @Override
    public void complete() {
        mRecyclerView.setRefreshing(false);
        hideDialog();
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible && mAdapter.getCount() == 0) {
            onRefresh();
        }
    }
}
