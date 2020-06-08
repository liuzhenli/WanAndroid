package com.liuzhenli.app.utils.face;

import android.content.Context;

import com.google.gson.Gson;
import com.liuzhenli.app.bean.CommonConfigData;
import com.liuzhenli.app.utils.Constant;
import com.liuzhenli.app.utils.SharedPreferencesUtil;

/**
 * @author Liuzhenli
 * @since 2019-07-06 16:28
 */
public class DataUtils {
    public static void doCommonConfigData(Context context, CommonConfigData data) {
        Gson gson = new Gson();
        SharedPreferencesUtil.getInstance().putString(Constant.COMMON_CONFIG, gson.toJson(data, CommonConfigData.class));
    }

}
