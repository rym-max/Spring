package service;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.tongji.bwm.Application;
//import com.tongji.bwm.entity.ERMS.Item;
//import com.tongji.bwm.pojo.Enum.CommonEnum;
//import com.tongji.bwm.pojo.Pagination;
//import com.tongji.bwm.solr.Models.Document;
//import com.tongji.bwm.solr.Models.SearchResult;
//import com.tongji.bwm.solr.Client.SolrConfig;
//import com.tongji.bwm.solr.Client.SolrConnection;
//import javafx.util.Pair;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.testng.annotations.Test;
//import static org.testng.Assert.*;
//
//import java.util.*;
//import java.util.concurrent.Future;
//
//@Slf4j
//@SpringBootTest(classes = {Application.class})
public class TestSpiderItemService extends AbstractTestNGSpringContextTests {
//
//    @Autowired
//    private ItemService itemService;
//
//    @Autowired
//    private SolrConfig solrConfig;
//
//    @Autowired
//    private SolrConnection solrConnection;
//
//    @Autowired
//    private ItemRepository itemRepository;
//
//    @Test
//    public void TestGetparameter() {
//        List<Pair<String, String>> params = itemService.GetParameters("", "", 0, 0, 1, 30, 10);
//        for (Pair<String, String> p : params) {
//            log.info(p.getKey() + "---------------" + p.getValue());
//        }
//        //那么问题出在了哪
//        //根据参数查询
//        Future<String> ff = solrConnection.Get(solrConfig.getUrl(), "/select", params);
//
//        SearchResult<String> searchResult = null;
//        JSONObject jsonObject = null;
//        try {
//
//            String ss = ff.get();
//            log.info(ss);
//
//            jsonObject = JSONObject.parseObject(ss);
//            searchResult = JSON.parseObject(jsonObject.getJSONObject("response").toJSONString(), SearchResult.class);
//
//        } catch (Exception e) {
//            log.warn("查询出错");
//        }
//        assertNotNull(searchResult);
//        //看一眼searchResult
//        log.info(searchResult.toString());
//        //参数信息在各个地方，需要分别设置，不能一次性
//
//
//        for (Document<String> doc : searchResult.getDocs()) {
//            log.info("查询的ID--------" + doc.getId());
//        }
//
//        //查一下Item
//        List<Item> items = new ArrayList<>();
//        for (Document<String> doc : searchResult.getDocs()) {
//            Item item = itemService.GetById(doc.getId());
//            if (item != null) {
//                items.add(item);
//                log.info(item.GetValue("dc.title"));
//            }
//        }
//        //看眼items
//
//
//        //下一步参数是否能查
////        for(Pair<String,String> param: params){
////            if(param.getKey().equals("start"))
////                searchResult.setStart(Integer.parseInt(param.getValue()));
////            if(param.getKey().equals("rows"))
////                searchResult.setStart(Integer.parseInt(param.getValue()));
////        }
////
////        int page = (searchResult.getStart() +searchResult.getRows())/searchResult.getRows();
////        Pagination<SpiderItem> pp= new Pagination<SpiderItem>(items,searchResult.getNumFound(),page,searchResult.getRows());
////        assertNotNull(pp);
//    }
//
//    @Test
//    public void TestItemService() {
//        List<Pair<String, String>> param = itemService.GetParameters("", "", 0, 0, 1, 1, 10);
//        for (Pair<String, String> p : param) {
//            log.info(p.getKey() + "---------------" + p.getValue());
//        }
//
//        Pagination<Item> pageList = null;
//        try {
//            pageList = itemService.GetPageList(solrConfig.getUrl(), param);
//
//        } catch (Exception e) {
//            log.warn("查询出错");
//        }
//
////        assertNotNull(pageList);
//        assertNotNull(pageList);
//        assertNotNull(pageList.getList());
//        assertNotEquals(pageList.getList().size(), 0);
//        for (Item item : pageList.getList()) {
//            log.info("查询的Item信息------" + item.GetValue("dc.title"));
//        }
//    }
//
//    @Test
//    public void TestGetById() {
//        Item item = itemService.GetById("C6CCCA14-EE38-4A28-8993-0001CF4089D0");
//        assertNotNull(item);
//        log.info("查询的Item的Id-----------" + item.getId());
//        log.info("查询的Item信息-------------" + item.getMetadataValue());
//        for (String set : item.get_field().keySet()) {
//            log.info("元数据列表===========" + set);
//        }
//
//    }
//
//
//
//    @Test
//    public void TestCount(){
//        Long count = itemService.Count(null);
//        log.info("总文献数量："+count);
//
////        Long countspider = spiderItemRepository.count();
////        log.info("总爬虫数量："+countspider);
//
//        Long count1 = itemRepository.countAll();
//        log.info("总文献数量："+count1);
//
//        Long count2 = itemRepository.countByStatus(CommonEnum.AuditStatusEnum.Pass);
//        log.info("通过文献数量"+count2);
//
//
//
//    }
}