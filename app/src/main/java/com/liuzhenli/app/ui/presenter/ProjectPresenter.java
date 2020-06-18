package com.liuzhenli.app.ui.presenter;

import com.liuzhenli.app.base.RxPresenter;
import com.liuzhenli.app.bean.ArticleListBean;
import com.liuzhenli.app.bean.ProjectTreeBean;
import com.liuzhenli.app.network.Api;
import com.liuzhenli.app.observer.SampleProgressObserver;
import com.liuzhenli.app.ui.contract.ProjectContract;
import com.liuzhenli.app.utils.RxUtil;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;


/**
 * describe: 项目
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/5/24 10:51 AM
 */
public class ProjectPresenter extends RxPresenter<ProjectContract.View> implements ProjectContract.Presenter<ProjectContract.View> {

    private final Api mApi;

    @Inject
    public ProjectPresenter(Api api) {
        this.mApi = api;
    }

    @Override
    public void getProjects(int page, String cid) {
        DisposableObserver subscribe = RxUtil.subscribe(mApi.getProjects(page, cid), new SampleProgressObserver<ArticleListBean>() {
            @Override
            public void onNext(ArticleListBean data) {
                mView.showProjects(data);
            }
        });
        addSubscribe(subscribe);
    }
}
