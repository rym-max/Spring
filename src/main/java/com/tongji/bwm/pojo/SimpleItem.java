package com.tongji.bwm.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.tongji.bwm.entity.ERMS.All;
import com.tongji.bwm.utils.HtmlUtils;
import lombok.Data;
import org.apache.commons.text.StringEscapeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author starcloud
 * @date 2019/11/17
 **/
@Data
public class SimpleItem {

    @JSONField
    private String id;

    @JSONField
    private String title;
    @JSONField
    private String url;
    @JSONField
    private String creator;
    @JSONField
    private String source;

    @JSONField
    private String description;
    @JSONField
    private String simpleDes;
    @JSONField
    private String smallDes;

    @JSONField
    private String date;
    @JSONField
    private String publishDate;
    @JSONField
    private String dateYear;
    @JSONField
    private String dateMonth;



    public static SimpleItem getInstance(All all){

        SimpleItem item = new SimpleItem();
        item.setTitle(all.GetValue("dc.title"));
        item.setId(all.getId());


        item.setCreator(all.GetValue("dc.creator"," ",""));
        item.setSource(all.GetValue("dc.source"));
        item.setUrl(all.GetValue("dc.url"));

        String dateStr = all.GetValue("dc.date.issued");
        item.setDate(dateStr);
        item.setPublishDate(dateStr);
        if(item.getDate().length()==10){
            item.setDateYear(dateStr.substring(0,4));
            item.setDateMonth(dateStr.substring(5,7));
        }




        String description = all.GetValue("dc.description");
        String xmldesc = StringEscapeUtils.unescapeXml(description);
        item.setDescription(xmldesc);//这个需要转码
        //去除tag并取前300个长度的字符
        if(HtmlUtils.removeTag(xmldesc).length()>300){
            item.setSimpleDes(HtmlUtils.removeTag(xmldesc).substring(0,300));
        }else{
            item.setSimpleDes(HtmlUtils.removeTag(xmldesc));
        }

        if(HtmlUtils.removeTag(xmldesc).length()>50){
            item.setSmallDes(HtmlUtils.removeTag(xmldesc).substring(0,50));
        }else{
            item.setSimpleDes(HtmlUtils.removeTag(xmldesc));
        }

        return item;
    }

    public static Map<String,SimpleItem> getInstance(Pagination<All> pagination){
        Map<String,SimpleItem> map = new HashMap<>();
        int i =0;

        List<All> list = pagination.getList();

        for(All all : list){
            i++;
            map.put(i+"a",getInstance(all));
        }


        return map;
    }


    public static Pagination<SimpleItem> getPagination(Pagination<All> pagination){
        List<SimpleItem> list = new ArrayList<>();
        pagination.getList().forEach(
                s -> {
                    list.add(getInstance(s));
                }
        );

        Pagination<SimpleItem> pagination1 = new Pagination<SimpleItem>(
                pagination.getPage(),
                pagination.getRows(),
                pagination.getTotal(),
                pagination.getPageCount(),
                list
        );
        return pagination1;
    }
}
