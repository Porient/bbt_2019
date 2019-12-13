package com.bbt.back.utils;

import java.util.HashMap;

/**
 * @Description:
 * @Author: Liu Bin
 * @Date: 2019/12/13 0013 13:55
 */
public class MapUtil {
    public static HashMap<String, Integer> addKey(HashMap<String, Integer> map,String key){
        if(map.containsKey(key)){
            map.put(key,map.get(key)+1);
        }else {
            map.put(key,1);
        }
        return map;
    }
}
