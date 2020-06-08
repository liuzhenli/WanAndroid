package com.liuzhenli.app.utils;

import android.widget.Toast;

import com.liuzhenli.app.AppApplication;

/**
 * @author Liuzhenli
 * @since 2019-07-07 03:11
 */
public class ToastUtil {
    public static void showCenter(String ex) {
        Toast.makeText(AppApplication.getInstance(),ex,Toast.LENGTH_SHORT).show();
    }
}
