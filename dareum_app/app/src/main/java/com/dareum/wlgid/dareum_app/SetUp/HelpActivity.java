package com.dareum.wlgid.dareum_app.SetUp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dareum.wlgid.dareum_app.R;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        WebView webView = (WebView)findViewById(R.id.helpweb);
        webView.setVisibility(View.VISIBLE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://awse.dothome.co.kr/Dareum/faq.html");
        webView.setWebViewClient(new WebViewClientClass());

    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view,String murl) {
            view.loadUrl(murl);
            return true;
        }
    }
}