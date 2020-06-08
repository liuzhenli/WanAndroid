package com.liuzhenli.app.module;


import com.liuzhenli.app.AppApplication;
import com.liuzhenli.app.network.Api;
import com.liuzhenli.app.network.support.HeaderInterceptor;
import com.liuzhenli.app.network.support.LoggingInterceptor;
import com.liuzhenli.app.network.support.RewriteCacheControlInterceptor;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

@Module
public class ApiModule {

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient() {

        OkHttpClient.Builder builder = null;
        try {
            LoggingInterceptor logging = new LoggingInterceptor(new MyLog());
            if (AppApplication.isDebug) {
                logging.setLevel(LoggingInterceptor.Level.BODY);
            } else {
                logging.setLevel(LoggingInterceptor.Level.NONE);
            }

            HeaderInterceptor headerInterceptor = new HeaderInterceptor();

            File cacheFile = new File(AppApplication.getInstance().getCacheDir(), "[缓存目录]");
            RewriteCacheControlInterceptor rewrite_cache_control_interceptor = new RewriteCacheControlInterceptor();

            builder = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(false) // 失败重发
                    .addInterceptor(headerInterceptor)
                    .cache(new Cache(cacheFile, 1024 * 1024 * 100))
                    .addInterceptor(logging)
                    .addInterceptor(rewrite_cache_control_interceptor)
                    .addNetworkInterceptor(rewrite_cache_control_interceptor)
            ;

        } catch (Exception e) {
            Logger.e(e);
        }

        return builder.build();
    }


    @Singleton
    @Provides
    protected Api provideBookService(OkHttpClient okHttpClient) {
        return Api.getInstance(okHttpClient);
    }

    public static class MyLog implements LoggingInterceptor.LoggerM {
        @Override
        public void log(String message) {
            Logger.i("oklog: " + message);
        }
    }
}