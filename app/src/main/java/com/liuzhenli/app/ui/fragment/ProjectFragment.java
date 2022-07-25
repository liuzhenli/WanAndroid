package com.liuzhenli.app.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.liuzhenli.app.base.BaseRVFragment;
import com.liuzhenli.app.bean.ArticleBean;
import com.liuzhenli.app.bean.ArticleListBean;
import com.liuzhenli.app.databinding.FragmentProjectBinding;
import com.liuzhenli.app.network.AppComponent;
import com.liuzhenli.app.ui.activity.JumpActivity;
import com.liuzhenli.app.ui.adapter.ProjectAdapter;
import com.liuzhenli.app.ui.contract.ProjectContract;
import com.liuzhenli.app.ui.presenter.ProjectPresenter;
import com.liuzhenli.app.utils.DataDiffUtil;

/**
 * describe:
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/6/11 7:29 PM
 */
public class ProjectFragment extends BaseRVFragment<ProjectPresenter, ArticleBean, FragmentProjectBinding> implements ProjectContract.View {


    public static ProjectFragment getInstance(int cid) {
        ProjectFragment instance = new ProjectFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("cid", cid);
        instance.setArguments(bundle);
        return instance;
    }

    private String mCid;

    @Override
    public FragmentProjectBinding inflateView(LayoutInflater inflater) {
        return FragmentProjectBinding.inflate(inflater);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public void initData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            mCid = arguments.getInt("cid") + "";
        }
    }

    @Override
    public void configViews() {
        initAdapter(ProjectAdapter.class, true, true, true);
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
    public void onRefresh() {
        super.onRefresh();
        mPage = 0;
        getDataFromNet();
    }

    private void getDataFromNet() {
        mPresenter.getProjects(mPage, mCid);
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        mPage++;
        getDataFromNet();
    }

    @Override
    public void showProjects(ArticleListBean data) {
        mRecyclerView.setRefreshing(false);
        if (mAdapter.getCount() > 0 && mPage == 0) {
            DataDiffUtil.diffResult(mAdapter, data.data.datas, new DataDiffUtil.ItemSameCallBack<ArticleBean>() {
                @Override
                public boolean isItemSame(ArticleBean oldItem, ArticleBean newItem) {
                    return oldItem.id == newItem.id;
                }

                @Override
                public boolean isContentSame(ArticleBean oldItem, ArticleBean newItem) {
                    return oldItem.shareDate == newItem.shareDate;
                }
            });
        } else {
            mAdapter.addAll(data.data.datas);
        }
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
