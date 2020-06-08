package com.liuzhenli.app.ui.contract;


import com.liuzhenli.app.base.BaseContract;
import com.liuzhenli.app.bean.NavigationData;

/**
 * describe:
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/6/2 9:04 PM
 */
public class NavigationContract {
    public interface View extends BaseContract.BaseView {

        void showNavigation(NavigationData data);
    }

    public interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getNavigation();
    }
}
