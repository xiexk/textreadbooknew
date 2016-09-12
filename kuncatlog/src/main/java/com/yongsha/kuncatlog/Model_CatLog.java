package com.yongsha.kuncatlog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/16.
 * 目录model
 */

public class Model_CatLog {
    public String id;//目录id
    public String title;//目录标题
    public boolean islastcatlog;//最后一级目录？
    public List<Model_CatLog> child=new ArrayList<>(0);//子目录
    public int leven;//目录等级（一级目录，二级目录......）
    public boolean isxepandent=false;//是否展开
}
