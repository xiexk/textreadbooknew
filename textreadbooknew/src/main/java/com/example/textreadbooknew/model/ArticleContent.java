package com.example.textreadbooknew.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by Administrator on 2016/8/16.
 * 文章内容
 */
public class ArticleContent {

    @JSONField(name = "id")
    public String id;//文章id
    @JSONField(name = "type")
    public int type;//文章类型 1是知识点 0是做题 3是套题
    @JSONField(name = "title")
    public String title;//文章标题

    //...........................................知识点内容.........................................

    @JSONField(name = "body")
    public String body;//（知识点）文章内容
    @JSONField(name = "knowledge")
    public String knowledge;//（知识点）考查点

    //...........................................做题内容...........................................

    @JSONField(name = "subjectKnowledge")
    public String subjectKnowledge;//（做题内容）考查点
    @JSONField(name = "subjectType")
    public String subjectType;//（做题内容）题型名称
    @JSONField(name = "subjectContent")
    public String subjectContent;//（做题内容）题干
    @JSONField(name = "subjectOpation")
    public String subjectOpation;//（做题内容）题目选项
    @JSONField(name = "subjectAnswer")
    public String subjectAnswer;//（做题内容）题目答案
    @JSONField(name = "subjectAnalysis")
    public String subjectAnalysis;//（做题内容）题目解析
    @JSONField(name = "subjectDirect")
    public String subjectDirect;//（做题内容）题目重点


    //...........................................套题(包含做题内容和以下内容).........................

    @JSONField(name = "parentId")
    public String parentId;//父题id
    @JSONField(name = "clientId")
    public String clientId;//客户端id（暂时不知道拿来干嘛）
    @JSONField(name = "answerCount")
    public int answerCount;//回答个数
    @JSONField(name = "aId")
    public int aId;//（暂时不知道拿来干嘛）
    @JSONField(name = "typeId")
    public int typeId;//（暂时不知道拿来干嘛）
    @JSONField(name = "childSub")
    public List<TaotiChildModel> childSub;

}
