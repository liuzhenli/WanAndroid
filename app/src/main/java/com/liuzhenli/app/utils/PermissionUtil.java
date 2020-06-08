package com.liuzhenli.app.utils;



import androidx.fragment.app.FragmentActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observer;

/**
 * 权限申请工具
 * @author Liuzhenli
 * @since 2019-07-07 18:28
 */
public class PermissionUtil {
    public  static  void requestPermission(FragmentActivity context, Observer observer, final String...permissions){
        RxPermissions rxPermissions = new RxPermissions(context);
        rxPermissions.request(permissions)
                .subscribe(observer);
    }
}

