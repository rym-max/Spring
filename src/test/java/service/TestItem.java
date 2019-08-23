package service;

import com.tongji.bwm.Application;
import com.tongji.bwm.entity.ERMS.Item;
import com.tongji.bwm.service.ERMS.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.*;

@Slf4j
@SpringBootTest(classes = {Application.class})
public class TestItem extends AbstractTransactionalTestNGSpringContextTests {
    
    @Autowired
    private ItemService itemService;
    
    
    @Test
    public void TestItemField(){
        Item item = itemService.GetById("C6CCCA14-EE38-4A28-8993-0001CF4089D0");
        
        String metadataValue = item.getMetadataValue();
        
        HashMap<String,String[]> _field = new HashMap<>();

        
        if(metadataValue!=null && metadataValue.length()!=0 && _field.isEmpty()){
            //解析XML
//            SAXReader reader = new SAXReader();
//            Document document = reader
            try {
                Document document = DocumentHelper.parseText(metadataValue);
                Element rootElement = document.getRootElement();

                Iterator<Element> iterator = rootElement.elementIterator("field");

                while(iterator.hasNext()){
                    Element recordEle = iterator.next();

                    //读取attribute name
                    String name = recordEle.attributeValue("name");
                    log.info("看一下字段------"+name);
                    log.info("看一下结果------"+recordEle.getText());
                    List<String> values = new ArrayList<String>();
                    if(_field.containsKey(name)){
                        values = new ArrayList<>(Arrays.asList(_field.get(name)));
                        values.add(recordEle.getText());
                        _field.remove(name);
                    }else{
                        values.add(recordEle.getText());
                    }
                    _field.put(name,values.toArray(new String[values.size()]));
                }
            }catch (DocumentException e){
                //无法解析
                metadataValue = null;
            }
        }


        for(String key:_field.keySet()){
            log.info("元数据列表----------------"+key);
        }
    }
}
