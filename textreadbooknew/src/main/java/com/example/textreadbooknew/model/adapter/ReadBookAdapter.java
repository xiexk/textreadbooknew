package com.example.textreadbooknew.model.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.textreadbooknew.R;
import com.example.textreadbooknew.ReadPage;
import com.example.textreadbooknew.config.Config_Article;
import com.example.textreadbooknew.model.ArticleContent;
import com.example.textreadbooknew.model.BookLogcat;
import com.example.textreadbooknew.model.ReadBookData;
import com.example.textreadbooknew.tool.BookTool;
import com.example.textreadbooknew.tool.Helper_Phone;
import com.example.textreadbooknew.tool.read.ReadBookPageTool;
import com.example.textreadbooknew.tool.webviewtool.ReadPageWebViewClient;
import com.example.textreadbooknew.tool.webviewtool.WebViewJavaScript;

import org.apache.tools.ant.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/8/17.
 * 阅读书本界面适配器（viewpage的内容适配器）
 */
public class ReadBookAdapter extends PagerAdapter {
    private Activity context;
    private LayoutInflater layoutInflater;
    private ReadBookData readBookData;
    private String color_white = "#FFFFFF";// 夜间模式时，字体颜色设置为白色
    private WebViewJavaScript webViewJavaScript;
    private ReadPageWebViewClient readPageWebViewClient;
    private String bookid;
    private String backgroundColor = "#182432";
    private String newbackgroundColor = "#FFFFFF";// #CBE4CF
    private String fontColor = "#FFFFFF";// 夜间模式时，字体颜色设置为白色

    public ReadBookAdapter(Activity context, ReadBookData readBookData,String bookid) {
        this.readBookData = readBookData;
        this.context = context;
        this.bookid=bookid;
        this.layoutInflater = context.getLayoutInflater();
    }


