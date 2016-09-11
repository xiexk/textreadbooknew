package com.example.textreadbooknew.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/22.
 * 阅读书本数据 书本目录和总页数等
 */
public class ReadBookData {
    public int totalPage=0;//总页数
    public List<BookLogcat> bookLogcats=new ArrayList<>(0);
}
