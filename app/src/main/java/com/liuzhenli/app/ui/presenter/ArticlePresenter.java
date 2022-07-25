package com.liuzhenli.app.ui.presenter;

import com.liuzhenli.app.base.RxPresenter;
import com.liuzhenli.app.bean.ArticleListBean;
import com.liuzhenli.app.network.Api;
import com.liuzhenli.app.observer.SampleProgressObserver;
import com.liuzhenli.app.ui.contract.ArticleContract;
import com.liuzhenli.app.utils.RxUtil;

import javax.inject.Inject;

/**
 * describe: 微信公众号列表
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/5/24 10:51 AM
 */
public class ArticlePresenter extends RxPresenter<ArticleContract.View> implements ArticleContract.Presenter<ArticleContract.View> {

    private final Api mApi;

    @Inject
    public ArticlePresenter(Api api) {
        this.mApi = api;
    }

    @Override
    public void getArticleList(String id, int page) {
        addSubscribe(RxUtil.subscribe(mApi.getArticleChaptersList(id, page), new SampleProgressObserver<ArticleListBean>() {
            @Override
            public void onNext(ArticleListBean articleListBean) {
                mView.showArticleList(articleListBean);
            }
        }));
    }
}
