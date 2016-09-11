package com.example.textreadbooknew.model.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.textreadbooknew.R;
import com.example.textreadbooknew.ReadPage;
import com.example.textreadbooknew.model.TaotiChildModel;
import com.example.textreadbooknew.tool.read.ReadBookPageTool;
import com.example.textreadbooknew.tool.webviewtool.ReadPageWebViewClient;
import com.example.textreadbooknew.tool.webviewtool.WebViewJavaScript;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/8/22.
 * 套题选项adapter
 */
public class TaoTiAdapter extends PagerAdapter {
    private Activity context;
    private LayoutInflater layoutInflater;
    private List<TaotiChildModel> datas=new ArrayList<>(0);
    private ViewPager taoti_viewpager;
    private String bookid;
    private String backgroundColor = "#182432";
    private String newbackgroundColor = "#FFFFFF";// #CBE4CF
    private String fontColor = "#FFFFFF";// 夜间模式时，字体颜色设置为白色
    private WebViewJavaScript webViewJavaScript;
    private ReadPageWebViewClient readPageWebViewClient;

    public TaoTiAdapter( Activity context,List<TaotiChildModel> datas,String bookid, ViewPager taoti_viewpager) {
        this.context=context;
        this.layoutInflater=context.getLayoutInflater();
        this.bookid=bookid;
        this.taoti_viewpager = taoti_viewpager;
        this.datas=datas;
    }

