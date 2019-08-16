package com.dareum.wlgid.dareum_app;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainNotice extends AppCompatActivity {

    String url = "http://awse.dothome.co.kr/Dareum/notice.html";
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_notice);

        WebView webView = (WebView)findViewById(R.id.noticeweb);
        webView.setVisibility(View.VISIBLE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClientClass());

    }


    private class WebViewClientClass extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view,String murl) {
            view.loadUrl(murl);
            return true;
        }
    }
}