package com.tongji.bwm.http.Scrapy;

import com.tongji.bwm.entity.Spider.Item;
import com.tongji.bwm.pojo.Enum.Scrapy.ScrapyEnum;
import com.tongji.bwm.service.Spider.SpiderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

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

    @Scheduled(cron = "0 0 1 * * ?")
    public void task(){
        //先获取所有 isopen=1
        List<Item> list1 = spiderItemService.findAllIsOpen(true);

        int total = list1.size();
        int errorCount = 0;
        int successCount = 0;

        for(Item item: list1){
        /* 判断时间
            1.获取周期(天)
            2.获取上次时间
             */
            if(!IsNeedCrawl(item.getLastActionTime(),item.getInterval()))
                continue;
            if(scrapyService.Start(item,"AUTO","127.0.0.1")){

                item.setLastAction(ScrapyEnum.ActionEnum.AUTO_RUN);
                item.setLastResult(
                    SucessResult(item)
                );
                item.setLastActionUser("AUTO");
                item.setLastActionTime(new Date());

                successCount++;
            }else {
                errorCount++;
            }
        }
    }

    private String SucessResult(Item item){

        return "{'success':1}";
    }

    private boolean IsNeedCrawl(Date lastTime, Integer interval){
        long last = lastTime.getTime();
        long now = (new Date()).getTime();

        if(last + interval*24*60*60*1000 <= now)
            return true;
        return false;
    }

}
