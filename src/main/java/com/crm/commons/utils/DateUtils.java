package com.crm.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 对data类型数据进行处理的类
 */
public class DateUtils {
    public static String formatDataTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        return dateStr;
    }
}
