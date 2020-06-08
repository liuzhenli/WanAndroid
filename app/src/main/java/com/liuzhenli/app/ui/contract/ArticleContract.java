package com.liuzhenli.app.ui.contract;

import com.liuzhenli.app.base.BaseContract;
import com.liuzhenli.app.bean.ArticleListBean;

/**
 * 公众号文章列表
 *
 * @author Liuzhenli
 * @since 2019-07-07 11:53
 */
public class ArticleContract {
    public interface View extends BaseContract.BaseView {
        /**
         * 显示首页数据
         *
         * @param data 数据
         */
        void showArticleList(ArticleListBean data);
    }

    public interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 公众号历史数据列表
         *
         * @param id   公众号id
         * @param page 分页
         */
        void getArticleList(String id, int page);
    }
}
