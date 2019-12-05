package service;

import com.alibaba.fastjson.JSONObject;
import com.tongji.bwm.Application;
import com.tongji.bwm.entity.ERMS.All;
import com.tongji.bwm.pojo.Pagination;
import com.tongji.bwm.service.ERMS.AllService;
import com.tongji.bwm.solr.Models.Document;
import com.tongji.bwm.solr.Models.SearchResult;
import com.tongji.bwm.solr.Client.SolrConfig;
import com.tongji.bwm.solr.Client.SolrConnection;
import com.tongji.bwm.solr.Client.SolrIndex;
import com.tongji.bwm.solr.Models.TaskTypeEnum;
import com.tongji.bwm.utils.FilterEntityUtils;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private AllService allService;

    @Value("${solrserver.core.tongjieu}")
    private String solrUrl;

    @Test
    public void TestSolrIndex(){
        //1
        solrIndex.initTaskInfo(TaskTypeEnum.ADD);
        //2
        Future<String> result = solrIndex.startCreateIndex();
        //3
        try{
            result.get();
        }catch (Exception e){
            log.warn("获取结果出错了+\r" +
                    e.getMessage());
        }
    }

    @Test
    public void TestSolrPost(){
        log.info("先看一眼solr地址===="+solrUrl);
        Pagination<All> pageList = allService.GetPageList(FilterEntityUtils.getPageRowCondition(0,2));
        for(All i: pageList.getList()){
            log.info("SpiderItem------");
            log.info(i.getId());
            log.info(i.get_field().get("dc.title")[0]);
        }
        All[] items = pageList.getList().toArray(new All[pageList.getList().size()]);
        try {
            Future<String> future = allService.InsertToSolr(items,true);
            String result = future.get();
            log.info("结果来着\r\n"+result);
        }catch (DocumentException e){
            //
            log.warn("解析出错");
        }catch (Exception e){
            log.warn("访问出错");
            log.warn(e.getMessage());
        }

    }

    @Test
    public void TestSelect(){

        List<Pair<String,String>> parameters = allService.GetParameters("","",0,0,1,1,10);

        Future<String> future = solrConnection.Get(solrUrl,"/select",parameters);
        try {
            String parseStr = future.get();
            JSONObject jsonObject = JSONObject.parseObject(parseStr);
            SearchResult<String> searchResult = allService.GetSearchResult(parseStr);
            List<All> items = new ArrayList<>();
            for(Document<String> doc:searchResult.getDocs()){
                log.info("SpiderItem------id------"+doc.getId());
            }
        }catch (Exception e){
            log.warn("反正就是错了");
        }
    }

    @Test
    public void TestPost(){
        Future<String> result = solrConnection.Post(solrUrl,"/update",
                "<add commitWithin=\"1000\" overwrite=\"true\">" +
                        "<doc>"+
                        "<field name=\"id\">C8CCCA14-EE38-4A28-8993-0001CF4089D0</field>" +
                        "<field name=\"channel\">1</field>" +
                        "<field name=\"category\">4</field>" +
                        "<field name=\"click\">0</field>" +
                        "<field name=\"sort\">0</field>" +
                        "<field name=\"status\">1</field>" +
                        "<field name=\"ctime\">1568184951</field>" +
                        "<field name=\"mtime\">1568184951</field>" +
                        "<field name=\"dc.title\">Fragile Balance</field>" +
                        "<field name=\"dc.date.issued\">2006-11-01</field>"+
                        "<field name=\"dc.description\">没有哦</field>"+
                        "<field name=\"dc.source\">THINK TANK</field>" +
                        "<field name=\"dc.creator\">Xiangming</field>" +
                        "<field name=\"dc.subject\">China</field>" +
                        "<field name=\"dc.subject\">Deutschland</field>" +
                        "<field name=\"dc.subject\">chinesisch</field>" +
                        "<field name=\"dc.url\">https://zeitschrift-ip.dgap.org/de/ip-die-zeitschrift/archiv/jahrgang-2006/november/fragile-balance</field>" +
                        "</doc>"+
                        "<commit></commit>" +
                    "</add>"
                );
        try {
            String mm = result.get();
            log.info(mm);
        }catch (Exception e){
            log.info("出错了哦\r\n"+e.getMessage());
        }
    }


    @Test
    public void DeleteSolr(){
        List<String> ids = new ArrayList<>();

        ids.add("C8CCCA14-EE38-4A28-8993-0001CF4089D0");
        ids.add("bd129496-0a13-4e1a-b0b7-00035f404d8b");

        Future<String> future = allService.DeleteToSolr(ids,true);
        try{
            String result = future.get();
            log.info(result);
        }catch (Exception e){
            log.info("访问出错！");
            log.info(e.getMessage());
        }
    }

    @Test
    public void TestCreateIndex(){
        solrIndex.initTaskInfo(TaskTypeEnum.ADD);
        Future<String> future = solrIndex.startCreateIndex();
        log.info("get前");
        try {
            String result = future.get();
            log.info("get后");
            log.info(result);
        }catch (Exception e){
            log.warn("出错了");
            log.warn(e.getMessage());
        }
    }

    @Test
    public void TestFreshIndex(){
        solrIndex.initTaskInfo(TaskTypeEnum.ADD);
        Future<String> future = solrIndex.startFreshIndex();
        log.info("get前");
        try {
            String result = future.get();
            log.info("get后");
            log.info(result);
        }catch (Exception e){
            log.warn("出错了");
            log.warn(e.getMessage());
        }
    }

    @Test
    public void TestSolrPageList(){
        Integer page = 83;
        Integer row = 500;
        Integer pageCount = 0;
        Integer totalnumber= 0;
        while (true){
            Pagination<All> pagination = allService.GetPageList(FilterEntityUtils.getPageRowCondition(page,row));
            List<All> list = pagination.getList();

            log.info("现在页数：" + page);
            if(list.size()==0) {
                break;
            }
            if(list.size()!=500){
                log.info("最后or问题："+list.size());
            }
            page++;
            if(pageCount==0||totalnumber==0){
                pageCount = pagination.getPageCount();
                totalnumber = pagination.getTotal();
                log.info("总页数："+pageCount);
                log.info("总数量"+totalnumber);
            }
        }
        log.info("总页数"+pageCount);
        log.info("结束来着");
    }


    @Test
    public void TestDeleteAll(){
        Future<String> response = allService.DeleteAllToSolr(true);
        log.info("返回响应");
        String result = "";
        try {
            result = response.get();
        }catch (Exception e){
            log.error(e.getCause().toString());
            log.error(e.getMessage());
            log.warn("出错了");
        }
        log.info(result);

    }

    @Test
    public void TestPaginationList(){
        int a = 0;
        log.info("每隔25次打印一下");
        Pagination<All> pageList = allService.GetPageList(FilterEntityUtils.getPageRowCondition(0,500));
        for(All all: pageList.getList()){
            if(a%25 ==0){
                log.info("第"+a+"次哦！");
                log.info("看一眼id");
                log.info(all.getId());
            }
            a++;

        }
    }

    @Test
    public void TestInsert(){
        List<All> list = new ArrayList<>();
        All all1 = allService.GetById("AC081E77-39B7-40E1-8458-0A9689A5B4B2");
        All all2 = allService.GetById("EBD9CE03-D0BA-466B-B9BD-0A99C4573F2B");
        All all3 = allService.GetById("5626D952-99D8-442D-9CC4-0ADDC1A7A2C8");
        All all4 = allService.GetById("18B4F257-2C9E-4BD2-B9DF-0B279A3465FD");
        All all5 = allService.GetById("6D2B2CFF-17C3-4E27-B1DB-0B340B3D171B");
        All all6 = allService.GetById("337A5779-694F-477D-8386-0B644DFA948D");
        All all7 = allService.GetById("3AEB18CF-026C-4130-889F-0BF3F0EF84FE");

        list.add(all1);
        list.add(all2);
        list.add(all3);
        list.add(all4);
        list.add(all5);
        list.add(all6);
        list.add(all7);

        String result ="";
        try {
            Future<String> future = allService.InsertToSolr(list.toArray(new All[list.size()]), true);
            result = future.get();
        }catch (Exception e){
            log.error(e.getMessage());
            log.error("出错了");
        }

        log.info(result);
    }
}
