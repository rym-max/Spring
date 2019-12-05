package com.tongji.bwm.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public static Date getPastDate(int past){
        Calendar current = Calendar.getInstance();
        int day = current.get(Calendar.DATE);
        current.set(Calendar.DATE,day-past+1);

        return current.getTime();
    }

    public static Integer getPeriod(Date start_date, Date end_date) {

        //设置转换的日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        //得到相差的天数 betweenDate
        long betweenDate = (end_date.getTime() - start_date.getTime())/(60*60*24*1000);

        return Math.toIntExact(betweenDate)+1;
    }
}
