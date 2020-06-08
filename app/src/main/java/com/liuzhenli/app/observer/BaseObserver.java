package com.liuzhenli.app.observer;


import com.liuzhenli.app.exception.ApiException;
import com.liuzhenli.app.exception.ExceptionEngine;

import io.reactivex.observers.DisposableObserver;

/**
 * @author Liuzhenli
 * @since 2019-07-06 10:35
 */
public abstract class BaseObserver<T> extends DisposableObserver<T> {
    @Override
    public void onError(Throwable e) {
        e = ExceptionEngine.handleException(e);
        if (e != null) {
            onError((ApiException) e);
        } else {
            onError(new ApiException(123, e));
        }
    }

    protected abstract void onError(ApiException e);
}
