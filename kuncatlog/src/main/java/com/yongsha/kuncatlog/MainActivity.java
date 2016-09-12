package com.yongsha.kuncatlog;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private CateLogLinearLayout logcatelinear;
    private List<Model_CatLog> modelCatLogs = new ArrayList<>(0);
private CateLogLinearLayout.CateLogLinearLayoutListener cateLogLinearLayoutListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catlog);
        initCatLog();
        logcatelinear = (CateLogLinearLayout) findViewById(R.id.main_booklogcate_linear);
        logcatelinear.setBookCataLogs(modelCatLogs);
        cateLogLinearLayoutListener=new CateLogLinearLayout.CateLogLinearLayoutListener() {
            @Override
            public void cateLogReturn(Model_CatLog model_catLog) {
                Toast.makeText(MainActivity.this,model_catLog.title+"",Toast.LENGTH_SHORT).show();
            }
        };
        logcatelinear.setCateLogLinearLayoutListener(cateLogLinearLayoutListener);
    }

    /**
     * 初始化目录数据 有三级目录
     */
    private void initCatLog() {
        for (int i = 0; i < 8; i++) {
            Model_CatLog catlog_1 = new Model_CatLog();
            catlog_1.id = i + "";
            catlog_1.islastcatlog = false;
            catlog_1.leven = 1;
            catlog_1.title = "我是目录" + i;

            List<Model_CatLog> chiled2 = new ArrayList<>(0);
            for (int j = 0; j < 5; j++) {

                Model_CatLog catlog_2 = new Model_CatLog();
                catlog_2.id = i + "" + j;
                catlog_2.islastcatlog = false;
                catlog_2.leven = 2;
                catlog_2.title = "我是目录" + i + "--" + j;
                List<Model_CatLog> chiled3 = new ArrayList<>(0);

                for (int z = 0; z < 3; z++) {

                    Model_CatLog catlog_3 = new Model_CatLog();
                    catlog_3.id = i + "" + j + "" + z;
                    catlog_3.islastcatlog = true;
                    catlog_3.leven = 3;
                    catlog_3.title = "我是目录" + i + "--" + j + "--" + z;
                    chiled3.add(catlog_3);
                }
                catlog_2.child = new ArrayList<>(0);
                catlog_2.child.addAll(chiled3);
                chiled2.add(catlog_2);
            }
            catlog_1.child = new ArrayList<>(0);
            catlog_1.child.addAll(chiled2);

            modelCatLogs.add(catlog_1);
        }
    }

}
