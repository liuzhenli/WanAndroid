package com.liuzhenli.app.ui.presenter;

import android.util.ArrayMap;

import com.liuzhenli.app.base.RxPresenter;
import com.liuzhenli.app.network.Api;
import com.liuzhenli.app.ui.contract.SettingContract;

/**
 * Description:
 *
 * @author liuzhenli 2021/2/25
 * Email: 848808263@qq.com
 */
public class SettingPresenter extends RxPresenter<SettingContract.View> implements SettingContract.Presenter<SettingContract.View> {
    private Api mApi;

    public SettingPresenter(Api api) {
        this.mApi = api;
        ArrayMap<String, String> stringStringArrayMap = new ArrayMap<>();
    }



}
