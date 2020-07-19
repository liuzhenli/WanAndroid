package com.liuzhenli.app.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;

import com.liuzhenli.app.AppApplication;
import com.liuzhenli.app.R;
import com.liuzhenli.app.events.DoSomethingEvent;
import com.liuzhenli.app.network.AppComponent;
import com.liuzhenli.app.base.rxlife.RxFragment;
import com.liuzhenli.app.ui.activity.HomeActivity;
import com.liuzhenli.app.utils.ToastUtil;
import com.liuzhenli.app.view.loading.CustomProgressDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author Liuzhenli
 * @since 2019-07-06 17:18
 */
public abstract class BaseFragment<T1 extends BaseContract.BasePresenter> extends RxFragment {

    protected View parentView;
    protected FragmentActivity activity;
    protected Context mContext;
    public boolean isLoadFinish;
    public Toolbar mCommonToolbar;
    public TextView mTvTitle;

    private CustomProgressDialog dialog;
    public String TAG;

    @Inject
    protected T1 mPresenter;

    /**
     * return 根部局的文件Id R.layout.xxx
     */
    public abstract @LayoutRes
    int getLayoutResId();


    protected abstract void setupActivityComponent(AppComponent appComponent);


    @Override
    protected View onCreateView() {
        parentView = LayoutInflater.from(getActivity()).inflate(getLayoutResId(), null);
        activity = getSupportActivity();
        mContext = activity;
        TAG = activity.getClass().getName();
        isLoadFinish = false;
        return parentView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        initVariable();
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mCommonToolbar = view.findViewById(R.id.toolbar);

        setupActivityComponent(AppApplication.getInstance().getAppComponent());
        attachView();
        initData();
        if (mCommonToolbar != null) {
            mTvTitle = view.findViewById(R.id.tv_toolbar_title);
            mCommonToolbar.setNavigationOnClickListener(v -> onBackPressed());
            initToolBar();
        }
        configViews();

        if (!hasCreateView && getUserVisibleHint()) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
        }
    }

    protected void initToolBar() {

    }

    public abstract void initData();

    /**
     * 对各种控件进行设置、适配、填充数据
     */
    public abstract void configViews();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (FragmentActivity) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.activity = null;
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    public FragmentActivity getSupportActivity() {
        return (FragmentActivity) super.getActivity();
    }

    public Context getApplicationContext() {
        return this.activity == null ? (getActivity() == null ? null : getActivity()
                .getApplicationContext()) : this.activity.getApplicationContext();
    }


    protected View getParentView() {
        return parentView;
    }

    protected CustomProgressDialog getDialog() {
        return getDialog(true);
    }

    protected CustomProgressDialog getDialog(boolean cancelAble) {
        if (dialog == null) {
            dialog = new CustomProgressDialog(mContext).instance(getActivity());
            dialog.setCancelable(cancelAble);
        }
        return dialog;
    }

    public void hideDialog() {
        dismissDialog();
    }

    public void showDialog() {
        getDialog().show();
    }

    public void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    protected boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }


    /**
     * rootView是否初始化标志，防止回调函数在rootView为空的时候触发
     */
    private boolean hasCreateView;

    /**
     * 当前Fragment是否处于可见状态标志，防止因ViewPager的缓存机制而导致回调函数的触发
     */
    private boolean isFragmentVisible;

    /**
     * onCreateView()里返回的view，修饰为protected,所以子类继承该类时，在onCreateView里必须对该变量进行初始化
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (parentView == null) {
            return;
        }
        hasCreateView = true;
        if (isVisibleToUser) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
            return;
        }
        if (isFragmentVisible) {
            onFragmentVisibleChange(false);
            isFragmentVisible = false;
        }
    }

    public void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    private void initVariable() {
        hasCreateView = false;
        isFragmentVisible = false;
    }

    protected void onFragmentVisibleChange(boolean isVisible) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void doSomeThing(DoSomethingEvent somethingEvent) {

    }

    protected void toast(String message) {
        ToastUtil.showCenter(message);
    }

}
