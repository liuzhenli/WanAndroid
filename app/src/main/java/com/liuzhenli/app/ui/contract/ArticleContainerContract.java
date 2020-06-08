package com.liuzhenli.app.ui.contract;

import android.provider.ContactsContract;

import com.liuzhenli.app.base.BaseBean;
import com.liuzhenli.app.base.BaseContract;
import com.liuzhenli.app.bean.ArticleChapters;

/**
 * describe:
 *
 * @author Liuzhenli 848808263@qq.com
 * @since on 2020/5/26 7:35 AM
 */
public interface ArticleContainerContract {
   interface View extends BaseContract.BaseView {
       public void showArticleTabInfo(ArticleChapters data);
   }
   
   interface Presenter<T> extends BaseContract.BasePresenter<T> {
       public void getArticleTabInfo();
   }
}
