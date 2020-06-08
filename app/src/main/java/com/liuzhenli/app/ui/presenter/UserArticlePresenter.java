package com.liuzhenli.app.ui.presenter;

import com.liuzhenli.app.base.RxPresenter;
import com.liuzhenli.app.bean.ArticleListBean;
import com.liuzhenli.app.bean.NavigationData;
import com.liuzhenli.app.bean.TopArticleList;
import com.liuzhenli.app.network.Api;
import com.liuzhenli.app.observer.SampleProgressObserver;
import com.liuzhenli.app.ui.contract.HomeContract;
import com.liuzhenli.app.ui.contract.UserArticleContract;
import com.liuzhenli.app.utils.RxUtil;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.observers.DisposableObserver;

/**
 * describe:
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/5/24 10:51 AM
 */
public class UserArticlePresenter extends RxPresenter<UserArticleContract.View> implements UserArticleContract.Presenter<UserArticleContract.View> {

    private final Api mApi;

    @Inject
    public UserArticlePresenter(Api api) {
        this.mApi = api;
    }

    @Override
    public void getUserArticle(int page) {
        DisposableObserver subscribe = RxUtil.subscribe(mApi.getUserArticles(page + ""), new SampleProgressObserver<ArticleListBean>() {
            @Override
            public void onNext(ArticleListBean articleListBean) {
                mView.showUserArticle(articleListBean);
            }
        });
        addSubscribe(subscribe);

    }
}
