package com.zkk.test.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final String dateFormatPattern = "yyyy-MM-dd HH:mm:ss";

    public static String dateUtil(Long milliseconds){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatPattern);
        String time = simpleDateFormat.format(milliseconds);
        return time;
    }
}
