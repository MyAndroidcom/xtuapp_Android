package com.xtuapp.zsxd.activity.home;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xtuapp.zsxd.R;
import com.xtuapp.zsxd.activity.base.BaseActivity;

import org.xutils.view.annotation.ViewInject;

/**
 * Created by Administrator on 2016/4/7 0007.
 */
public class SchoolActivity extends BaseActivity{
    @ViewInject(R.id.wv_web)
    private WebView mWebView;
    String SCHOOL_URL = "http://www.xtu.edu.cn/intro/brief/";
    @Override
    protected int setView() {
        return R.layout.activity_school_intr;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);//支持js
        settings.setBuiltInZoomControls(true);//支持放大缩小
        settings.setUseWideViewPort(true);//支持双击缩放


        mWebView.setWebViewClient(new WebViewClient(){
//            开始加载时

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.loadUrl(SCHOOL_URL);
    }

    @Override
    protected void initData(Bundle savedInstanceStatet) {

    }
}
