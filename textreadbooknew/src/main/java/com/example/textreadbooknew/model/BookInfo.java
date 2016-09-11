package com.example.textreadbooknew.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Administrator on 2016/8/16.
 * 书本基本信息
 */

public class BookInfo {
    @JSONField(name = "id")
    public String id; //书本id
    @JSONField(name = "author")
    public String author; //作者
    @JSONField(name = "brief")
    public String brief;//书本简介
     @JSONField(name = "cover")
    public String cover;//书本封面地址
    @JSONField(name = "name")
    public String name; //书本名称
    @JSONField(name = "subjectId")
    public int subjectId;//学科分类
    @JSONField(name = "subjectName")
    public String subjectName; //学科名称

}
