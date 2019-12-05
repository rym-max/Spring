package com.tongji.bwm.http;

import com.tongji.bwm.solr.Client.SolrIndex;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author starcloud
 * @date 2019/12/01
 **/
@Slf4j
@Component
@EnableScheduling
public class SimpleTestSchedule {

    @Autowired
    private SolrIndex solrIndex;

    @Scheduled(cron = "*/10 * * * * ?")
    public void LogTask(){

        if(solrIndex.getTaskInfo()!=null && solrIndex.getTaskInfo().getStatus().equals(1)){
            log.info("当前进度：<<<<<<"+solrIndex.getTaskInfo().getPercent());
        }
    }
}
