package com.liuzhenli.app.network.support;


import com.google.gson.Gson;
import com.liuzhenli.app.AppApplication;
import com.liuzhenli.app.utils.AccountManager;
import com.liuzhenli.app.utils.AppUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Retrofit2 Cookie拦截器。用于保存和设置Cookies
 *
 * @author liuzhenli
 */
public final class HeaderInterceptor implements Interceptor {

    private Map<String, String> commonParams = new HashMap<>();

    /***设置通用参数**/
    private void initCommonParams() {
        commonParams.put("platform", "2");
        commonParams.put("_versions", AppUtils.getAppVersionCode(AppApplication.getInstance()) + "");
        commonParams.put("merchant", AppUtils.getChannelValue(AppApplication.getInstance()));
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        initCommonParams();
        Request oldRequest = chain.request();
        Request.Builder newRequestBuild = null;
        String method = oldRequest.method();
        Request newRequest = null;

        RequestBody body = oldRequest.body();
        if ("GET".equals(method) || "DELETE".equals(method) || body instanceof MultipartBody) {
            HttpUrl.Builder commonParamsUrlBuilder = oldRequest.url()
                    .newBuilder()
                    .scheme(oldRequest.url().scheme())
                    .host(oldRequest.url().host());
            for (Map.Entry<String, String> item : commonParams.entrySet()) {
                commonParamsUrlBuilder.addQueryParameter(item.getKey(), item.getValue());
            }
            newRequestBuild = oldRequest.newBuilder()
                    .method(oldRequest.method(), body)
                    .url(commonParamsUrlBuilder.build());

        } else {
            if (body instanceof FormBody) {
                FormBody.Builder newFormBody = new FormBody.Builder();
                FormBody oldFormBody = (FormBody) body;
                for (int i = 0; i < oldFormBody.size(); i++) {
                    String paramName = oldFormBody.encodedName(i);
                    newFormBody.addEncoded(paramName, oldFormBody.encodedValue(i));

                }
                for (Map.Entry<String, String> item : commonParams.entrySet()) {
                    newFormBody.add(item.getKey(), item.getValue());
                }


                newRequestBuild = oldRequest.newBuilder().method(oldRequest.method(), newFormBody.build());
            } else if (body instanceof RequestBody) {
                //buffer流
                HashMap rootMap = null;
                Gson mGson = new Gson();
                Buffer buffer = new Buffer();
                body.writeTo(buffer);
                String oldParamsJson = buffer.readUtf8();
                //原始参数
                rootMap = mGson.fromJson(oldParamsJson, HashMap.class);
                if (rootMap == null) {
                    rootMap = new HashMap<>(5);
                }

                for (Map.Entry<String, String> item : commonParams.entrySet()) {
                    rootMap.put(item.getKey(), item.getValue());
                }
                //装换成json字符串
                String newJsonParams = mGson.toJson(rootMap);

                newRequestBuild = oldRequest.newBuilder().post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), newJsonParams));
            }
        }

        if (newRequestBuild != null) {
            //添加 header 等
            if (AccountManager.getInstance().isLogin()) {
                newRequestBuild = newRequestBuild
                        .addHeader("Cookie", "loginUserName=" + AccountManager.getInstance().getUserName());
                newRequestBuild = newRequestBuild
                        .addHeader("Cookie", "loginUserPassword=" + AccountManager.getInstance().getUserPassword());
            }
            newRequest = newRequestBuild
                    .addHeader("Connection", "close")
                    .build();
            return chain.proceed(newRequest);
        }

        return null;
    }
}
