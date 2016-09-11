package com.example.textreadbooknew;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.example.textreadbooknew.model.ArticleContent;
import com.example.textreadbooknew.model.BookLogcat;
import com.example.textreadbooknew.tool.BookTool;
import com.example.textreadbooknew.tool.KxxApiUtil;
import com.example.textreadbooknew.tool.SdCardUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   public static  String book_id = "11341";//书本id
   // String book_id = "8473";//书本id
    public static String BOOK_DOWNLOAD_PATH = "/KXX/book_download/";
    private  List<BookLogcat> bookLogcats=new ArrayList<>();
    private  List<BookLogcat> bookLogcatssingle=new ArrayList<>();//提取过的章节目录只有最后一级
    private  List<ArticleContent> articleContents=new ArrayList<>();//这个章节的所有页面内容

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void myclick(View v) {
        switch (v.getId()) {
            case R.id.bt_readinbook://解压书本
                moveBook(book_id);
                break;
            case R.id.bt_readinbooklocate://解析目录
                bookLogcats= BookTool.readinBookLogcata(book_id);
                BookTool.extractLogcate(bookLogcats,bookLogcatssingle);
                Log.v("MainActivity", "解析目录完成");
                break;
            case R.id.bt_readinarticlecontent://解析一个章节内容
                articleContents=BookTool.getArticleContentList(book_id, bookLogcatssingle.get(0).id);
                break;
            case R.id.bt_readbook:
                Intent intent = new Intent(MainActivity.this, ReadPage.class);
                intent.putExtra("bookid",book_id);
                startActivity(intent);
                break;
            case R.id.bt_booklogcate:
                Intent intent2 = new Intent(MainActivity.this, BookCataLog.class);
                startActivity(intent2);
                break;
        }
    }

    private void moveBook(String book_id) {
        try {

            File molu = new File(SdCardUtils.getSdKardFile().getPath()
                    + BOOK_DOWNLOAD_PATH);
            if (!molu.exists())
                molu.mkdirs();

            Log.v("MainActivity", "书本解压开始");
            File sdDir = new File(SdCardUtils.getSdKardFile().getPath()
                    + BOOK_DOWNLOAD_PATH + book_id + ".zip");

            InputStream is = getAssets().open(book_id + ".zip");
            BufferedInputStream bis = new BufferedInputStream(is);
            OutputStream os = new FileOutputStream(sdDir);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            byte[] read_byte = new byte[3072];
            int read_length = 0;
            while ((read_length = bis.read(read_byte)) != -1) {
                bos.write(read_byte, 0, read_length);
            }
            bos.flush();
            bos.close();
            os.close();
            bis.close();
            is.close();
            if (ZipUtils.unZip(sdDir.getPath(), SdCardUtils.getSdKardFile()
                    .getPath() + BOOK_DOWNLOAD_PATH)) {
                sdDir = new File(SdCardUtils.getSdKardFile().getPath()
                        + BOOK_DOWNLOAD_PATH + book_id + ".zip");
                sdDir.delete();
                Log.v("MainActivity", "书本解压完成");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    /**
//     * 整理目录
//     */
//    private void extractLogcate(List<BookLogcat> bookLogcats){
//        for(int i=0;i<bookLogcats.size();i++){
//            BookLogcat bookLogcat=bookLogcats.get(i);
//            if(bookLogcat.count>0){
//                bookLogcatssingle.add(bookLogcat);
//            }else{
//                extractLogcate(bookLogcat.child);
//            }
//        }
//    }

}
