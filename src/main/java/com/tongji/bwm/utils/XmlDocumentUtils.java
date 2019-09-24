package com.tongji.bwm.utils;

public class XmlDocumentUtils {

    public static String getXmlStringWithoutRoot(String xmlString,String root){

        if(xmlString!=null && !xmlString.isEmpty()) {
            xmlString = xmlString.replaceAll("<" + root + ">", "");
            xmlString = xmlString.replaceAll("</" + root + ">", "");
            return xmlString;
        }else {
            return "";
        }
    }
}
