package com.liuzhenli.app.network.support;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.liuzhenli.app.AppApplication;
import com.liuzhenli.app.base.BaseBean;
import com.liuzhenli.app.exception.CrashHandler;
import com.liuzhenli.app.exception.ApiCodeException;
import com.liuzhenli.app.utils.Constant;
import com.liuzhenli.app.utils.SharedPreferencesUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;


/**
 * @author liuzhenli
 */
public class RewriteCacheControlInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    /**
     * 24小时制
     */
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();


        boolean networkAvailable = CrashHandler.getInstance().isNetworkAvailable(AppApplication.getInstance());
        if (!networkAvailable) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            Log.i("cache", "no network");
        }
        Response originalResponse = chain.proceed(request);
        if (networkAvailable) {
/*
            if (originalResponse.code()!=200){
                try {
                    FileUtils.writeFile(Constant.LOG_PATH, format.format(System.currentTimeMillis()) + ":"+ originalResponse.code()+ ":"+request.url().toString() +"\n", true);
                }catch (Exception e){

                }
            }
*/
            ResponseBody responseBody = originalResponse.body();
            BufferedSource source = responseBody.source();
            // Buffer the entire body.
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();
            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            BaseBean httpStatus = null;
            try {
                httpStatus = new Gson().fromJson(buffer.clone().readString(charset), BaseBean.class);
            } catch (Exception e) {
//                String s = request.url().toString();
//                if (!s.contains("graph.qq.com")) {
////                    StatisticsManager.getInstance().sendErrorReport(s);
//                    FileUtils.writeFile(Constant.LOG_PATH, format.format(System.currentTimeMillis()) + ":格式错误:" + originalResponse.code() + ":" + s + "\n", true);
//                    Logger.i(s);
//                }
            }
            if (httpStatus != null) {
//                if (!TextUtils.isEmpty(httpStatus.accessToken)) {
//                    SharedPreferencesUtil.getInstance().putString(Constant.SP_TOKEN, httpStatus.accessToken);
//                }
//                if (httpStatus.isCodeInvalid()) {
//                    throw new ApiCodeException(httpStatus.status.errorCode, httpStatus.status.errorMsg);
//                }
                if (AppApplication.isDebug) {
                    Log.e("request---ulr--:\n", request.url().toString());
                }
            }
            String cacheControl = request.cacheControl().toString();
            if (TextUtils.isEmpty(cacheControl)) {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "no-cache")
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            }
        } else {
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                    .removeHeader("Pragma")
                    .build();
        }


    }
}
