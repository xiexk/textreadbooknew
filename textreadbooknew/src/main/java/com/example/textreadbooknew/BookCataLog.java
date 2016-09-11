package com.example.textreadbooknew;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.textreadbooknew.model.BookLogcat;
import com.example.textreadbooknew.tool.BookTool;
import com.example.textreadbooknew.view.BookCateLogLinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/9.
 * 书本目录界面
 */
public class BookCataLog extends Activity {
    private BookCateLogLinearLayout logcatelinear;
    private List<BookLogcat> bookLogcats=new ArrayList<>(0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_booklogcate);
        bookLogcats= BookTool.readinBookLogcata(MainActivity.book_id);
        bookLogcats=BookTool.setLeven(bookLogcats,1);
        logcatelinear=(BookCateLogLinearLayout)findViewById(R.id.main_booklogcate_linear);
        logcatelinear.setBookCataLogs(bookLogcats);
     //   logcatelinear.updataView();
    }

    public void booklogcatemyclick(View v){
        switch (v.getId()){
            case R.id.booklogcate_bt_leven:

                break;
        }
    }
}
