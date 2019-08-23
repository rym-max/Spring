package service;

import com.alibaba.fastjson.JSONObject;
import com.tongji.bwm.Application;
import com.tongji.bwm.entity.ERMS.Item;
import com.tongji.bwm.pojo.Pagination;
import com.tongji.bwm.service.ERMS.ItemService;
import com.tongji.bwm.solr.Client.Models.SearchResult;
import com.tongji.bwm.solr.Client.SolrConfig;
import com.tongji.bwm.solr.Client.SolrConnection;
import com.tongji.bwm.solr.Client.SolrIndex;
import com.tongji.bwm.utils.FilterEntityUtils;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Slf4j
@SpringBootTest(classes = {Application.class})
public class TestSolr extends AbstractTestNGSpringContextTests {

    @Autowired
    private SolrIndex solrIndex;

    @Autowired
    private SolrConnection solrConnection;

    @Autowired
    private ItemService itemService;

    @Autowired
    private SolrConfig solrConfig;

    @Test
    public void TestSolrIndex(){
        //1
        solrIndex.initTaskInfo();
        //2
        Future<String> result = solrIndex.startCreateIndex();
        //3
        try{
            result.get();
        }catch (Exception e){
            log.warn("获取结果出错了+\r\n" +
                    e.getMessage());
        }
    }

    @Test
    public void TestSolrPost(){
        log.info("先看一眼solr地址===="+solrConfig.getUrl());
        Pagination<Item> pageList = itemService.GetPageList(FilterEntityUtils.getPageRowCondition(0,2));
        for(Item i: pageList.getList()){
            log.info("Item------");
            log.info(i.getId());
            log.info(i.get_field().get("dc.title")[0]);
        }
        Item[] items = pageList.getList().toArray(new Item[pageList.getList().size()]);
        try {
            itemService.InsertToSolr(solrConfig.getUrl(),items,true);
            Thread.sleep(10000);
        }catch (DocumentException e){
            //
            log.warn("解析出错");
        }catch (InterruptedException e){
            //
            log.warn("线程出错");
        }

    }

    @Test
    public void TestSelect(){

        List<Pair<String,String>> parameters = itemService.GetParameters("","",0,0,1,1,10);

        Future<String> future = solrConnection.Get(solrConfig.getUrl(),"/select",parameters);
        try {
            String parseStr = future.get();
            JSONObject jsonObject = JSONObject.parseObject(parseStr);
            SearchResult<String> searchResult = itemService.GetSearchResult(parseStr);
            List<Item> items = new ArrayList<>();
            for(com.tongji.bwm.solr.Client.Models.Document<String> doc:searchResult.getDocs()){
                log.info("Item------id------"+doc.getId());
            }
        }catch (Exception e){
            log.warn("反正就是错了");
        }


    }

}
