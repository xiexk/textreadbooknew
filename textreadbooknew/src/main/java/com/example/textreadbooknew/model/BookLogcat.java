package com.example.textreadbooknew.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by Administrator on 2016/8/16.
 * 书本目录
 */

public class BookLogcat {
    @JSONField(name = "id")
    public String id;//目录id
    @JSONField(name = "title")
    public String title;//目录标题
    @JSONField(name = "count")
    public int count;//章节内容页数
    @JSONField(name = "child")
    public List<BookLogcat> child;//子目录

    //............................目录等级.....................................................
    public int leven;
    public boolean isxepandent=false;//是否展开（默认展开目录）

    //............................书本阅读adapter需要添加的参数......................................

    public int startPosition;//起始position,在整理好目录后只有一级目录的情况下才使用（也就是没有子目录了）
    public int endPosition;//结束position,在整理好目录后只有一级目录的情况下才使用（也就是没有子目录了）

}
