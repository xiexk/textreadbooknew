package com.example.textreadbooknew;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageWebView extends Activity {
	private WebView wb;
	private  boolean flag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		flag=true;
		setContentView(R.layout.my_web_view);

		Intent intent = getIntent();
		if(flag){
			String add = intent.getStringExtra("add");
			
			if (add != null && !TextUtils.isEmpty(add)) {
				wb = (WebView) this.findViewById(R.id.myWebView);
				WebSettings settings = wb.getSettings();
				settings.setUseWideViewPort(true);
				settings.setLoadWithOverviewMode(true);
				settings.setSupportZoom(true);
				settings.setBuiltInZoomControls(true);
				settings.setJavaScriptEnabled(true);

				String data = "<!DOCTYPE html>"
						+ "<html>"
						+ " <head>"
						+ "    <title>test.html</title>"
						+ "    <meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">"
						+ "   <meta http-equiv=\"description\" content=\"this is my page\">"
						+ "    <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">"
						+ "  </head>"
						+ "  <style type=\"text/css\">"
						+ "  </style>"
						+ "  <script type=\"text/javascript\">"
						+ "  window.onload = function(){"
						+ "		var height1 = document.body.scrollHeight;"
						+ "		var height2 = document.getElementById(\"imgTs\").offsetHeight;"
						+ "		var height = (height1*1-height2*1)/2;"
						+ "		var width1 = document.body.scrollWidth;"
						+ "		var width2 = document.getElementById(\"imgTs\").offsetWidth;"
						+ "		var width = (width1*1-width2*1)/2;"
						+ "  		document.getElementById(\"testdiv\").style.marginTop = height+\"px\";"
						+ "  		document.getElementById(\"testdiv\").style.marginLeft = width+\"px\";"
						+ "  	}" + "  </script>" + "  <body>"
						+ "    <div class=\"test\" id=\"testdiv\">"
						+ "    	<img id=\"imgTs\" alt=\"\" src=\"" + add + "\"/>"
						+ "    </div>" + "  </body>" + "</html>";
				wb.loadData(data, "text/html", "UTF-8");
			}
		}else{
			
			String add = intent.getStringExtra("add");
			//Bitmap bitMap = BitmapFactory.decodeFile(add);
			//add:/storage/emulated/0/KXX/Portrait/20140612_103310.jpg
			//imageUrl = "file:///mnt/sdcard/img/"+fileName; 无效果
			//add:~~:file:///storage/emulated/0/KXX/Portrait/20140612_103310.jpg
//			System.out.println("add:~~:"+add);
			if (add != null && !TextUtils.isEmpty(add)) {
				wb = (WebView) this.findViewById(R.id.myWebView);
				WebSettings settings = wb.getSettings();
				settings.setUseWideViewPort(true);
				settings.setLoadWithOverviewMode(true);
				settings.setSupportZoom(true);
				settings.setBuiltInZoomControls(true);
				settings.setJavaScriptEnabled(true);

				String data = "<!DOCTYPE html>"
						+ "<html>"
						+ " <head>"
						+ "    <title>test.html</title>"
						+ "    <meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">"
						+ "   <meta http-equiv=\"description\" content=\"this is my page\">"
						+ "    <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">"
						+ "  </head>"
						+ "  <style type=\"text/css\">"
						+ "  </style>"
						+ "  <script type=\"text/javascript\">"
						+ "  window.onload = function(){"
						+ "		var height1 = document.body.scrollHeight;"
						+ "		var height2 = document.getElementById(\"imgTs\").offsetHeight;"
						+ "		var height = (height1*1-height2*1)/2;"
						+ "		var width1 = document.body.scrollWidth;"
						+ "		var width2 = document.getElementById(\"imgTs\").offsetWidth;"
						+ "		var width = (width1*1-width2*1)/2;"
						+ "  		document.getElementById(\"testdiv\").style.marginTop = height+\"px\";"
						+ "  		document.getElementById(\"testdiv\").style.marginLeft = width+\"px\";"
						+ "  	}" 
					//	+ "     function onSaveCallback(src){"
					//	+ "		window.hui.showMessage(src);"
					//	+ "		document.getElementById(\"imgTs\").src = src;"
					//	+ "  	}"
						+ "  </script>" + "  <body>"
						+ "    <div class=\"test\" id=\"testdiv\">"
					//	+ "    	<img id=\"imgTs\" alt=\"\" src=\""  +"file://"   + add + "\"/>"
						+ "   	<img id=\"imgTs\" alt=\"\" src=\" "+ "file://" +add           +"\"/>"
						+ "    </div>" + "  </body>" + "</html>";
				//wb.loadData(data, "text/html", "UTF-8");
				// localHtmlImage();
				//1.sb.append("<img src=\"file:///sdcard/Test/zuzong.jpg\"/>");
				//2.webview.loadDataWithBaseURL(null, sb.toString(), "text/html", "utf-8", null);
				wb.loadDataWithBaseURL(null, data, "text/html", "utf-8", null);
			}	
		}
		
	}

	@Override 
	public boolean onKeyDown(int keyCode, KeyEvent event) { 
		if ((keyCode == KeyEvent.KEYCODE_BACK)) { 
//			System.out.println("按下了back键 onKeyDown()"); 
			ImageWebView.this.finish();
		return false; 
		}else { 
		return super.onKeyDown(keyCode, event); 
		} 
	} 
	
	 @Override
	    public void finish() {
	        ViewGroup view = (ViewGroup) getWindow().getDecorView();
	        view.removeAllViews();
	        super.finish();
	    }
	
	
	
	
	/**
	 * bitmap转String 在html显示 无用
	 * @param bitmap
	 * @return
	 */
	public String bitmaptoString(Bitmap bitmap) {  
        // 将Bitmap转换成Base64字符串  
        StringBuffer string = new StringBuffer();  
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();  
          
        try {  
            bitmap.compress(CompressFormat.PNG, 100, bStream);  
            bStream.flush();  
            bStream.close();  
            byte[] bytes = bStream.toByteArray();  
            string.append(Base64.encodeToString(bytes, Base64.NO_WRAP));  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
//        System.out.println("string.."+string.length());  
        return string.toString();  
    }
	

	
	  /**    
	   * 显示本地图片和文字混合的Html内容     无用
	   */   
	  static final String mimeType = "text/html";    
	 static final String encoding = "utf-8";
	private void localHtmlImage() { 
		try{													//file:///storage/emulated/0/KXX/Portrait/20140612_103310.jpg
			//String data = "<html>在模拟器 2.1 上测试,这是<IMG src=\"APK'>file:///android_asset/app_main_background.png \"/>APK里的图片</html>";
			 //wb.loadDataWithBaseURL(null, data, mimeType, encoding, null);
			//wb.loadData(data, mimeType, encoding);
			//System.out(19607): /storage/emulated/0/     a.jpg   KXX/Portrait/20140612_103310.jpg
			//System.out.println(Environment.getExternalStorageDirectory()+File.separator+"KXX/Portrait/20140612_103310.jpg");
			//wb.loadDataWithBaseURL(null,"<img src="+"file://"+Environment.getExternalStorageDirectory()+File.separator+"KXX/Portrait/20140612_103310.jpg"+"/>", "text/html", "UTF-8", null);
		} catch (Exception ex) { 
			 ex.printStackTrace();
		}

	}
	
	
	
	
	
	
}
