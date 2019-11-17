package com.tongji.bwm.pojo;

import com.tongji.bwm.entity.ERMS.All;
import com.tongji.bwm.utils.HtmlUtils;
import lombok.Data;
import org.apache.commons.text.StringEscapeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author starcloud
 * @date 2019/11/17
 **/
@Data
public class SimpleItem {

    private String title;
    private String url;
    private String creator;
    private String source;

    private String description;
    private String simpleDes;
    private String smallDes;

    private String date;
    private String publishDate;

    public static SimpleItem getInstance(All all){
        SimpleItem item = new SimpleItem();
        item.setTitle(all.GetValue("dc.title"));

        item.setCreator(all.GetValue("dc.creator"," ",""));
        item.setSource(all.GetValue("dc.source"));
        item.setUrl(all.GetValue("dc.url"));
        item.setDate(all.GetValue("dc.date.issued"));
        item.setPublishDate(all.GetValue("dc.date.issued"));

        String description = all.GetValue("dc.description");
        String xmldesc = StringEscapeUtils.unescapeXml(description);
        item.setDescription(xmldesc);//这个需要转码
        //去除tag并取前300个长度的字符
        item.setSimpleDes(HtmlUtils.removeTag(xmldesc).substring(0,300));
        item.setSmallDes(HtmlUtils.removeTag(xmldesc).substring(0,50));
        return item;
    }

    public static Pagination<SimpleItem> getInstance(Pagination<All> pagination){
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
