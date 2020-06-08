package com.liuzhenli.app;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import androidx.multidex.MultiDexApplication;

import com.liuzhenli.app.manager.QDSkinManager;
import com.liuzhenli.app.network.AppComponent;
import com.liuzhenli.app.network.DaggerAppComponent;
import com.liuzhenli.app.module.ApiModule;
import com.liuzhenli.app.module.AppModule;
import com.liuzhenli.app.utils.AppUtils;
import com.liuzhenli.app.utils.SharedPreferencesUtil;
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;

/**
 * @author Liuzhenli
 * @since 2019-07-06 16:28
 */
public class AppApplication extends MultiDexApplication {
    public static boolean isDebug = BuildConfig.DEBUG;
    private static AppApplication sInstance;
    private AppComponent appComponent;
    private String mVersionName;
    private int mVersionCode;
    public static boolean openSkinMake = false;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        initComponent();
        AppUtils.init(this);
        setVersionInfo();
        QMUISwipeBackActivityManager.init(this);

        SharedPreferencesUtil.init(this, "wan_android", Context.MODE_PRIVATE);
        //腾讯皮肤管理类
        QDSkinManager.install(this);
    }

    public static AppApplication getInstance() {
        return sInstance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private void initComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .build();

    }

    private void setVersionInfo() {
        try {
            PackageInfo pi = getPackageManager().getPackageInfo(getPackageName(), 0);
            mVersionCode = pi.versionCode;
            mVersionName = pi.versionName;
        } catch (
                PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getVersionName() {
        return mVersionName;
    }

    public int getVersionCode() {
        return mVersionCode;
    }

}
