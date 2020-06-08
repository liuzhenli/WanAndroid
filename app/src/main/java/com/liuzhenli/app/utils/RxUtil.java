package com.liuzhenli.app.utils;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Liuzhenli
 * @since 2019-07-07 03:16
 */
public class RxUtil {

    public static <T> DisposableObserver subscribe(Observable<T> observable, DisposableObserver<T> observer) {
        return observable.compose(rxSchedulerHelper()).subscribeWith(observer);
    }

    /**
     * 统一线程处理
     */
    private static <T> ObservableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
