package com.example.textreadbooknew;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.textreadbooknew.model.ArticleContent;
import com.example.textreadbooknew.model.BookLogcat;
import com.example.textreadbooknew.model.ReadBookData;
import com.example.textreadbooknew.model.adapter.ReadBookAdapter;
import com.example.textreadbooknew.tool.BookTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/16.
 * 阅读书本界面
 */
public class ReadPage extends Activity {
    public static boolean isNightModel=false;
    private String bookid;
    private ViewPager viewPager;
    private ReadBookAdapter readBookAdapter;
    private ReadBookData readBookData;//阅读书本adapter需要的数据
    private List<BookLogcat> bookLogcatssingle = new ArrayList<>();//提取过的章节目录只有最后一级
    private List<ArticleContent> articleContents = new ArrayList<>();//这个章节的所有页面内容
    private boolean scrolled;//已经滑动
    private int currentposition;//当前viewpage的下标
    private int currentpositionOffsetPixels;//viewpage当前的像素用于判断左右滑动
    private boolean right;//右滑
    private boolean left;//左滑

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readpage);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        viewPager = (ViewPager) this.findViewById(R.id.read_page_viewpager);
    }

    private void initListener() {

//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.v("ReadPage", "onPageScrolled positionOffset=" + positionOffset + "  positionOffsetPixels=" + positionOffsetPixels);
//                if (positionOffsetPixels < currentpositionOffsetPixels) {//右滑
//                    right = true;
//                    left = false;
//                } else if (positionOffsetPixels > currentpositionOffsetPixels) {//左滑
//                    left = true;
//                    right = false;
//                }
//                currentpositionOffsetPixels = positionOffsetPixels;
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                currentposition = position;
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                Log.v("ReadPage", "onPageScrollStateChanged state=" + state + "currentposition=" + currentposition + " articleContents.size()=" + articleContents.size());
//                switch (state) {
//                    case 1:
//                        scrolled = true;
//                        break;
//                    case 2:
//                        scrolled = false;
//                        break;
//                    case 0:
//                        if (scrolled) {
//                            if (articleContents.size() == 1) {//一章节只有一页的情况
//                                if (right) {
//                                    nextChapter();
//                                } else if (left) {
//                                    previousChapter();
//                                }
//                            }else{
//                                if(currentposition == articleContents.size() - 1){//一章节有多页的情况
//                                    if(right){
//                                        nextChapter();
//                                    }
//                                }else if(currentposition==0){
//                                    if(left){
//                                        previousChapter();
//                                    }
//                                }
//                            }
//                        }
//                        break;
//                    default:
//                        break;
//                }
//            }
//        });
    }

    private void initData() {
        bookid = getIntent().getStringExtra("bookid");
        BookTool.extractLogcate(BookTool.readinBookLogcata(bookid), bookLogcatssingle);//获取整理好的目录
        BookTool.setBookLogcatPosition(bookLogcatssingle);
        readBookData=new ReadBookData();
        readBookData.bookLogcats=bookLogcatssingle;
        readBookData.totalPage=BookTool.getAllArticleCount(bookLogcatssingle);
        articleContents = BookTool.getArticleContentList(bookid, bookLogcatssingle.get(12).id);
        readBookAdapter = new ReadBookAdapter(ReadPage.this, readBookData, bookid);
        viewPager.setAdapter(readBookAdapter);
        viewPager.setCurrentItem(73);
    }

    /**
     * 下一章节
     */
    private void nextChapter() {
        articleContents = BookTool.getArticleContentList(bookid, bookLogcatssingle.get(13).id);
    //    readBookAdapter.setData(bookLogcatssingle.get(13), articleContents);
        viewPager.setCurrentItem(0);
    }

    /**
     * 上一章节
     */
    private void previousChapter() {
        articleContents = BookTool.getArticleContentList(bookid, bookLogcatssingle.get(0).id);
 //       readBookAdapter.setData(bookLogcatssingle.get(0), articleContents);
        viewPager.setCurrentItem(articleContents.size()-1);
    }


}
