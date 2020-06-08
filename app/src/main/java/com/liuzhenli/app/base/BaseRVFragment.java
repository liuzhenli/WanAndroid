package com.liuzhenli.app.base;


import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.liuzhenli.app.R;
import com.liuzhenli.app.utils.NetworkUtils;
import com.liuzhenli.app.view.recyclerview.EasyRecyclerView;
import com.liuzhenli.app.view.recyclerview.adapter.OnLoadMoreListener;
import com.liuzhenli.app.view.recyclerview.adapter.RecyclerArrayAdapter;
import com.liuzhenli.app.view.recyclerview.swipe.OnRefreshListener;

import java.lang.reflect.Constructor;

import butterknife.BindView;


/**
 * @author Liuzhenli
 * @since 2019-07-07 07:55
 */
public abstract class BaseRVFragment<T1 extends BaseContract.BasePresenter, T2> extends BaseFragment<T1> implements OnLoadMoreListener, OnRefreshListener, RecyclerArrayAdapter.OnItemClickListener {
    @BindView(R.id.recyclerView)
    protected
    EasyRecyclerView mRecyclerView;
    protected RecyclerArrayAdapter<T2> mAdapter;


    /**
     * 页数
     */
    protected int mPage = 0;
    /**
     * 每页有几条数据
     */
    protected int mCount = 20;

    protected void initAdapter(Class<? extends RecyclerArrayAdapter<T2>> clazz, boolean refreshable, boolean loadMoreAble, boolean dividerAble) {
        mAdapter = (RecyclerArrayAdapter<T2>) createInstance(clazz);
        if (mRecyclerView != null) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            if (dividerAble) {
                mRecyclerView.setItemDecoration(getResources().getColor(R.color.common_divider_narrow), 1, 0, 0);
            }
            mRecyclerView.setAdapterWithProgress(mAdapter);
        }
        if (mAdapter != null) {
            mAdapter.setOnItemClickListener(this);
            mAdapter.setError(R.layout.common_error_view).setOnClickListener(v -> mAdapter.resumeMore());
            if (loadMoreAble) {
                mAdapter.setMore(R.layout.common_more_view, this);
                mAdapter.setNoMore(R.layout.common_nomore_view);
            }

            if (refreshable && mRecyclerView != null) {
                mRecyclerView.setRefreshListener(this);
            }
        }
    }

    protected void initAdapter(Class<? extends RecyclerArrayAdapter<T2>> clazz, boolean refreshAble, boolean loadMoreAble) {
        initAdapter(clazz, refreshAble, loadMoreAble, true);
    }

    public Object createInstance(Class clazz) {
        Object obj = null;
        try {
            Constructor constructor = clazz.getDeclaredConstructor(new Class[]{Context.class});
            constructor.setAccessible(true);
            obj = constructor.newInstance(new Object[]{mContext});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void onRefresh() {
        if (!NetworkUtils.isConnected(getApplicationContext())) {
            mAdapter.pauseMore();
            return;
        }
        mPage = 1;
    }

    protected void loadError() {
        if (mAdapter != null) {
            if (mAdapter.getCount() < 1) {
                mAdapter.clear();
            }
            mAdapter.pauseMore();
        }

        mRecyclerView.setRefreshing(false);
        mRecyclerView.showTipViewAndDelayClose(getResources().getString(R.string.network_unusable));
    }

    @Override
    public void onLoadMore() {
        if (!NetworkUtils.isAvailable(getApplicationContext())) {
            mAdapter.pauseMore();
            return;
        }
        mPage++;
    }
}
