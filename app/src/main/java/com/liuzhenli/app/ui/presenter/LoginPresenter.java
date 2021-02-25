package com.liuzhenli.app.ui.presenter;

import com.liuzhenli.app.AppApplication;
import com.liuzhenli.app.bean.UserInfo;
import com.liuzhenli.app.manager.PreferenceManager;
import com.liuzhenli.app.network.Api;
import com.liuzhenli.app.base.RxPresenter;
import com.liuzhenli.app.observer.SampleProgressObserver;
import com.liuzhenli.app.ui.contract.LoginContract;
import com.liuzhenli.app.utils.AccountManager;
import com.liuzhenli.app.utils.RxUtil;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import javax.inject.Inject;


/**
 * @author Liuzhenli
 * @since 2019-07-07 11:55
 */
public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter<LoginContract.View> {
    private static final String TAG = "LoginPresenter";
    private Api mApi;

    @Inject
    public LoginPresenter(Api api) {
        mApi = api;
    }

    @Override
    public void login(String phone, String password) {
        HashMap<String, String> params = new HashMap<String, String>(2);
        params.put("username", phone);
        params.put("password", password);

        addSubscribe(RxUtil.subscribe(mApi.getLoginData(params), new SampleProgressObserver<UserInfo>(mView) {

            @Override
            public void onNext(@NotNull UserInfo baseBean) {
                PreferenceManager.getInstance(AppApplication.getInstance()).saveUserNameAndPwd(phone, password);
                AccountManager.getInstance().saveUser(baseBean);
                mView.showLoginResult(baseBean);
            }
        }));
    }
}
