package com.example.textreadbooknew.tool;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2016/7/30.
 * io流工具
 */
public class Helper_IO {
    /**
     * 将InputStream转换成String
     */
    public static String showReturnMess(InputStream inputStream) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    inputStream));
            String str = "";
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    /**
     * @param filePath 文件路径
     * @return 字符串
     */
    public static String readFileContents(File filePath) {
        String all_read_contents = "";
        try {
            FileInputStream fis = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis,
                    "utf-8"));
            String read = null;
            while ((read = br.readLine()) != null) {
                all_read_contents += read;
            }
            br.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.v("MainActivity", "----read file----is error");
        }
        return all_read_contents;
    }
}
