package com.liuzhenli.app.ui.contract;

import com.liuzhenli.app.base.BaseContract;
import com.liuzhenli.app.bean.ArticleListBean;

/**
 * @author Liuzhenli
 * @since 2019-07-07 11:53
 */
public class HomeContract {
    public interface View extends BaseContract.BaseView {
        /**
         * 显示首页数据
         *
         * @param data 数据
         */
        void showData(ArticleListBean data);
    }

    public interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 首页数据
         *
         * @param page 分页
         */
        void getHomePageData(int page);
    }
}
