package service;
//
//import com.tongji.bwm.Application;
//import com.tongji.bwm.entity.ERMS.Item;
//import com.tongji.bwm.solr.Client.SolrConfig;
//import lombok.extern.slf4j.Slf4j;
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.DocumentHelper;
//import org.dom4j.Element;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
//import org.testng.annotations.Test;
//
//import java.util.*;
//import java.util.concurrent.Future;
//
//@Slf4j
//@SpringBootTest(classes = {Application.class})
public class TestItem extends AbstractTransactionalTestNGSpringContextTests {
//
//    @Autowired
//    private ItemService itemService;
//
//    @Test
//    public void TestXML() throws DocumentException{
//        Item item = itemService.GetById("c6ccca14-ee38-4a28-8993-0001cf4089d0");
//
//        String MetadataValue = item.getMetadataValue();
//
//        Document document = DocumentHelper.parseText(MetadataValue);
//        Element root = document.getRootElement();
//
//        log.info("根节点信息");
//        log.info(root.asXML());
//
//        log.info("看一眼text");
//        log.info(root.getText());
//
////        log.info("看一眼下节点？");
////        log.info(root.element("doc").asXML());
//
//        log.info("maybe");
//        log.info(root.element("field").asXML());
//
//    }
//
//
//    @Test
//    public void TestItemField(){
//        Item item = itemService.GetById("C6CCCA14-EE38-4A28-8993-0001CF4089D0");
//
//        String metadataValue = item.getMetadataValue();
//
//        HashMap<String,String[]> _field = new HashMap<>();
//
//
//        if(metadataValue!=null && metadataValue.length()!=0 && _field.isEmpty()){
//            //解析XML
////            SAXReader reader = new SAXReader();
////            Document document = reader
//            try {
//                Document document = DocumentHelper.parseText(metadataValue);
//                Element rootElement = document.getRootElement();
//
//                Iterator<Element> iterator = rootElement.elementIterator("field");
//
//                while(iterator.hasNext()){
//                    Element recordEle = iterator.next();
//
//                    //读取attribute name
//                    String name = recordEle.attributeValue("name");
//                    log.info("看一下字段------"+name);
//                    log.info("看一下结果------"+recordEle.getText());
//                    List<String> values = new ArrayList<String>();
//                    if(_field.containsKey(name)){
//                        values = new ArrayList<>(Arrays.asList(_field.get(name)));
//                        values.add(recordEle.getText());
//                        _field.remove(name);
//                    }else{
//                        values.add(recordEle.getText());
//                    }
//                    _field.put(name,values.toArray(new String[values.size()]));
//                }
//            }catch (DocumentException e){
//                //无法解析
//                metadataValue = null;
//            }
//        }
//
//
//        for(String key:_field.keySet()){
//            log.info("元数据列表----------------"+key);
//        }
//    }
//
//
//    @Autowired
//    private SolrConfig solrConfig;
//
//
//    @Test
//    public void TestDelete(){
//        //主要测试delete功能
//        String id="f61fdc38-dd86-4915-99af-bef4fb7afef9";
//        String result=null;
//        itemService.Delete(id);
//        Future<String> future = itemService.DeleteToSolr(solrConfig.getUrl(),id,true);
//        try{
//            result = future.get();
//            log.info("结果\r\n"+result);
//        }catch (Exception e){
//            log.info("出问题了");
//        }
//    }
}
