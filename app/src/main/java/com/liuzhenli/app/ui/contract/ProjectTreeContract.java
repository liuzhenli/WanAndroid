package com.liuzhenli.app.ui.contract;

import com.liuzhenli.app.base.BaseContract;
import com.liuzhenli.app.bean.ProjectTreeBean;

/**
 * describe:
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/6/11 7:31 PM
 */
public class ProjectTreeContract {

    public interface View extends BaseContract.BaseView {
        /**
         * 项目
         *
         * @param data project Tree
         */
        void showProjectTree(ProjectTreeBean data);
    }

    public interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 项目
         */
        void getProjectTree();
    }
}
