package com.liuzhenli.app.utils;

import android.content.Context;
import android.content.DialogInterface;

import com.liuzhenli.app.ui.activity.LoginActivity;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

/**
 * describe:弹窗
 *
 * @author Liuzhenli on 2020-03-15 08:20
 */
public class DialogUtil {
    private static int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;

    /**
     * 单选弹窗
     *
     * @param context      Context
     * @param items        条目标题
     * @param listener     选中监听
     * @param checkedIndex 默认选中的条目
     */
    public static void sowSingleChoiceDialog(Context context, String[] items, DialogInterface.OnClickListener listener, int checkedIndex) {
        new QMUIDialog.CheckableDialogBuilder(context)
                .setCheckedIndex(checkedIndex)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        dialog.dismiss();
                        if (listener != null) {
                            listener.onClick(dialog, position);
                        }
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    /**
     * 普通弹窗
     *
     * @param context                上下文
     * @param title                  标题
     * @param message                弹窗内容
     * @param noText                 取消按钮文案
     * @param noListener             取消按钮监听
     * @param yesText                确定按钮文案
     * @param yesListener            确定按钮监听
     * @param canceledOnTouchOutside 触摸是否可以消失
     */
    public static void showMessagePositiveDialog(Context context, String title, String message, String noText,
                                                 QMUIDialogAction.ActionListener noListener, String yesText, QMUIDialogAction.ActionListener yesListener, boolean canceledOnTouchOutside) {
        new QMUIDialog.MessageDialogBuilder(context)
                .setTitle(title)
                .setMessage(message)
                .setCanceledOnTouchOutside(canceledOnTouchOutside)
                .addAction(noText, new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        if (noListener != null) {
                            noListener.onClick(dialog, index);
                        }
                        dialog.dismiss();
                    }
                })
                .addAction(0, yesText, QMUIDialogAction.ACTION_PROP_POSITIVE, new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        if (yesListener != null) {
                            yesListener.onClick(dialog, index);
                        }
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    public static void showMessagePositiveDialog(Context context, String title, String message, String cancleText,
                                                 String yesText, QMUIDialogAction.ActionListener yesListener) {
        showMessagePositiveDialog(context, title, message, cancleText, null, yesText, yesListener, true);
    }

    /**
     * 登录提示弹窗
     *
     * @param context 上下文
     */
    public static void showLoginDialog(Context context) {
        showMessagePositiveDialog(context, "提示", "您还没有登录,请登录~", "取消",
                "登录", (dialog, index) -> LoginActivity.start(context));
    }
}
