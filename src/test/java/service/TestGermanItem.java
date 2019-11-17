package service;

import com.tongji.bwm.Application;
import com.tongji.bwm.entity.ERMS.All;
import com.tongji.bwm.germany.entity.Item;
import com.tongji.bwm.germany.service.ItemService;
import com.tongji.bwm.service.ERMS.AllService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * @author starcloud
 * @date 2019/11/12
 **/
@Slf4j
@SpringBootTest(classes = {Application.class})
public class TestGermanItem extends AbstractTestNGSpringContextTests {

    @Autowired
    private ItemService itemService;

    @Autowired
    private AllService allService;


    @Test
    public void TestItem(){
        //我发现又出了一个鬼问题，难受
        All all = new All();
        all = allService.GetById("8E0B96E4-06D9-4868-B54E-55D6FB72FE55");

//        //先试试看能不能往原表插入
//
//        all.setId(null);
//        try {
//            String id = allService.Insert(all);
//            log.info("插入成功，id：-----------"+id);
//        }catch (Exception e){
//            log.error("出错了");
//        }

        //下一步来着

        Item item = Item.GetInstanceByAll(all);
        log.info("item来着-----------"+item);

        //下一步来着
        try {
            log.info("我想尝试一下成不成功！");
            itemService.InsertToGermany(item);
            log.info("咋就不成功了");
            log.info("id=================="+item.getId());
        }catch (Exception e){
            log.error("哦！出错了！");
        }







    }
}
