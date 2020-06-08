package com.liuzhenli.app.observer;

import io.reactivex.observers.DisposableObserver;

/**
 * @author Liuzhenli
 * @since 2019-10-24 10:35
 */

public abstract class MyObserver<T> extends DisposableObserver<T> {
    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {

    }


}
