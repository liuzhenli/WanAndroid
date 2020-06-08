package com.liuzhenli.app.ui.contract;

import com.liuzhenli.app.base.BaseContract;
import com.liuzhenli.app.bean.ArticleListBean;

/**
 * @author Liuzhenli
 * @since 2019-07-07 11:53
 */
public class UserArticleListContract {
    public interface View extends BaseContract.BaseView {
        /**
         * 显示广场数据
         *
         * @param data 数据
         */
        void showUserArticle(ArticleListBean data);
    }

    public interface Presenter<T> extends BaseContract.BasePresenter<T> {
        /**
         * 广场数据
         *
         * @param uid 作者id
         * @param page 分页
         */
        void getUserArticle(String uid,int page);
    }
}
