package com.bbt.back.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @Author: 潘浩霖
 * @Date: 2019/2/23
 */
public class DateFormatUtil {
    public static String format(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
