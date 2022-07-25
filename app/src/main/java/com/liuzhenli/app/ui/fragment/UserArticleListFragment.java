package com.liuzhenli.app.ui.fragment;

import android.view.LayoutInflater;

import com.liuzhenli.app.base.BaseRVFragment;
import com.liuzhenli.app.bean.ArticleBean;
import com.liuzhenli.app.bean.ArticleListBean;
import com.liuzhenli.app.databinding.FragmentUserarticlelistBinding;
import com.liuzhenli.app.network.AppComponent;
import com.liuzhenli.app.ui.adapter.ArticleAdapter;
import com.liuzhenli.app.ui.contract.UserArticleListContract;
import com.liuzhenli.app.ui.presenter.UserArticleListPresenter;
import com.liuzhenli.app.utils.DataDiffUtil;

/**
 * describe:按照作者昵称搜索文章
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/6/7 4:24 PM
 */
public class UserArticleListFragment extends BaseRVFragment<UserArticleListPresenter, ArticleBean, FragmentUserarticlelistBinding> implements UserArticleListContract.View {
    public static String EXTRA_NAME = "user_name";
    private String mUserName;


    @Override
    public FragmentUserarticlelistBinding inflateView(LayoutInflater inflater) {
        return FragmentUserarticlelistBinding.inflate(inflater);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public void initData() {
        if (getArguments() != null) {
            mUserName = getArguments().getString(EXTRA_NAME);
        }
        initAdapter(ArticleAdapter.class, true, true, true);
    }

    @Override
    public void configViews() {
        mPresenter.getUserArticle(mUserName, mPage);
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        mTvTitle.setText(mUserName + "的博客");
    }

    @Override
    public void showUserArticle(ArticleListBean data) {
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

    }

    @Override
    public void showError(Exception e) {
        hideDialog();
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        mPage++;
        mPresenter.getUserArticle(mUserName, mPage);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPage = 0;
        mPresenter.getUserArticle(mUserName, mPage);
    }

    @Override
    public void complete() {
        hideDialog();
    }
}
