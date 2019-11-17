package com.tongji.bwm.germany.service;

import com.tongji.bwm.germany.entity.Item;
import com.tongji.bwm.germany.repository.ItemRepository;
import com.tongji.bwm.solr.Client.SolrConnection;
import com.tongji.bwm.utils.XmlDocumentUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.Future;

/**
 * @author starcloud
 * @date 2019/09/24
 * @description
 *  中德metadata service 只有插入功能
 **/
@Slf4j
@Service
public class ItemService implements IItemService<String>{

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private SolrConnection solrConnection;

    @Setter
    @Value("${solrserver.core.tongji}")
    private String solrUrl;

    @Override
    public String Insert(Item item) {
        return itemRepository.save(item).getId();
    }

    @Override
    public void InsertToSolr(String serverURL, Item[] items, boolean commit) throws DocumentException {
        InsertSolr(serverURL,items,commit);
    }

    public void InsertToSolr(Item[] items, boolean commit) throws DocumentException{
        InsertToSolr(solrUrl,items,commit);
    }
    /**
     *注释用中文
     * version: 1.0
     * date: 19/11/11 11:14
     * author: hzxstarcloud
     * description:
     * @params
     [serverURL, items, commit]
     * @return java.util.concurrent.Future<java.lang.String>
    **/
    public Future<String> InsertSolr(String serverURL, Item[] items, boolean commit) throws DocumentException {
        //        log.info("进入future");
        StringBuffer xmlStringB = new StringBuffer();
        for(int i=0;i<items.length;i++){
            Item item = items[i];
//            Document document = DocumentHelper.parseText(item.getMetadataValue());
//            Element root = document.getRootElement();

            String metavalue = XmlDocumentUtils.getXmlStringWithoutRoot(item.getMetadataValue(),"doc");
//            log.info("metavalue前后");
            xmlStringB.append("<doc>")
                    .append("<field name=\"id\">" + item.getId() + "</field>")
                    .append("<field name=\"channel\">" + item.getChannelId() + "</field>")
                    .append("<field name=\"category\">" + item.getCategoryId() + "</field>")
                    .append("<field name=\"click\">" + item.getClick() + "</field>")
                    .append("<field name=\"sort\">" + item.getSort() + "</field>")
                    .append("<field name=\"status\">" + item.getStatus() + "</field>")
                    .append("<field name=\"ctime\">" + item.getCreateTime().getTime() + "</field>")
                    .append("<field name=\"mtime\">" + item.getModifyTime().getTime() + "</field>")
                    .append(metavalue)
                    .append("</doc>");
        }
        String xmlString =  "<add commitWithin=\"1000\" overwrite=\"true\">" +
                xmlStringB.toString() +
                (commit ? "<commit></commit>" : "") +
                "</add>";
//        log.info("post前");
        return solrConnection.Post(serverURL,"/update",xmlString);
    }

    /**
     *注释用中文
     * version: 1.0
     * date: 19/11/11 14:34
     * author: hzxstarcloud
     * description:
     * 主要是往中德智库类插入数据，先往数据库，失败返回false
     * 然后往中德solr插入数据，失败返回true，在数据库即可
     * @params
     [items]
     * @return boolean
    **/
    public boolean InsertToGermany(Item[] items){
        try{
            for(int i=0;i<items.length;i++){
                String id = Insert(items[i]);
                items[i].setId(id);
            }
        }catch (Exception e){
            log.error("插入中德数据库失败！");
            return false;
        }
        try{
            Future<String> response = InsertSolr(solrUrl,items,true);
            String result = response.get();
            //这里需要判断一下成功没有。
        }catch (DocumentException e){
            log.warn("插入中德solr出错，api请求xml文档错误");
            return true;//无伤大雅，进数据库就好
        }catch (Exception e){
            log.warn("插入中德solr出错，详细错误：\r\n"+e.toString());
            return true;//无伤大雅。进数据库就好
        }
        log.debug("成功插入["+items.length+"]条记录进入中德智库");
        return true;
    }

    public boolean InsertToGermany(Item item){
        return InsertToGermany(new Item[]{item});
    }
}
