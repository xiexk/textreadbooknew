package com.example.textreadbooknew.tool;

import com.alibaba.fastjson.JSON;
import com.example.textreadbooknew.model.ArticleContent;
import com.example.textreadbooknew.model.BookLogcat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/16.
 * 书本工具
 */
public class BookTool {
    /**
     * 解析书本目录
     */
    public static  List<BookLogcat> readinBookLogcata(String book_id) {
        List<BookLogcat> bookLogcats=new ArrayList<>();
        try {

        File sdDir = new File(SdCardUtils.getSdKardFile().getPath()
                + KxxApiUtil.BOOK_DOWNLOAD_PATH + book_id + File.separator
                + book_id + ".clg");
        String read_content = Helper_IO.readFileContents(sdDir);
        bookLogcats = JSON.parseArray(read_content, BookLogcat.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return bookLogcats;
    }


    /**
     * 整理目录
     */
    public  static List<BookLogcat> extractLogcate(List<BookLogcat> bookLogcats,List<BookLogcat> bookLogcatssingle){
        for(int i=0;i<bookLogcats.size();i++){
            BookLogcat bookLogcat=bookLogcats.get(i);
            if(bookLogcat.count>0){
                bookLogcatssingle.add(bookLogcat);
            }else{
                extractLogcate(bookLogcat.child,bookLogcatssingle);
            }
        }
        return bookLogcatssingle;
    }

    /**
     * 设置目录层级目录
     */
    public  static List<BookLogcat> setLeven(List<BookLogcat> bookLogcats,int leven){
        for(int i=0;i<bookLogcats.size();i++){
            BookLogcat bookLogcat=bookLogcats.get(i);
            bookLogcat.leven=leven;
            if(bookLogcat.count>0){
           //     break;
            }else{
                setLeven(bookLogcat.child, leven+1);
            }
        }
        return bookLogcats;
    }

    /**
     * @param book_id 书本id
     * @param tit_id titid
     * @return 一个章节的内容（包含多个章节内容）
     */
    public static List<ArticleContent> getArticleContentList(String book_id, String tit_id) {
        List<ArticleContent> datas = new ArrayList<>();
        try {
            File sdDir = new File(SdCardUtils.getSdKardFile().getPath()
                    + KxxApiUtil.BOOK_DOWNLOAD_PATH + book_id + File.separator
                    + "artiles" + File.separator + tit_id + ".tit");
            String read_content = Helper_IO.readFileContents(sdDir);
            datas = JSON.parseArray(read_content, ArticleContent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datas;
    }

    /**
     * 设置目录position数据
     */
    public static void setBookLogcatPosition(List<BookLogcat> bookLogcatssingle){
        int position=0;
        for(BookLogcat a:bookLogcatssingle){
            a.startPosition=position;
            position=position+a.count-1;
            a.endPosition=position;
            position++;
        }
    }

    /**
     * @param bookLogcatssingle 整理后的目录（只有一级目录）
     * @return 文章总页数
     */
    public static int getAllArticleCount(List<BookLogcat> bookLogcatssingle){
        int a=0;
        for(BookLogcat b:bookLogcatssingle){
            a+=b.count;
        }
        return a;
    }

    /**
     * 根据adapter中的position获取对应目录
     * @param position adapter position
     * @return 章节目录
     */
    public static BookLogcat getBookLogcatByPosition(int position,List<BookLogcat> bookLogcatssingle){
        for(BookLogcat a:bookLogcatssingle){
            if(a.startPosition<=position&&a.endPosition>=position){
                return a;
            }
        }
        return null;
    }

}
