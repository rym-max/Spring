package com.tongji.bwm.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatterUtils {

    public static String GetYear(String dateStr){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat forYear = new SimpleDateFormat("yyyy");
        String result = "";
        if(dateStr==null||dateStr.isEmpty())
            return result;
        if(dateStr.length()==4)
            return dateStr;
        try {
            Date date = simpleDateFormat.parse(dateStr);
            result = forYear.format(date);
        }catch(ParseException e){
            //啥事不干
        }
        return result;
    }

    public static String GetDateStr(Date date){
        SimpleDateFormat completeDate = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        return completeDate.format(date);
    }
}
