package com.tongji.bwm.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author starcloud
 * @date 2019/11/17
 **/
public class HtmlUtils {
    static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // script
    static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // style
    static final String regEx_html = "<[^>]+>"; // HTML tag
    static final String regEx_space = "\\s+|\t|\r|\n";// other characters

    public static String removeTag(String htmlStr){


        Pattern p_script = Pattern.compile(regEx_script,
                Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll("");

        Pattern p_style = Pattern
                .compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll("");

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll("");

        Pattern p_space = Pattern
                .compile(regEx_space, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll(" ");

        return htmlStr;
    }
}
