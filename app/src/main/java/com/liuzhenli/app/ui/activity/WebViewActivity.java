package com.liuzhenli.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.liuzhenli.app.R;
import com.liuzhenli.app.base.BaseActivity;
import com.liuzhenli.app.databinding.ActWebviewBinding;
import com.liuzhenli.app.network.AppComponent;
import com.liuzhenli.app.view.webview.ZLWebChromeClient;
import com.liuzhenli.app.view.webview.ZLWebViewClient;

/**
 * describe: web view
 *
 * @author Liuzhenli on 2019-08-17 19:27
 * @since 1.0.0
 */
public class WebViewActivity extends BaseActivity {
    public static final String INTENT_ID = "url";
    public ValueCallback<Uri[]> mUploadMessageForAndroid5;
    public ValueCallback<Uri> mUploadMessage;
    private String mUrl;

    private ActWebviewBinding binding;

    public static void start(Context context, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(INTENT_ID, url);
        context.startActivity(intent);
    }

    @Override
    protected View bindContentView() {
        binding = ActWebviewBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public int getContextViewId() {
        return R.layout.act_webview;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {
        mUrl = getIntent().getStringExtra(INTENT_ID);
        binding.webView.setWebChromeClient(new ZLWebChromeClient(mContext));
        binding.webView.setWebViewClient(new ZLWebViewClient(mContext));
        WebSettings settings = binding.webView.getSettings();
        settings.setJavaScriptEnabled(true);
        // mWebView.addJavascriptInterface(new JsReadBook(mHandler), "test");
        //解决没有声音的问题
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启本地存储，某些功能才能触发（比如网站触摸式菜单）
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        String cacheDirPath = getFilesDir().getAbsolutePath() + "/webcache";
        // String cacheDirPath =
        // getCacheDir().getAbsolutePath()+Constant.APP_DB_DIRNAME;
        // 设置数据库缓存路径
        settings.setDatabasePath(cacheDirPath);
        // 设置 Application Caches 缓存目录
        settings.setAppCachePath(cacheDirPath);
        settings.setAppCacheEnabled(true);
        // 设置可以访问文件
        settings.setAllowFileAccess(true);
        binding.webView.loadUrl(mUrl);
    }

    public void setRefresh(boolean b) {

    }

    public void startRefresh() {

    }
}
