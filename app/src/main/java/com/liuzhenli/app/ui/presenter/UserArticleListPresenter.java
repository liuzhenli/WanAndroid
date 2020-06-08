package com.liuzhenli.app.ui.presenter;

import com.liuzhenli.app.base.RxPresenter;
import com.liuzhenli.app.bean.ArticleListBean;
import com.liuzhenli.app.network.Api;
import com.liuzhenli.app.observer.SampleProgressObserver;
import com.liuzhenli.app.ui.contract.UserArticleContract;
import com.liuzhenli.app.ui.contract.UserArticleListContract;
import com.liuzhenli.app.utils.RxUtil;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * describe:
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/5/24 10:51 AM
 */
public class UserArticleListPresenter extends RxPresenter<UserArticleListContract.View> implements UserArticleListContract.Presenter<UserArticleListContract.View> {

    private final Api mApi;

    @Inject
    public UserArticleListPresenter(Api api) {
        this.mApi = api;
    }

    @Override
    public void getUserArticle(String name,int page) {
        DisposableObserver subscribe = RxUtil.subscribe(mApi.getUserArticleList(name,page + ""), new SampleProgressObserver<ArticleListBean>() {
            @Override
            public void onNext(ArticleListBean articleListBean) {
                mView.showUserArticle(articleListBean);
            }
        });
        addSubscribe(subscribe);

    }
}
