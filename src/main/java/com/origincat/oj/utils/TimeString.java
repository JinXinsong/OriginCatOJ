package com.origincat.oj.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeString {
    public static String getTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssS");
        Date date= new Date();
        return simpleDateFormat.format(date);
    }
}