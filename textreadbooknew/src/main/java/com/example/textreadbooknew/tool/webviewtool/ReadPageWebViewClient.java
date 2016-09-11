package com.example.textreadbooknew.tool.webviewtool;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Administrator on 2016/8/17.
 * 自定义webviewclient
 */
public class ReadPageWebViewClient extends WebViewClient {
    private WebView webView;
    public ReadPageWebViewClient(WebView webView){
        this.webView=webView;
    }
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        injectImgClick();
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    // 注入js函数监听
    public void injectImgClick() {
        webView.loadUrl("javascript:(function(){var objs = document.getElementsByTagName('img');for(var i = 0; i <objs.length; i++) {objs[i].onclick = function() {window.toolbox.ImageClick(i,this.src);};}})()");
    }
}
