package service;

import com.tongji.bwm.Application;
import com.tongji.bwm.entity.Spider.SpiderItem;
import com.tongji.bwm.http.Scrapy.ScrapyService;
import com.tongji.bwm.service.Spider.SpiderItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * @author starcloud
 * @date 2019/09/17
 **/
@Slf4j
@SpringBootTest(classes = {Application.class})
public class TestScrapy extends AbstractTestNGSpringContextTests {

    @Autowired
    private ScrapyService scrapyService;

    @Autowired
    private SpiderItemService spiderItemService;

    @Test
    public void TestStartByUser(){
        SpiderItem spiderItem = spiderItemService.GetById(1);
        log.info("开始爬取"+spiderItem.getName());

        Boolean success = scrapyService.Start(spiderItem,"TEST",null);
        log.info("爬取成功："+success);
    }

}
