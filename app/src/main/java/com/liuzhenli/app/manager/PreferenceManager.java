/*
 * Tencent is pleased to support the open source community by making QMUI_Android available.
 *
 * Copyright (C) 2017-2018 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the MIT License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://opensource.org/licenses/MIT
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liuzhenli.app.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.liuzhenli.app.utils.SharedPreferencesUtil;


public class PreferenceManager {
    private static SharedPreferencesUtil sPreferences;
    private static PreferenceManager sQDPreferenceManager = null;

    private static final String APP_VERSION_CODE = "app_version_code";
    private static final String APP_SKIN_INDEX = "app_skin_index";

    private PreferenceManager(Context context) {
        sPreferences = SharedPreferencesUtil.getInstance();
    }

    public static final PreferenceManager getInstance(Context context) {
        if (sQDPreferenceManager == null) {
            sQDPreferenceManager = new PreferenceManager(context);
        }
        return sQDPreferenceManager;
    }

    public void setSkinIndex(int index) {
        sPreferences.putInt(APP_SKIN_INDEX, index);
    }

    public int getSkinIndex() {
        return sPreferences.getInt(APP_SKIN_INDEX, QDSkinManager.SKIN_BLUE);
    }


    public void saveUserNameAndPwd(String userName, String pwd) {
        sPreferences.putArray("user_name_pwd", new String[]{userName, pwd});
    }

    public String[] getUserNameAndPwd() {
        return sPreferences.getArray("user_name_pwd");
    }
}
