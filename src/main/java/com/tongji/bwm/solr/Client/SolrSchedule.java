package com.tongji.bwm.solr.Client;

import com.tongji.bwm.solr.Models.TaskTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @author starcloud
 * @date 2019/12/13
 **/
@Slf4j
@Component
@EnableScheduling
public class SolrSchedule {

    @Autowired
    private SolrIndex solrIndex;

    @Scheduled(cron = "0 0 4 * * ?")
    public void task(){
        log.info("开始自动创建solr索引");
        if(solrIndex.getTaskInfo()==null || solrIndex.getTaskInfo().getStatus().equals(0)) {
            log.info("创建任务开始");
            solrIndex.initTaskInfo(TaskTypeEnum.ADD);
            Future<String> future = solrIndex.startFreshIndex();
            try {
                String result = future.get();
                log.info("创建索引结束");
                log.info(result);
            } catch (Exception e) {
                log.warn("创建索引失败");
                log.warn(e.getMessage());
            }
        }else {
            log.info("索引任务正在执行，自动创建结束。");
        }
    }
}
