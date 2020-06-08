package com.liuzhenli.app.ui.contract;

import com.liuzhenli.app.base.BaseContract;
import com.liuzhenli.app.bean.UserInfo;

/**
 * @author Liuzhenli
 * @since 2019-07-07 11:53
 */
public class LoginContract {
    public interface View extends BaseContract.BaseView {
        void showLoginResult(UserInfo data);
    }

    public interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void login(String phone,String password);
    }
}
