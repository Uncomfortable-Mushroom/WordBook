package com.example.lenovo.wordbook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/10/19.
 */

public class NEWS  extends AppCompatActivity {
    private CustomActionWebView mCustomActionWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        mCustomActionWebView=(CustomActionWebView) findViewById(R.id.web_view);
        mCustomActionWebView.setWebViewClient(new CustomWebViewClient());

        List<String> list = new ArrayList<>();
        list.add("翻译该词");

        //设置item
        mCustomActionWebView.setActionList(list);

        //链接js注入接口，使能选中返回数据
        mCustomActionWebView.linkJSInterface();

        mCustomActionWebView.getSettings().setBuiltInZoomControls(true);
        mCustomActionWebView.getSettings().setDisplayZoomControls(false);
        //使用javascript
        mCustomActionWebView.getSettings().setJavaScriptEnabled(true);
        mCustomActionWebView.getSettings().setDomStorageEnabled(true);

        mCustomActionWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mCustomActionWebView.getSettings().setLoadWithOverviewMode(true);



        //增加点击回调
        mCustomActionWebView.setActionSelectListener(new ActionSelectListener() {
            @Override
            public void onClick(String title, String selectText) {
                if(title.equals("翻译该词") ){
                    Intent intent = new Intent(NEWS.this, InternetSearch.class);
                    intent.putExtra("extra", selectText);
                    startActivity(intent);
                    return;
                }
            }
        });

        mCustomActionWebView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mCustomActionWebView.loadUrl("http://english.sina.com/");
            }
        }, 1000);


}
    @Override
    protected void onPause() {
        super.onPause();
        if(mCustomActionWebView != null) {
            mCustomActionWebView.dismissAction();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private class CustomWebViewClient extends WebViewClient {

        private boolean mLastLoadFailed = false;

        @Override
        public void onPageFinished(WebView webView, String url) {
            super.onPageFinished(webView, url);
            if (!mLastLoadFailed) {
                CustomActionWebView customActionWebView = (CustomActionWebView) webView;
                customActionWebView.linkJSInterface();

            }
        }

        @Override
        public void onPageStarted(WebView webView, String url, Bitmap favicon) {
            super.onPageStarted(webView, url, favicon);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            mLastLoadFailed = true;
        }
    }

}
