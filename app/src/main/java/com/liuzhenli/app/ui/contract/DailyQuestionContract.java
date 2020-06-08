package com.liuzhenli.app.ui.contract;

import com.liuzhenli.app.base.BaseContract;
import com.liuzhenli.app.bean.ArticleListBean;

/**
 * @author Liuzhenli
 * @since 2019-07-07 11:53
 */
public class DailyQuestionContract {
    public interface View extends BaseContract.BaseView {
        /**
         * 问答数据
         *
         * @param data 数据
         */
        void showDailyQuestion(ArticleListBean data);
    }

    public interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 问答
         *
         * @param page 分页
         */
        void getDailyQuestion(int page);
    }
}
