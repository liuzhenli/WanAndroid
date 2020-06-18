package com.liuzhenli.app.ui.presenter;

import com.liuzhenli.app.base.RxPresenter;
import com.liuzhenli.app.bean.ProjectTreeBean;
import com.liuzhenli.app.network.Api;
import com.liuzhenli.app.observer.SampleProgressObserver;
import com.liuzhenli.app.ui.contract.ProjectTreeContract;
import com.liuzhenli.app.utils.RxUtil;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;


/**
 * describe: 项目
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/5/24 10:51 AM
 */
public class ProjectTreePresenter extends RxPresenter<ProjectTreeContract.View> implements ProjectTreeContract.Presenter<ProjectTreeContract.View> {

    private final Api mApi;

    @Inject
    public ProjectTreePresenter(Api api) {
        this.mApi = api;
    }


    @Override
    public void getProjectTree() {
        DisposableObserver subscribe = RxUtil.subscribe(mApi.getProjectTree(), new SampleProgressObserver<ProjectTreeBean>() {
            @Override
            public void onNext(ProjectTreeBean projectTreeBean) {
                mView.showProjectTree(projectTreeBean);
            }
        });
        addSubscribe(subscribe);
    }
}