    @Override
    public int getCount() {
        return datas.size();
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
        View view = layoutInflater.inflate(R.layout.test_taoti_item,null);
//        try {
//            TaotiChildModel modle = datas.get(position);
//
//            RelativeLayout top_content = (RelativeLayout) view .findViewById(R.id.top_content);
//            RelativeLayout f_taoti_rl = (RelativeLayout) view .findViewById(R.id.f_taoti_rl);
//            final RelativeLayout sub_conten = (RelativeLayout) view .findViewById(R.id.sub_conten);// 提交区域
//
//            if (ReadPage.isNightModel) {
//                top_content.setBackgroundColor(Color.parseColor(backgroundColor));
//                sub_conten.setBackgroundColor(Color.parseColor(backgroundColor));
//            }
//            // 上一题
//            TextView tv1 = (TextView) view.findViewById(R.id.tv1);
//            tv1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (taoti_viewpager.getCurrentItem() - 1 >= 0)
//                        taoti_viewpager.setCurrentItem(taoti_viewpager .getCurrentItem() - 1);
//                }
//            });
//            // 下一题
//            TextView tv2 = (TextView) view.findViewById(R.id.tv2);
//            tv2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (taoti_viewpager.getCurrentItem() + 1 < datas
//                            .size())
//                        taoti_viewpager.setCurrentItem(taoti_viewpager .getCurrentItem() + 1);
//                }
//            });
//            // 题目类型
//            TextView content_type = (TextView) view.findViewById(R.id.content_type);
//            content_type.setText(modle.subjectType);
//            // 当前第几题
//            TextView now_page = (TextView) view.findViewById(R.id.now_page);
//            now_page.setText(position+1+"");
//            // 总共多少题
//            TextView all_title = (TextView) view .findViewById(R.id.all_title);
//            all_title.setText(datas.size()+"");
//            // 文章名称和标题区域
//            WebView subject_up = (WebView) view .findViewById(R.id.subject_up);
//            final LinearLayout subject_check_box = (LinearLayout) view
//                    .findViewById(R.id.subject_check_box);// 选项区域
//
//            // String web_content =
//            // "<div><div style=\"font-weight:bold\">" + "[" +
//            // modle.subjectType + "]" + "</div>";
//            String web_content = modle.subjectContent;
//            // appTools.sysLogMessage("套题内容web", web_content);
//            web_content = ReadBookPageTool.changeImgType(web_content);
//            web_content = ReadBookPageTool.changeImgUrl(web_content,bookid);
//            if (ReadPage.isNightModel) {
//                web_content = "<body style=\"color:" + fontColor+ ";\"> " + web_content + "</body>";
//                subject_up.setBackgroundColor(Color.parseColor(backgroundColor));
//                subject_check_box.setBackgroundColor(Color.parseColor(backgroundColor));
//                f_taoti_rl.setBackgroundColor(Color.parseColor(backgroundColor));
//            }
//
//            // appTools.sysLogMessage("套题题内容web替换", web_content);
//            subject_up.getSettings().setLayoutAlgorithm( WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//            subject_up.getSettings().setJavaScriptEnabled(true);
//            webViewJavaScript = new WebViewJavaScript(context);
//            subject_up.addJavascriptInterface(webViewJavaScript, "toolbox");
//            readPageWebViewClient = new ReadPageWebViewClient(subject_up);
//            subject_up.setWebViewClient(readPageWebViewClient);
//            subject_up.loadDataWithBaseURL(null, web_content, "text/html", "utf-8", null);
//            // 选项区域
//
//            TextView sub_btn = (TextView) view .findViewById(R.id.sub_btn);// 提交按钮
//            TextView sub_text_btn = (TextView) view.findViewById(R.id.sub_text_btn);// 提交字符串
//            final RelativeLayout down_content = (RelativeLayout) view .findViewById(R.id.down_content);// 答案区域
//            RelativeLayout item_1 = (RelativeLayout) view .findViewById(R.id.item_1);// 我的答案区域
//            final TextView text_1_1_1 = (TextView) view .findViewById(R.id.text_1_1_1);// 我的答案
//
//            // 判断选项是否为空
//            if ("[]".equals(modle.subjectOpation)) {
//                subject_check_box.setVisibility(View.GONE);
//                sub_btn.setText("直接查看答案");
//                sub_text_btn.setVisibility(View.GONE);
//                item_1.setVisibility(View.GONE);
//            } else {
//                final String answer = modle.subjectAnswer;
//                final ArrayList<String> selete_map = new ArrayList<>();
//                JSONArray array_json = new JSONArray(modle.subjectOpation);
//                for (int i = 0; i < array_json.length(); i++) {
//                    String object = array_json.get(i).toString();
//                    final CheckBox btn = new CheckBox(context);
//                    String btn_text = object.toString().replaceAll( "<br/>", "");
//                    btn_text=ReadBookPageTool.changeImgUrlNoFileHead(btn_text,bookid);
//                    btn_text=ReadBookPageTool.changeImgType(btn_text);
//                    item_1.setBackgroundColor(Color.parseColor(newbackgroundColor));
//                    if (ReadPage.isNightModel) {
//                        item_1.setBackgroundColor(Color .parseColor(backgroundColor));
//                        btn.setTextColor(Color.parseColor(fontColor));
//                    }
//                    btn.setText(Html.fromHtml(btn_text,
//                            new Html.ImageGetter() {
//                                @Override
//                                public Drawable getDrawable( String source) {
//                                    Drawable drawable = new BitmapDrawable();
//                                    try {
//                                        drawable = Drawable.createFromPath(source); // 获取网路图片
//                                        if (drawable.getIntrinsicHeight() < 40) {// 处理按钮图片，不确定是否内存溢出
//                                            drawable.setBounds(0,0,drawable.getIntrinsicWidth() * 4,drawable.getIntrinsicHeight() * 4);
//                                        } else {
//                                            drawable.setBounds(0, 0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
//                                        }
//                                    } catch (Exception e) {
//                                        return null;
//                                    }
//                                    return drawable;
//                                }
//                            }, null));
//                    btn.setButtonDrawable(R.drawable.check_box_type);
//                    btn.setGravity(Gravity.BOTTOM);
//                    btn.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            if (btn.isChecked()) {
//                                selete_map.add(btn.getText().toString().substring(0, 1));
//                                // btn.setChecked(false);
//                            } else {
//                                selete_map.remove(btn.getText() .toString().substring(0, 1));
//                                // btn.setChecked(true);
//                            }
//                            StringBuffer sb = new StringBuffer();
//                            Iterator it = selete_map.iterator();
//                            while (it.hasNext()) {
//                                sb.append(it.next());
//                            }
//                            if (answer.equals(sb.toString())) {
//                                text_1_1_1.setText(sb.toString()
//                                        + "（√）");
//                            } else {
//                                text_1_1_1.setText(sb.toString()
//                                        + "（×）");
//                            }
//                        }
//                    });
//                    android.widget.RadioGroup.LayoutParams params = new android.widget.RadioGroup.LayoutParams(
//                            android.widget.RadioGroup.LayoutParams.MATCH_PARENT,
//                            android.widget.RadioGroup.LayoutParams.WRAP_CONTENT);
//                    params.setMargins(0, 20, 0, 0);
//                    btn.setPadding(15, 0, 0, 0);
//                    subject_check_box.addView(btn, params);
//                }
//            }
//            // 提交按钮
//            sub_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    down_content.setVisibility(View.VISIBLE);
//                    sub_conten.setVisibility(View.GONE);
//                    for (int i = 0; i < subject_check_box.getChildCount(); i++) {
//                        CheckBox cb = (CheckBox) subject_check_box .getChildAt(i);
//                        cb.setEnabled(false);
//                    }
//                }
//            });
//            sub_text_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    down_content.setVisibility(View.VISIBLE);
//                    sub_conten.setVisibility(View.GONE);
//                    for (int i = 0; i < subject_check_box.getChildCount(); i++) {
//                        CheckBox cb = (CheckBox) subject_check_box.getChildAt(i);
//                        cb.setEnabled(false);
//                    }
//                }
//            });
//            // 参考答案
//            WebView text_2_1_1 = (WebView) view .findViewById(R.id.text_2_1_1);
//            text_2_1_1.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//            text_2_1_1.getSettings().setJavaScriptEnabled(true);
//            webViewJavaScript = new WebViewJavaScript(context);
//            text_2_1_1.addJavascriptInterface(webViewJavaScript, "toolbox");
//            readPageWebViewClient = new ReadPageWebViewClient(text_2_1_1);
//            text_2_1_1.setWebViewClient(readPageWebViewClient);
//            RelativeLayout item_2 = (RelativeLayout) view.findViewById(R.id.item_2);
//            if (TextUtils.isEmpty(modle.subjectAnswer)) {
//                item_2.setVisibility(View.GONE);
//            } else {
//                String text_2_1_1_body = modle.subjectAnswer;
//                text_2_1_1_body=ReadBookPageTool.changeImgUrl(text_2_1_1_body,bookid);
//                text_2_1_1.setBackgroundColor(Color.parseColor(newbackgroundColor));
//                item_2.setBackgroundColor(Color.parseColor(newbackgroundColor));
//                if (ReadPage.isNightModel) {
//                    text_2_1_1.setBackgroundColor(Color .parseColor(backgroundColor));
//                    item_2.setBackgroundColor(Color .parseColor(backgroundColor));
//                    text_2_1_1_body = "<body style=\"color:" + fontColor + ";\"> " + text_2_1_1_body + "</body>";
//                }
//                text_2_1_1_body=ReadBookPageTool.changeImgType(text_2_1_1_body);
//                text_2_1_1.loadDataWithBaseURL(null, text_2_1_1_body,"text/html", "utf-8", null);
//            }
//            // 点播
//            WebView text_3_2 = (WebView) view.findViewById(R.id.text_3_2);
//            text_3_2.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//            text_3_2.getSettings().setJavaScriptEnabled(true);
//            webViewJavaScript = new WebViewJavaScript(context);
//            text_3_2.addJavascriptInterface(webViewJavaScript, "toolbox");
//            readPageWebViewClient = new ReadPageWebViewClient(text_3_2);
//            text_3_2.setWebViewClient(readPageWebViewClient);
//            RelativeLayout item_3 = (RelativeLayout) view .findViewById(R.id.item_3);
//            if (TextUtils.isEmpty(modle.subjectDirect)) {
//                item_3.setVisibility(View.GONE);
//            } else {
//                String text_3_2_body = modle.subjectDirect;
//                text_3_2_body=ReadBookPageTool.changeImgUrl(text_3_2_body,bookid);
//                text_3_2.setBackgroundColor(Color .parseColor(newbackgroundColor));
//                item_3.setBackgroundColor(Color .parseColor(newbackgroundColor));
//                if (ReadPage.isNightModel) {
//                    text_3_2.setBackgroundColor(Color .parseColor(backgroundColor));
//                    item_3.setBackgroundColor(Color.parseColor(backgroundColor));
//                    text_3_2_body = "<body style=\"color:" + fontColor + ";\"> " + text_3_2_body + "</body>";
//                }
//                text_3_2_body= ReadBookPageTool.changeImgType(text_3_2_body);
//                text_3_2.loadDataWithBaseURL(null, text_3_2_body,"text/html", "utf-8", null);
//            }
//            // 解析
//            WebView text_4_2 = (WebView) view.findViewById(R.id.text_4_2);
//            text_4_2.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//            text_4_2.getSettings().setJavaScriptEnabled(true);
//            webViewJavaScript = new WebViewJavaScript(context);
//            text_4_2.addJavascriptInterface(webViewJavaScript, "toolbox");
//            readPageWebViewClient = new ReadPageWebViewClient(text_4_2);
//            text_4_2.setWebViewClient(readPageWebViewClient);
//            RelativeLayout item_4 = (RelativeLayout) view
//                    .findViewById(R.id.item_4);
//            if (TextUtils.isEmpty(modle.subjectAnalysis)) {
//                item_4.setVisibility(View.GONE);
//            } else {
//                String text_4_body = modle.subjectAnalysis;
//                text_4_body=ReadBookPageTool.changeImgUrl(text_4_body,bookid);
//                text_4_2.setBackgroundColor(Color .parseColor(newbackgroundColor));
//                item_4.setBackgroundColor(Color .parseColor(newbackgroundColor));
//                if (ReadPage.isNightModel) {
//                    text_4_2.setBackgroundColor(Color.parseColor(backgroundColor));
//                    item_4.setBackgroundColor(Color .parseColor(backgroundColor));
//                    text_4_body = "<body style=\"color:" + fontColor+ ";\"> " + text_4_body + "</body>";
//                }
//                text_4_body=ReadBookPageTool.changeImgType(text_4_body);
//                text_4_2.loadDataWithBaseURL(null, text_4_body,"text/html", "utf-8", null);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        container.addView(view);
        return view;
    }

}
