package com.example.textreadbooknew.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.textreadbooknew.R;
import com.example.textreadbooknew.model.BookLogcat;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/9/9.
 * 书本目录linearlayout
 *
 * 1, 把所有目录初始化成view并排列好并设置所有的view为不可见状态
 * 2，显示第一级的目录
 * 3，添加监听：如果不是最后一级目录 根据修改数据中的isxepandent是否展开boolean值 并根据这个值来显示隐藏view
 *    如果是最后一级目录则返回参数
 */
public class BookCateLogLinearLayout extends LinearLayout implements View.OnClickListener{

    private LayoutInflater inflater;
    private List<View> views=new ArrayList<>(0);
    private List<BookLogcat> bookLogcats=new ArrayList<>(0);

    public BookCateLogLinearLayout(Context context) {
        super(context);
    }
    public BookCateLogLinearLayout(Context context, AttributeSet attrs){
        super(context, attrs);
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * @param bookLogcats
     * 设置 目录 数据
     */
    public void setBookCataLogs(List<BookLogcat> bookLogcats){
        this.bookLogcats=bookLogcats;
        updataView(bookLogcats);
        showHidentLog(bookLogcats,true);
    }

    /**
     * 更新界面
     */
    public void updataView(List<BookLogcat> bookLogcats){

        for(int i=0;i<bookLogcats.size();i++){
            BookLogcat bookLogcat=bookLogcats.get(i);
            View v=inflater.inflate(R.layout.main_layout_booklogcat, null);
            views.add(v);
            v.setTag(bookLogcat);
            v.setOnClickListener(this);
            addView(v);
            setText(v, bookLogcat);
            v.setVisibility(GONE);
            if(bookLogcat.count>0){
                setLevenImageview(v,2);
            }else{
                updataView(bookLogcat.child);
            }
        }

    }

    /**
     * 设置text
     * @param v
     * @param bookLogcat
     */
    public void setText(View v,BookLogcat bookLogcat){
        TextView title=(TextView)v.findViewById(R.id.main_booklogcat_tv_title);
        title.setText(bookLogcat.title);
        title.setPadding(bookLogcat.leven * 20, 0, 0, 0);
    }

    /**
     * 设置imageview
     * @param v
     * @param leven
     */
    public void setLevenImageview(View v,int leven){
        ImageView iv=(ImageView)v.findViewById(R.id.main_booklogcat_iv_right);
        iv.getDrawable().setLevel(leven);
    }

    @Override
    public void onClick(View v) {
        BookLogcat tag=(BookLogcat)v.getTag();
        tag.isxepandent=!tag.isxepandent;
        if(tag.count>0){
            //跳转到阅读界面
        }else{
            //根据是否展开显示隐藏子目录
            List<BookLogcat> childlogs=getChildLogs(bookLogcats,tag.id);
            showHidentLog(childlogs,tag.isxepandent);
        }
    }



    /**
     * 显示隐藏目录
     */
    private void  showHidentLog(List<BookLogcat> childlogs,boolean show){
        for(int i=0;i<childlogs.size();i++){
            BookLogcat a=childlogs.get(i);
            for(View v:views){
                if(((BookLogcat)v.getTag()).id.equals(a.id)){
                    if(show){
                        v.setVisibility(VISIBLE);
                    }else{
                        v.setVisibility(GONE);
                    }
                }
            }
        }
    }

    /**
     * 获取position下的目录
     * @param position
     * @return
     */
    private List<BookLogcat> getChildLogs(List<BookLogcat> bookLogcats,String position){
        for(int i=0;i<bookLogcats.size();i++){
            BookLogcat a=bookLogcats.get(i);
            if(a.id.equals(position)){
                return a.child;
            }else{
                getChildLogs(a.child,position);
            }
        }
        return null;
    }

}
