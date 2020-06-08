package com.liuzhenli.app.utils.valid;

import android.content.Context;

import com.liuzhenli.app.utils.AccountManager;
import com.liuzhenli.app.utils.DialogUtil;
import com.toptechs.libaction.action.Valid;

/**
 * @author liuzhenli
 */
public class LoginValid implements Valid {
    private Context mContext;

    public LoginValid(Context context) {
        mContext = context;
    }

    @Override
    public boolean check() {

        return AccountManager.getInstance().isLogin();
    }

    @Override
    public void doValid() {
        DialogUtil.showLoginDialog(mContext);
    }
}
