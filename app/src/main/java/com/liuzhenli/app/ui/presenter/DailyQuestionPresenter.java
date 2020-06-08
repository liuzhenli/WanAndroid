package com.liuzhenli.app.ui.presenter;

import com.liuzhenli.app.base.RxPresenter;
import com.liuzhenli.app.bean.ArticleListBean;
import com.liuzhenli.app.network.Api;
import com.liuzhenli.app.observer.SampleProgressObserver;
import com.liuzhenli.app.ui.contract.DailyQuestionContract;
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
public class DailyQuestionPresenter extends RxPresenter<DailyQuestionContract.View> implements DailyQuestionContract.Presenter<DailyQuestionContract.View> {

    private final Api mApi;

    @Inject
    public DailyQuestionPresenter(Api api) {
        this.mApi = api;
    }


    @Override
    public void getDailyQuestion(int page) {
        DisposableObserver subscribe = RxUtil.subscribe(mApi.getDailyQuestion(page + ""), new SampleProgressObserver<ArticleListBean>() {
            @Override
            public void onNext(ArticleListBean articleListBean) {
                mView.showDailyQuestion(articleListBean);
            }
        });
        addSubscribe(subscribe);
    }
}
