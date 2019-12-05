package service;

import com.tongji.bwm.Application;
import com.tongji.bwm.entity.ERMS.All;
import com.tongji.bwm.entity.ERMS.Region;
import com.tongji.bwm.germany.entity.Item;
import com.tongji.bwm.germany.repository.ItemRepository;
import com.tongji.bwm.pojo.Pagination;
import com.tongji.bwm.pojo.SimpleItem;
import com.tongji.bwm.repository.ERMS.AllRepository;
import com.tongji.bwm.service.Basic.SimpleCacheService;
import com.tongji.bwm.service.ERMS.AllService;
import com.tongji.bwm.service.ERMS.RegionService;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * @author starcloud
 * @date 2019/12/02
 **/
@Slf4j
@SpringBootTest(classes = {Application.class})
public class TestAll extends AbstractTestNGSpringContextTests {

    @Autowired
    private AllRepository allRepository;


    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private AllService allService;

    @Test
    public void TestPageList(){
        Pageable pageable = PageRequest.of(20,10);
        log.info("开始时间");
        Page<All> list = allRepository.findAll(pageable);
        log.info("返回的Page的ListSize："+list.getSize());

    }

    @Test
    public void TestPageList2(){
        Pageable pageable = PageRequest.of(20,10);
        log.info("开始时间");
        Page<Item> list = itemRepository.findAll(pageable);
        log.info("返回的Page的ListSize："+list.getSize());
    }

    @Test
    public void TestCount(){
        log.info("开始时间");
        Long count = allRepository.count();
        log.info("数数："+count);
    }


    @Autowired
    private RegionService regionService;

    @Autowired
    private SimpleCacheService simpleCacheService;

    @Test
    public void TestSolrAll(){
        simpleCacheService.evictAllHome();
    }

    @Test
    public void TestChannelId(){
        All all = allService.GetById("4b19c4e5-0196-4992-a5a8-caa9d40917ca");
        log.info("啥情况:----"+all.getChannelId());
    }

}