    @Override
    public int getCount() {
        return readBookData.totalPage;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BookLogcat currentLogcat= BookTool.getBookLogcatByPosition(position, readBookData.bookLogcats);
        List<ArticleContent> articleContents=BookTool.getArticleContentList(bookid, currentLogcat.id);
        ArticleContent data=articleContents.get(position-currentLogcat.startPosition);
        View view = null;
        try {
            view = layoutInflater.inflate(R.layout.layout_readpage, null);
            LinearLayout ll_content = (LinearLayout) view.findViewById(R.id.layout_readpage_ll);
            View content = null;//根据文章类型加载不同的界面
            switch (data.type) {//1是知识点 0是做题 3是套题 mark
                case Config_Article.ARTICLE_KNOWLEDGE://知识点
                    content = layoutInflater.inflate(R.layout.layout_read_knowledge, null);
                    showKnowledgePage(content, currentLogcat, data, position);
                    break;
                case Config_Article.ARTICLE_SUBJECT://题目
                    content = layoutInflater.inflate(R.layout.layout_read_subject, null);
                    showSubjectPage(content,currentLogcat,data,position);
                    break;
                case Config_Article.ARTICLE_SET_QUESTION://套题
                    content = layoutInflater.inflate(R.layout.layout_taoti_page, null);
                    showSetQuestionPage(content,currentLogcat,data,position);
                    break;
            }
            ll_content.addView(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        container.addView(view);
        return view;
    }

    /**
     * 展示知识点内容
     */
    private void showKnowledgePage(View view, BookLogcat bookLogcat, ArticleContent data, int position) {

        WebView webView = (WebView) view.findViewById(R.id.read_knowledge_webview);
        String web_content = "<div><div style=\"float:left;top:0;font-weight:bold;position:relative; width:80%; height:20px; line-height:20px; text-overflow:ellipsis; white-space:nowrap; *white-space:nowrap; overflow:hidden; \">"
                + "[" + bookLogcat.title + "]" + "</div><span style=\"float:right;top:0\">"
                + (position-bookLogcat.startPosition+1) + "/" + bookLogcat.count + "</span></div>" + "</span></div><br /><div style=\"padding-bottom:10px;padding-top:10px\">"
                + data.title + "</div>";
        web_content += data.body;
        web_content=ReadBookPageTool.changeImgUrl(web_content, bookid);
        web_content=ReadBookPageTool.changeImgType(web_content);

        if (ReadPage.isNightModel) {
            web_content = "<body style=\"color:" + color_white + ";\"> " + web_content + "</body>";
        }
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setJavaScriptEnabled(true);
        webViewJavaScript = new WebViewJavaScript(context);
        webView.addJavascriptInterface(webViewJavaScript, "toolbox");
        readPageWebViewClient = new ReadPageWebViewClient(webView);
        webView.setWebViewClient(readPageWebViewClient);
        webView.loadDataWithBaseURL(null, web_content, "text/html", "utf-8", null);
    }

    /**
     * 展示题目内容
     */
    private void showSubjectPage(View view, BookLogcat bookLogcat, ArticleContent data, int position) {
        try {

        // 文章名称和标题区域
        WebView subject_up = (WebView) view.findViewById(R.id.subject_up);
        String web_content = "<div><div style=\"float:left;top:0;font-weight:bold;position:relative; width:80%; height:20px; line-height:20px; text-overflow:ellipsis; white-space:nowrap; *white-space:nowrap; overflow:hidden; \">"
                + "[" + bookLogcat.title+ "]" + "</div><span style=\"float:right;top:0\">"
                + (position-bookLogcat.startPosition+1) + "/" + bookLogcat.count + "</span></div>";
        web_content += "<br /><br /><div><div style=\"font-weight:bold\">"
                + "[" + data.subjectType + "]" + "</div>";

        web_content += data.title;
        web_content= ReadBookPageTool.changeImgType(web_content);
        web_content= ReadBookPageTool.changeImgUrl(web_content,bookid);
        view.setBackgroundColor(Color.parseColor(newbackgroundColor));
        subject_up.setBackgroundColor(Color.parseColor(newbackgroundColor));
        if (ReadPage.isNightModel) {
            view.setBackgroundColor(Color .parseColor(backgroundColor));
            subject_up.setBackgroundColor(Color .parseColor(backgroundColor));
            web_content = "<body style=\"color:" + fontColor + ";\"> " + web_content + "</body>";
        }
        subject_up.getSettings().setLayoutAlgorithm(
                WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        subject_up.getSettings().setJavaScriptEnabled(true);
        subject_up.addJavascriptInterface(new WebViewJavaScript(context), "toolbox");
        subject_up.setWebViewClient(new ReadPageWebViewClient(subject_up));
        subject_up.loadDataWithBaseURL(null, web_content, "text/html", "utf-8", null);

        // 选项区域
        final LinearLayout subject_check_box = (LinearLayout) view
                .findViewById(R.id.subject_check_box);// 选项区域
        final RelativeLayout sub_conten = (RelativeLayout) view
                .findViewById(R.id.sub_conten);// 提交区域
        TextView sub_btn = (TextView) view
                .findViewById(R.id.sub_btn);// 提交按钮
        TextView sub_text_btn = (TextView) view
                .findViewById(R.id.sub_text_btn);// 提交字符串
        final RelativeLayout down_content = (RelativeLayout) view
                .findViewById(R.id.down_content);// 答案区域
        RelativeLayout item_1 = (RelativeLayout) view
                .findViewById(R.id.item_1);// 我的答案区域
        final TextView text_1_1_1 = (TextView) view
                .findViewById(R.id.text_1_1_1);// 我的答案

        // 判断选项是否为空
        if ("[]".equals(data.subjectOpation)) {
            subject_check_box.setVisibility(View.GONE);
            sub_btn.setText("直接查看答案");
            sub_text_btn.setVisibility(View.GONE);
            item_1.setVisibility(View.GONE);
        } else {
            final String answer = data.subjectAnswer;
            final ArrayList<String> selete_map = new ArrayList<>();
            JSONArray array_json = new JSONArray(data.subjectOpation);
            for (int i = 0; i < array_json.length(); i++) {
                String object = array_json.get(i).toString();
                // appTools.sysOutMessage("套题object"+object);
                // final CheckBox btn = new CheckBox(ReadPage.this);
                final CheckBox btn = new CheckBox(context);
                String btn_text = object.toString().replaceAll("<br/>", "");
                btn_text= ReadBookPageTool.changeImgType(btn_text);
                btn_text= ReadBookPageTool.changeImgUrlNoFileHead(btn_text,bookid);
                if (ReadPage.isNightModel) {
                    item_1.setBackgroundColor(Color.parseColor(backgroundColor));
                    btn.setTextColor(Color.parseColor(fontColor));
                }
                // 设置布局选项的布局
                if (btn_text.indexOf("sup") != -1) {
                    btn_text = "<br/><span style='vertical-align:middle;'>" + btn_text + "</span>";
                }
                btn.setText(Html.fromHtml(btn_text,
                        new Html.ImageGetter() {
                            @Override
                            public Drawable getDrawable(
                                    String source) {
                                Drawable drawable = new BitmapDrawable();
                                try {
                                    drawable = Drawable.createFromPath(source); // 获取网路图片
                                    if (drawable.getIntrinsicHeight() < 40) {
                                        drawable.setBounds(0, 0,drawable.getIntrinsicWidth() * 5,drawable.getIntrinsicHeight() * 5);
                                        return drawable;
                                    } else {
                                        drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
                                        return drawable;
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    return null;
                                }
                            }
                        }, null));
                btn.setPadding(10, 10, 10, 10);
                btn.setButtonDrawable(R.drawable.check_box_type);
                btn.setGravity(Gravity.TOP);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (btn.isChecked()) {
                            selete_map.add(btn.getText().toString()
                                    .substring(0, 1));
                            // btn.setChecked(false);
                        } else {
                            selete_map.remove(btn.getText()
                                    .toString().substring(0, 1));
                            // btn.setChecked(true);
                        }
                        StringBuffer sb = new StringBuffer();
                        Iterator it = selete_map.iterator();
                        while (it.hasNext()) {
                            sb.append(it.next());
                        }
                        if (answer.equals(sb.toString())) {
                            text_1_1_1.setText(sb.toString()+ "（√）");
                        } else {
                            text_1_1_1.setText(sb.toString() + "（×）");
                        }
                    }
                });
                android.widget.RadioGroup.LayoutParams params = new android.widget.RadioGroup.LayoutParams(
                        android.widget.RadioGroup.LayoutParams.MATCH_PARENT,
                        android.widget.RadioGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 20, 0, 0);
                btn.setPadding(15, 0, 0, 0);
                subject_check_box.addView(btn, params);
            }
        }

        // 提交按钮
        sub_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                down_content.setVisibility(View.VISIBLE);
                sub_conten.setVisibility(View.GONE);
                for (int i = 0; i < subject_check_box.getChildCount(); i++) {
                    CheckBox cb = (CheckBox) subject_check_box .getChildAt(i);
                    cb.setEnabled(false);
                }
            }
        });
        sub_text_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                down_content.setVisibility(View.VISIBLE);
                sub_conten.setVisibility(View.GONE);
                for (int i = 0; i < subject_check_box
                        .getChildCount(); i++) {
                    CheckBox cb = (CheckBox) subject_check_box
                            .getChildAt(i);
                    cb.setEnabled(false);
                }
            }
        });

        // 参考答案
        WebView text_2_1_1 = (WebView) view
                .findViewById(R.id.text_2_1_1);
        text_2_1_1.getSettings().setLayoutAlgorithm(
                WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        text_2_1_1.getSettings().setJavaScriptEnabled(true);
        text_2_1_1.addJavascriptInterface(new WebViewJavaScript(context), "toolbox");
        text_2_1_1.setWebViewClient(new ReadPageWebViewClient(text_2_1_1));
        RelativeLayout item_2 = (RelativeLayout) view
                .findViewById(R.id.item_2);
        if (TextUtils.isEmpty(data.subjectAnswer)) {
            item_2.setVisibility(View.GONE);
        } else {
            String text_2_1_1_body = data.subjectAnswer;
            text_2_1_1_body=ReadBookPageTool.changeImgUrl(text_2_1_1_body,bookid);
            text_2_1_1.setBackgroundColor(Color
                    .parseColor(newbackgroundColor));
            item_2.setBackgroundColor(Color
                    .parseColor(newbackgroundColor));
            if (ReadPage.isNightModel) {
                text_2_1_1.setBackgroundColor(Color .parseColor(backgroundColor));
                item_2.setBackgroundColor(Color.parseColor(backgroundColor));
                text_2_1_1_body = "<body style=\"color:"+ fontColor + ";\"> " + text_2_1_1_body + "</body>";
            }
            text_2_1_1_body=ReadBookPageTool.changeImgType(text_2_1_1_body);
            text_2_1_1.loadDataWithBaseURL(null, text_2_1_1_body, "text/html", "utf-8", null);
        }
        // 点播
        WebView text_3_2 = (WebView) view
                .findViewById(R.id.text_3_2);
        text_3_2.getSettings().setLayoutAlgorithm(
                WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        text_3_2.getSettings().setJavaScriptEnabled(true);
        text_3_2.addJavascriptInterface(new WebViewJavaScript(context), "toolbox");
        text_3_2.setWebViewClient(new ReadPageWebViewClient(text_3_2));
        RelativeLayout item_3 = (RelativeLayout) view
                .findViewById(R.id.item_3);
        if (TextUtils.isEmpty(data.subjectDirect)) {
            item_3.setVisibility(View.GONE);
        } else {
            String text_3_2_body = data.subjectDirect;

            text_3_2_body=ReadBookPageTool.changeImgUrl(text_3_2_body,bookid);
            text_3_2.setBackgroundColor(Color.parseColor(newbackgroundColor));
            item_3.setBackgroundColor(Color.parseColor(newbackgroundColor));
            if (ReadPage.isNightModel) {
                text_3_2.setBackgroundColor(Color.parseColor(backgroundColor));
                item_3.setBackgroundColor(Color.parseColor(backgroundColor));
                text_3_2_body = "<body style=\"color:" + fontColor + ";\"> " + text_3_2_body + "</body>";
            }
            text_3_2_body=ReadBookPageTool.changeImgType(text_3_2_body);
            text_3_2.loadDataWithBaseURL(null, text_3_2_body, "text/html", "utf-8", null);
        }
        // 解析
        WebView text_4_2 = (WebView) view
                .findViewById(R.id.text_4_2);
        text_4_2.getSettings().setLayoutAlgorithm(
                WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        text_4_2.getSettings().setJavaScriptEnabled(true);
        text_4_2.addJavascriptInterface(new WebViewJavaScript(context), "toolbox");
        text_4_2.setWebViewClient(new ReadPageWebViewClient(text_4_2));
        RelativeLayout item_4 = (RelativeLayout) view .findViewById(R.id.item_4);
        if (TextUtils.isEmpty(data.subjectAnalysis)) {
            item_4.setVisibility(View.GONE);
        } else {
            String text_4_body = data.subjectAnalysis;
            text_4_body=ReadBookPageTool.changeImgUrl(text_4_body,bookid);
            text_4_2.setBackgroundColor(Color .parseColor(newbackgroundColor));
            item_4.setBackgroundColor(Color .parseColor(newbackgroundColor));
            if (ReadPage.isNightModel) {
                text_4_2.setBackgroundColor(Color .parseColor(backgroundColor));
                item_4.setBackgroundColor(Color .parseColor(backgroundColor));
                text_4_body = "<body style=\"color:" + fontColor + ";\"> " + text_4_body + "</body>";
            }
            text_4_body=ReadBookPageTool.changeImgType(text_4_body);
            text_4_2.loadDataWithBaseURL(null, text_4_body, "text/html", "utf-8", null);
         }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 展示套题内容
     */
    private void showSetQuestionPage(View view, BookLogcat bookLogcat, ArticleContent data, int position) {
        final RelativeLayout top_content = (RelativeLayout) view .findViewById(R.id.la_taoti_top_content);
        try {

            // 题目头部内容
            WebView parnet_content = (WebView) view.findViewById(R.id.parnet_content);
            String web_content = "<div><div style=\"float:left;top:0;font-weight:bold;position:relative; width:80%; height:20px; line-height:20px; text-overflow:ellipsis; white-space:nowrap; *white-space:nowrap; overflow:hidden; \">"
                    + "["+ bookLogcat.title+ "]"+ "</div><span style=\"float:right;top:0\">"
                    + (position-bookLogcat.startPosition+1) + "/" + bookLogcat.count + "</span></div>";
            web_content += "<br /><br /><div><div style=\"font-weight:bold\">"+ "["
                    + data.subjectType + "]" + "</div>";
            web_content += data.subjectContent;
            web_content=ReadBookPageTool.changeImgUrl(web_content, bookid);
            web_content=ReadBookPageTool.changeImgType(web_content);

            if (ReadPage.isNightModel) {
                parnet_content.setBackgroundColor(Color
                        .parseColor(backgroundColor));
                web_content = "<body style=\"color:" + fontColor + ";\"> "
                        + web_content + "</body>";
                web_content = web_content.replaceAll("#000000", fontColor);
            }
            // AppContext.sysLogMessage("read content = ", modle.body);
            parnet_content.getSettings().setLayoutAlgorithm(
                    WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            parnet_content.getSettings().setJavaScriptEnabled(true);
            parnet_content.addJavascriptInterface(new WebViewJavaScript(context), "toolbox");
            parnet_content.setWebViewClient(new ReadPageWebViewClient(parnet_content));
            parnet_content.loadDataWithBaseURL(null, web_content,"text/html", "utf-8", null);
            // 套题题目内容
            ViewPager taoti_viewpager = (ViewPager) view .findViewById(R.id.taoti_viewpager);
//            String childSub_str = data.childSub;
//            if (ReadPage.isNightModel) {
//                childSub_str = childSub_str .replaceAll("#000000", fontColor);
//            }
//            JSONArray jsonArr = new JSONArray(childSub_str);
            TaoTiAdapter taoTiadapter = new TaoTiAdapter(context,data.childSub,bookid, taoti_viewpager);
            taoti_viewpager.setAdapter(taoTiadapter);
            // 移动初始位置

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            params.height = (Helper_Phone.getScreenHeight(context)) / 2;
            top_content.setLayoutParams(params);

            // 图标手柄
            ImageView handle = (ImageView) view.findViewById(R.id.handle);
            handle.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            break;
                        case MotionEvent.ACTION_MOVE:
                            if (Math.abs(event.getRawY()) > 300) {
                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                        RelativeLayout.LayoutParams.MATCH_PARENT,
                                        RelativeLayout.LayoutParams.MATCH_PARENT);
                                params.height = (int) Math.abs(event.getRawY());
                                top_content.setLayoutParams(params);
                            }
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            });

        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}
