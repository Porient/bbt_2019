package com.bbt.back.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/12/12 0012 22:01
 */
public class DeduplicationUtil {
    public static List<Object> deduplication(List<Object> list){
        List<Object> res=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            if(!res.contains(list.get(i))){
                res.add(list.get(i));
            }
        }
        return res;
    }
}
