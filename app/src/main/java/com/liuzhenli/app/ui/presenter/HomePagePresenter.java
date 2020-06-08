package com.liuzhenli.app.ui.presenter;

import com.liuzhenli.app.base.RxPresenter;
import com.liuzhenli.app.bean.ArticleBean;
import com.liuzhenli.app.bean.ArticleListBean;
import com.liuzhenli.app.bean.TopArticleList;
import com.liuzhenli.app.network.Api;
import com.liuzhenli.app.observer.SampleProgressObserver;
import com.liuzhenli.app.ui.contract.HomeContract;
import com.liuzhenli.app.utils.RxUtil;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.internal.operators.observable.ObservableZip;
import io.reactivex.observers.DisposableObserver;

/**
 * describe:
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/5/24 10:51 AM
 */
public class HomePagePresenter extends RxPresenter<HomeContract.View> implements HomeContract.Presenter<HomeContract.View> {

    private final Api mApi;

    @Inject
    public HomePagePresenter(Api api) {
        this.mApi = api;
    }

    @Override
    public void getHomePageData(int page) {
        if (page == 0) {
            //第一页加载置顶文章和最新文章的第一页
            Observable<ArticleListBean> zip = Observable.zip(mApi.getTopArticle(), mApi.getHomePageArticleList(page), new BiFunction<TopArticleList, ArticleListBean, ArticleListBean>() {
                @Override
                public ArticleListBean apply(TopArticleList topList, ArticleListBean articleList) throws Exception {

                    for (int i = 0; i < topList.data.size(); i++) {
                        topList.data.get(i).itemType = 1;
                    }
                    //从第1个位置开始加入
                    articleList.data.datas.addAll(0, topList.data);
                    return articleList;
                }
            });

            DisposableObserver subscribe = RxUtil.subscribe(zip, new SampleProgressObserver<ArticleListBean>() {
                @Override
                public void onNext(ArticleListBean articleListBean) {
                    mView.showData(articleListBean);
                }
            });
            addSubscribe(subscribe);
        } else {
            DisposableObserver subscribe = RxUtil.subscribe(mApi.getHomePageArticleList(page), new SampleProgressObserver<ArticleListBean>() {
                @Override
                public void onNext(ArticleListBean articleListBean) {
                    mView.showData(articleListBean);
                }
            });
            addSubscribe(subscribe);
        }

    }
}
