package repository;
//
//import com.tongji.bwm.Application;
//import com.tongji.bwm.entity.ERMS.Item;
//import com.tongji.bwm.pojo.Enum.CommonEnum;
//import com.tongji.bwm.pojo.Pagination;
//import com.tongji.bwm.service.ERMS.ChannelService;
//import com.tongji.bwm.utils.FilterEntityUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Example;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.testng.annotations.Test;
//
//
//@Slf4j
//@SpringBootTest(classes = {Application.class})
public class TestCount extends AbstractTestNGSpringContextTests {
//
//    @Autowired
//    private ItemRepository itemRepository;
//
//    @Autowired
//    private ChannelService channelService;
//
//    @Autowired
//    private ItemService itemService;
//
//
//    @Test
//    public void TestItemCount(){
//
//        Item item = itemRepository.findById("C6CCCA14-EE38-4A28-8993-0001CF4089D0".toLowerCase()).get();
//
//        log.info("Item至少有用"+item.get_field().get("dc.title")[0]);
//        log.info("先测试一下直接查");
//        Long c = itemRepository.countByStatus(CommonEnum.AuditStatusEnum.Pass);
//        log.info("---------------总数："+c);
//
////        //换种方式了，pageable
////        Pageable page = FilterEntityUtils.getPageable(new FilterCondition());
////        Page<SpiderItem> p = itemRepository.findAll(page);
////        log.info("总页数--------"+p.getTotalElements());
//        log.info("测试一下example");
//        Item item2 = new Item();
//        item2.setOwnerChannel(channelService.GetById(2));
//        Example<Item> example = Example.of(item2);
//        long c2 = itemRepository.count(example);
//        log.info("channel2----------总数："+c2);
//    }
//
//    @Test
//    public void TestPage(){
//        Pagination<Item> pageList = itemService.GetPageList(FilterEntityUtils.getPageRowCondition(0,2));
//        for(Item i: pageList.getList()){
//            log.info("-------显示一条Item----------");
//            log.info(i.getId());
//            log.info(i.get_field().get("dc.title")[0]);
//
//        }
//    }
}
