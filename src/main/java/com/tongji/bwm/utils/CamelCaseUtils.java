package com.tongji.bwm.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CamelCaseUtils {

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    public static String lineToHump(String str){
        //str = str.toLowerCase();

        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb,matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String UpperFirstLetter(String str){
        if(str==null || str.isEmpty())
            return str;
        if(str.length()==1)
            return str.toUpperCase();
        return str.substring(0,1).toUpperCase()+str.substring(1);
    }

    public static String LowerFirstLetter(String str){
        if(str==null || str.isEmpty())
            return str;
        if(str.length()==1)
            return str.toLowerCase();
        return str.substring(0,1).toLowerCase()+str.substring(1);
    }

    public static String lineToHumpAndFirstToUpperCase(String str){
        String camelLowerCase = lineToHump(str);

        return UpperFirstLetter(camelLowerCase);
    }
}
