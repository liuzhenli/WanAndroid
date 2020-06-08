package com.liuzhenli.app.base;

import java.io.Serializable;

/**
 * @author Liuzhenli
 * @since 2019-07-06
 */
public class BaseBean implements Serializable {
    public int errorCode;
    public String errorMsg;

    public boolean isCodeInvalid() {
        return errorCode != 0;
    }

}
