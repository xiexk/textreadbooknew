package com.example.textreadbooknew.tool.webviewtool;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.example.textreadbooknew.tool.SdCardUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/17.
 * webview 脚本点击事件
 */
public class WebViewJavaScript {
    private Activity context;

    public WebViewJavaScript(Activity context) {
        this.context = context;
    }

    /**
     * 图片点击
     */
    @JavascriptInterface
    public void ImageClick(int i, String src) {
        Log.v("JavaScripse", i + "src" + src+"");
        Log.v("JavaScripse", i + "sdpath" + SdCardUtils.getSdCardPath());

        Intent intent = null;
        ArrayList<String> urls = new ArrayList<>();

//        if (src.contains("http:")) {//网络地址
//            intent = new Intent(context, GalleryUrlActivity.class);
//        } else {
//           String a[]= src.split("file://");
//            try{
//            src=a[1];}catch (Exception e){e.printStackTrace();}
//            intent = new Intent(context, GalleryFileActivity.class);
//        }
//        urls.add(src);
//        if(intent!=null){
//            intent.putStringArrayListExtra("urls", urls);
//            context.overridePendingTransition(R.anim.imageactivity_lib_fade, 0);//切换Activity的过渡动画
//            context.startActivity(intent);
//        }

//
//        Intent intent=new Intent(context,ImageWebView.class);
//        intent.putExtra("add",src);
//        context.startActivity(intent);


//        File file=new File(src);
//        Intent it =new Intent(Intent.ACTION_VIEW);
//        Uri mUri = Uri.parse("file://" + file.getPath());
//        it.setDataAndType(mUri, "image/*");
//        context.startActivity(it);


    }
}
