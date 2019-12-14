package com.tongji.bwm.http.Scrapy;

import com.tongji.bwm.entity.Spider.SpiderItem;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import com.tongji.bwm.pojo.Enum.Scrapy.ScrapyEnum;
import com.tongji.bwm.service.Spider.SpiderItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
@EnableScheduling
public class ScrapySchedule {

    @Autowired
    private ScrapyService scrapyService;

    @Autowired
    private SpiderItemService spiderItemService;

    /*
    只做启动处理
     */

    @Scheduled(cron = "0 53 23 * * ?")
    public void task(){
        //先获取所有 isopen=1
        List<SpiderItem> list1 = spiderItemService.findAllIsOpen(CommonEnum.AvailableEnum.Enable);
//        List<SpiderItem> list1 = spiderItemService.findOneTest();

        int total = list1.size();
        int skipCount = 0;
        int errorCount = 0;
        int successCount = 0;
        log.info("开始自动爬虫！");
        for(SpiderItem spiderItem : list1){
        /* 判断时间
            1.获取周期(天)
            2.获取上次时间
             */
            log.info("爬虫开始启动："+spiderItem.getName());
            if(!IsNeedCrawl(spiderItem.getLastCrawlDate(), spiderItem.getInterval())) {
                skipCount++;
                continue;
            }
            if(scrapyService.Start(spiderItem,"AUTO","127.0.0.1")){
                log.info("爬虫启动成功："+spiderItem.getName());
                spiderItem.setLastAction(ScrapyEnum.ActionEnum.AUTO_RUN);
                spiderItem.setLastResult(
                    SucessResult(spiderItem)
                );
                spiderItem.setLastActionUser("AUTO");
                spiderItem.setLastActionTime(new Date());
                spiderItemService.Update(spiderItem);
                successCount++;
            }else {
                log.info("爬虫启动失败："+spiderItem.getName());
                errorCount++;
            }
        }
        log.info("本轮自动爬虫结束，[总数]:"+total+" [成功数]:"+successCount+" [失败数]:"+errorCount+" [跳过数]:"+skipCount);
    }

    private String SucessResult(SpiderItem spiderItem){

        return "{'success':1}";
    }

    private boolean IsNeedCrawl(Date lastTime, Integer interval){
        long last = lastTime.getTime();
        long now = (new Date()).getTime();
        log.info("上次时间:"+last);
        log.info("本次时间:"+now);
        if(last + interval*24*60*60*1000 <= now)
            return true;
        return false;
    }

}
