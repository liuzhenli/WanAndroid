package com.liuzhenli.app.ui.presenter;

import com.liuzhenli.app.base.RxPresenter;
import com.liuzhenli.app.bean.ArticleChapters;
import com.liuzhenli.app.network.Api;
import com.liuzhenli.app.observer.SampleProgressObserver;
import com.liuzhenli.app.ui.contract.ArticleContainerContract;
import com.liuzhenli.app.utils.RxUtil;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * describe:
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/5/26 7:35 AM
 */
public class ArticleContainerPresenter extends RxPresenter<ArticleContainerContract.View> implements ArticleContainerContract.Presenter<ArticleContainerContract.View> {

    private Api mApi;

    @Inject
    public ArticleContainerPresenter(Api api) {
        this.mApi = api;
    }

    @Override
    public void getArticleTabInfo() {
        DisposableObserver subscribe = RxUtil.subscribe(mApi.getArticleChapters(), new SampleProgressObserver<ArticleChapters>() {
            @Override
            public void onNext(ArticleChapters articleChapters) {
                mView.showArticleTabInfo(articleChapters);
            }
        });
        addSubscribe(subscribe);

    }
}
