package com.liuzhenli.app.utils;

import android.service.autofill.UserData;

import com.liuzhenli.app.AppApplication;
import com.liuzhenli.app.manager.PreferenceManager;

/**
 * @author Liuzhenli
 * @since 2019-07-06 20:39
 */
public class AccountManager {

    private static AccountManager mInstance = new AccountManager();
    public UserData user;
    String[] userNameAndPwd;

    private AccountManager() {
    }

    public static AccountManager getInstance() {
        return mInstance;
    }


    public boolean isLogin() {
        String[] userNameAndPwd = PreferenceManager.getInstance(AppApplication.getInstance()).getUserNameAndPwd();
        return userNameAndPwd != null;
    }

    public String getUserName() {
        String[] userNameAndPwd = PreferenceManager.getInstance(AppApplication.getInstance()).getUserNameAndPwd();
        if (userNameAndPwd != null && userNameAndPwd.length == 2) {
            return userNameAndPwd[0];
        }
        return "游客";
    }

    public String getToken() {
        return "";
    }
}
