package com.liuzhenli.app.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.liuzhenli.app.AppApplication;
import com.liuzhenli.app.R;
import com.liuzhenli.app.events.DoSomethingEvent;
import com.liuzhenli.app.network.AppComponent;
import com.liuzhenli.app.base.rxlife.RxAppCompatActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Activity 基类
 *
 * @author Liuzhenli
 * @since 2019-07-06 17:18
 */
public abstract class BaseActivity<T1 extends BaseContract.BasePresenter> extends RxAppCompatActivity {
    protected Context mContext;
    public TextView mTvTitle, mTvRight;
    public Toolbar mToolBar;
    @Inject
    public T1 mPresenter;
    protected boolean isActivityStopped = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindContentView());
        mContext = this;
        //使用ButterKnife框架
        ButterKnife.bind(this);
        //初始话EventBus框架
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        setupActivityComponent(AppApplication.getInstance().getAppComponent());
        mToolBar = findViewById(R.id.toolbar);
        if (mToolBar != null) {
            mTvTitle = findViewById(R.id.tv_toolbar_title);
            mTvRight = findViewById(R.id.tv_toolbar_right);
            mToolBar.setNavigationOnClickListener(v -> onBackPressed());
            initToolBar();
        }
        if (mPresenter != null) {
            //noinspection unchecked
            mPresenter.attachView(this);
        }
        initData();
        configViews();
        if (savedInstanceState != null) {
            restoreState(savedInstanceState);
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            QMUIStatusBarHelper.translucent(this);
        }

        //设置状态栏黑色字体与图标
        QMUIStatusBarHelper.setStatusBarLightMode(this);

    }
    protected abstract View bindContentView();

    /**
     * 根部局
     *
     * @return setContentView 的 layout  R.layout.xxxx
     */
    @Override
    public abstract int getContextViewId();

    /**
     * Presenter 需要处理网络数据逻辑时,需要该组件
     *
     * @param appComponent 组件 appComponent.inject(this)
     */
    protected abstract void setupActivityComponent(AppComponent appComponent);

    /**
     * 顶部标题 toolBar
     */
    protected abstract void initToolBar();

    /**
     * 初始化数据逻辑
     */
    protected abstract void initData();

    /**
     * 处理View数据
     */
    protected abstract void configViews();

    protected void restoreState(Bundle savedInstanceState) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        isActivityStopped = true;
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void doSomeThing(DoSomethingEvent a) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
