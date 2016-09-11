package com.example.textreadbooknew.tool.read;


import com.example.textreadbooknew.tool.KxxApiUtil;
import com.example.textreadbooknew.tool.SdCardUtils;

/**
 * Created by Administrator on 2016/8/18.
 * 阅读界面的工具
 */
public class ReadBookPageTool {

    /**
     * 为String中 的img添加style
     * @param a 要替换的String
     * @return 添加img样式
     */
    public static String changeImgType(String a){
        a=a.replaceAll("<img\\s","<img style=\"vertical-align:middle\"");
        return a;
    }

    /**
     * 为String替换本地图片路径
     */
    public static String changeImgUrl(String a,String bookid){
        String path = "file://" + SdCardUtils.getSdKardFile().getPath()+ KxxApiUtil.BOOK_IMAGE_PATH+"/"+bookid+"/images";
        a=a.replaceAll("images", path);
        return a;
    }

    /**
     * 为String替换本地图片路径(没有file://开头)
     */
    public static String changeImgUrlNoFileHead(String a,String bookid){
        String path = SdCardUtils.getSdKardFile().getPath()+ KxxApiUtil.BOOK_IMAGE_PATH+"/"+bookid+"/images";
        a=a.replaceAll("images", path);
        return a;
    }

}
