package com.liuzhenli.app.ui.presenter;

import com.liuzhenli.app.base.RxPresenter;
import com.liuzhenli.app.bean.NavigationData;
import com.liuzhenli.app.network.Api;
import com.liuzhenli.app.observer.SampleProgressObserver;
import com.liuzhenli.app.ui.contract.NavigationContract;
import com.liuzhenli.app.utils.RxUtil;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * @author Liuzhenli
 * @since 2019-07-07 11:55
 */
public class NavigationPresenter extends RxPresenter<NavigationContract.View> implements NavigationContract.Presenter<NavigationContract.View> {
    private static final String TAG = "LoginPresenter";
    private Api mApi;

    @Inject
    public NavigationPresenter(Api api) {
        mApi = api;
    }


    @Override
    public void getNavigation() {

        DisposableObserver disposable = RxUtil.subscribe(mApi.getNavigation(), new SampleProgressObserver<NavigationData>(mView) {

            @Override
            public void onNext(NavigationData baseBean) {
                mView.showNavigation(baseBean);
            }

        });
        addSubscribe(disposable);

    }
}
