package com.example.textreadbooknew.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Administrator on 2016/8/22.
 * 套题子选项model
 */
public class TaotiChildModel {

    @JSONField(name = "aId")
    public int aId;//套题选项id
    @JSONField(name = "parentId")
    public int parentId;//父题id
    @JSONField(name = "answerCount")
    public int answerCount;//回答个数
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

}
