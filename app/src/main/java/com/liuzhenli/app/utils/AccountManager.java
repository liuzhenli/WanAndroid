package com.liuzhenli.app.utils;

import android.service.autofill.UserData;
import android.text.TextUtils;

import com.liuzhenli.app.AppApplication;
import com.liuzhenli.app.bean.UserInfo;
import com.liuzhenli.app.gson.GsonUtils;
import com.liuzhenli.app.manager.PreferenceManager;

import java.util.List;

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
        return userNameAndPwd != null && userNameAndPwd.length == 2 && !TextUtils.isEmpty(userNameAndPwd[0]);
    }

    public String getUserName() {
        String[] userNameAndPwd = PreferenceManager.getInstance(AppApplication.getInstance()).getUserNameAndPwd();
        if (userNameAndPwd != null && userNameAndPwd.length == 2) {
            return userNameAndPwd[0];
        }
        return "游客";
    }

    public void saveUser(UserInfo userInfo) {
        SharedPreferencesUtil.getInstance().putString("user_info", GsonUtils.gson.toJson(userInfo));
    }

    public UserInfo getUserInfo() {
        String userInfoStr = SharedPreferencesUtil.getInstance().getString("user_info");
        if (TextUtils.isEmpty(userInfoStr)) {
            return null;
        }
        return GsonUtils.toBean(userInfoStr, UserInfo.class);
    }

    public int getCollectCount() {
        UserInfo userInfo = getUserInfo();
        return null == userInfo ? 0 : userInfo.data == null ? 0 : userInfo.data.collectIds == null ? 0 : userInfo.data.collectIds.size();
    }

    public List<Integer> getCollectIds() {
        UserInfo userInfo = getUserInfo();
        return null == userInfo ? null : userInfo.data == null ? null : userInfo.data.collectIds;
    }

    public String getToken() {
        return "";
    }
}
