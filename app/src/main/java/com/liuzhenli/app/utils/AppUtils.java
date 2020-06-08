/**
 * created by yuyh, 16/04/09
 * Copyright (c) 2016, smuyyh@gmail.com All Rights Reserved.
 * #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG              #
 * #                                                   #
 */


package com.liuzhenli.app.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.liuzhenli.app.AppApplication;
import com.meituan.android.walle.WalleChannelReader;
import com.orhanobut.logger.Logger;

import java.util.List;

public class AppUtils {

    private static Context mContext;
    private static Thread mUiThread;

    private static Handler sHandler = new Handler(Looper.getMainLooper());

    public static void init(Context context) {
        mContext = context;
        mUiThread = Thread.currentThread();
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static AssetManager getAssets() {
        return mContext.getAssets();
    }

    public static Resources getResource() {
        return mContext.getResources();
    }

    public static boolean isUIThread() {
        return Thread.currentThread() == mUiThread;
    }

    public static void runOnUI(Runnable r) {
        sHandler.post(r);
    }

    public static void runOnUIDelayed(Runnable r, long delayMills) {
        sHandler.postDelayed(r, delayMills);
    }

    public static void removeRunnable(Runnable r) {
        if (r == null) {
            sHandler.removeCallbacksAndMessages(null);
        } else {
            sHandler.removeCallbacks(r);
        }
    }

    /**
     * 17wan 游戏 需要 url参数拼接
     *
     * @param url 源url
     */
    public static String resetGameUrl(String url) {
        if (url.contains("?") && !url.endsWith("?")) {
            url = url + "&";
        }

        return url;
    }

    public static int getAppVersionCode(Context ctx) {
        int currentVersionCode = 0;
        PackageManager manager = ctx.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            currentVersionCode = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(e);
        }
        return currentVersionCode;
    }

    public static String getAppVersionName(Context ctx) {
        String appVersionName = "null";
        PackageManager manager = ctx.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            // 版本名
            appVersionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(e);
        }
        return appVersionName;
    }

    public static String getChannelValue(Context context) {
        context = context.getApplicationContext();
        ApplicationInfo appInfo = null;
        try {
            appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(e);
        }

        String meituan_channel = "";
        try {
            meituan_channel = WalleChannelReader.getChannel(context);
        } catch (Exception e) {
            Logger.e(e);
        }
        String UMENG_CHANNEL = appInfo.metaData.getString("UMENG_CHANNEL");
        if (TextUtils.isEmpty(meituan_channel)) {
            return UMENG_CHANNEL;
        }
        return meituan_channel;
    }

    public static int getChannelCodeValue(Context context) {
        context = context.getApplicationContext();
        ApplicationInfo appInfo = null;
        try {
            appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(e);
        }

        int meituan_channel_code = 0;
        int UMENG_CHANNEL_CODE = 0;
        UMENG_CHANNEL_CODE = appInfo.metaData.getInt("CHNL");
        try {
            if (WalleChannelReader.get(context, "CHNL") != null) {
                meituan_channel_code = Integer.parseInt(WalleChannelReader.get(context, "CHNL"));
            }
        } catch (Exception e) {
            Logger.e(e);
        }
        if (meituan_channel_code == 0) {
            return UMENG_CHANNEL_CODE;
        }
        return meituan_channel_code;
    }

    public static String getFormatDeviceUnique() {
        String uuid = DeviceUtil.getDeviceUniqueId(AppApplication.getInstance());
        byte[] data = ("unique_id:" + uuid).getBytes();
        return new String(Base64.encode(data, Base64.DEFAULT));
    }

    public static String getFormatVersionCode() {
        byte[] data = ("app_version:android-" + getAppVersionCode(AppApplication.getInstance())).getBytes();
        return new String(Base64.encode(data, Base64.DEFAULT));
    }

    /**
     * 不需要权限
     * 监听app是在前台还是在后台
     *
     * @return true 后台
     */
    public static boolean isBackground(Context context) {
        context = context.getApplicationContext();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                return appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND;
            }
        }
        return false;
    }

    /**
     * 判断当前应用程序处于前台还是后台
     *     
     */
    public static boolean isApplicationBroughtToBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            return !topActivity.getPackageName().equals(context.getPackageName());
        }
        return false;
    }
}
