package com.origincat.oj.utils;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CreateRandomID {
    public static String getRandomID(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date= new Date();
        String string = simpleDateFormat.format(date);
        Random random = new Random();
        int randomNum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
        return randomNum + string;
    }
}