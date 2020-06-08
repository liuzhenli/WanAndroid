package com.liuzhenli.app.base;

/**
 * @author liuzhenli 848808263@qq.com
 */
public interface BaseContract {

    interface BasePresenter<T> {
        /**
         * 关联View
         *
         * @param view activity fragment
         */
        void attachView(T view);

        /**
         * 解除关联
         **/
        void detachView();
    }

    interface BaseView {
        /**
         * 显示错误信息
         *
         * @param e 异常
         */
        void showError(Exception e);

        /**
         * 完成
         */
        void complete();
    }
}