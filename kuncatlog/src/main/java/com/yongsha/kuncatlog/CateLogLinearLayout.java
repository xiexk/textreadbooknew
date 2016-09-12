package com.yongsha.kuncatlog;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/9/9.
 * 书本目录linearlayout
 * <p/>
 * 1, 把所有目录初始化成view并排列好并设置所有的view为不可见状态
 * 2，显示第一级的目录
 * 3，添加监听：如果不是最后一级目录 根据修改数据中的isxepandent是否展开boolean值 并根据这个值来显示隐藏view
 * 如果是最后一级目录则返回参数
 */
public class CateLogLinearLayout extends LinearLayout implements View.OnClickListener {

    private LayoutInflater inflater;
    private List<View> views = new ArrayList<>(0);
    private List<Model_CatLog> modelCatLogs = new ArrayList<>(0);
    private List<Model_CatLog> click_childlogs = new ArrayList<>(0);
    private CateLogLinearLayoutListener cateLogLinearLayoutListener;

    public CateLogLinearLayout(Context context) {
        super(context);
    }

    public CateLogLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * @param modelCatLogs 设置 目录 数据
     */
    public void setBookCataLogs(List<Model_CatLog> modelCatLogs) {
        this.modelCatLogs = modelCatLogs;
        updataView(modelCatLogs);
        showFirstLog(modelCatLogs);
    }

    /**
     * 更新界面
     */
    public void updataView(List<Model_CatLog> modelCatLogs) {

        for (int i = 0; i < modelCatLogs.size(); i++) {
            Model_CatLog modelCatLog = modelCatLogs.get(i);
            View v = inflater.inflate(R.layout.layout_catlog, null);
            views.add(v);
            v.setTag(modelCatLog);
            v.setOnClickListener(this);
            addView(v);
            setText(v, modelCatLog);
            v.setVisibility(GONE);
            if (modelCatLog.islastcatlog) {
                setLevenImageview(v, 2);
            } else {
                updataView(modelCatLog.child);
            }
        }

    }

    /**
     * 设置text
     *
     * @param v           view
     * @param modelCatLog 目录数据
     */
    public void setText(View v, Model_CatLog modelCatLog) {
        TextView title = (TextView) v.findViewById(R.id.main_booklogcat_tv_title);
        title.setText(modelCatLog.title);
        title.setPadding(modelCatLog.leven * 20, 0, 0, 0);
    }

    /**
     * 设置imageview 箭头
     *
     * @param v     view
     * @param leven 层级
     */
    public void setLevenImageview(View v, int leven) {
        ImageView iv = (ImageView) v.findViewById(R.id.main_booklogcat_iv_right);
        iv.getDrawable().setLevel(leven);
    }

    @Override
    public void onClick(View v) {
        Model_CatLog tag = (Model_CatLog) v.getTag();
        tag.isxepandent = !tag.isxepandent;
        if (tag.islastcatlog) {
            //跳转到阅读界面
            System.out.println("我点击了" + tag.title);
            if(cateLogLinearLayoutListener!=null){
                cateLogLinearLayoutListener.cateLogReturn(tag);
            }
        } else {
            //根据是否展开显示隐藏子目录
            getChildLogs(modelCatLogs, tag.id);
            showHidentLog(click_childlogs, tag.isxepandent);
        }
    }

    /**
     * 初始化时默认展示第一集目录
     *
     * @param childlogs 第一级目录数据
     */
    private void showFirstLog(List<Model_CatLog> childlogs) {
        for (int i = 0; i < childlogs.size(); i++) {
            Model_CatLog a = childlogs.get(i);

            for (View v : views) {
                if (((Model_CatLog) v.getTag()).id.equals(a.id)) {
                    v.setVisibility(VISIBLE);
                }
            }
        }
    }

    /**
     * 显示隐藏目录
     */
    private void showHidentLog(List<Model_CatLog> childlogs, boolean show) {
        for (int i = 0; i < childlogs.size(); i++) {
            Model_CatLog a = childlogs.get(i);

            for (View v : views) {
                if (((Model_CatLog) v.getTag()).id.equals(a.id)) {
                    if (show) {
                        v.setVisibility(VISIBLE);
                    } else {
                        v.setVisibility(GONE);
                    }
                }
            }

            if (!show) {   //用来区别 收起目录的时候可以收起底下所有的子级目录  展开的时候只能一级一级展开
                if (a.child.size() > 0) {
                    showHidentLog(a.child, false);
                }
            }
        }
    }

    /**
     * 获取position下的目录
     *
     * @param id 根据id判断
     */
    private void getChildLogs(List<Model_CatLog> modelCatLogs, String id) {
        for (int i = 0; i < modelCatLogs.size(); i++) {
            Model_CatLog a = modelCatLogs.get(i);
            if (a.id.equals(id)) {
                click_childlogs = a.child;
            } else {
                if (a.child != null) {
                    getChildLogs(a.child, id);
                }
            }
        }
    }

    /**
     * 设置监听
     *
     * @param cateLogLinearLayoutListener 添加监听实例
     */
    public void setCateLogLinearLayoutListener(CateLogLinearLayoutListener cateLogLinearLayoutListener) {
        this.cateLogLinearLayoutListener = cateLogLinearLayoutListener;
    }

    /**
     * 目录返回监听
     */
    interface CateLogLinearLayoutListener {
        public void cateLogReturn(Model_CatLog model_catLog);
    }

}
