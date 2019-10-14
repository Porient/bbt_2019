package com.bbt.back.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Kobe
 * @Date: 2019/10/14 20:10
 */
public class CutWord {
    public static List<String> cutWord(String words){
        words = words.replaceAll("[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]","");
        String result = "";
        try {
            Process process = Runtime.getRuntime().exec("python D:/IDEACode/Word/src/main/python/my_jieba.py " + words);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(),"GBK");
            LineNumberReader lineNumberReader = new LineNumberReader(inputStreamReader);
            result = lineNumberReader.readLine();
            inputStreamReader.close();
            lineNumberReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        result = result.substring(1,result.length()-1);
        System.out.println(result);
        result = result.replaceAll(" ","");
        String[] strings = result.split(",");
        List<String> list = new ArrayList<String>();
        for (String s : strings){
            list.add(s.substring(1,s.length()-1));
        }
        return list;
    }
}
