package com.tongji.bwm.solr.Client;

import com.tongji.bwm.pojo.Pagination;
import com.tongji.bwm.entity.ERMS.Item;
import com.tongji.bwm.filters.CustomException;
import com.tongji.bwm.service.ERMS.ItemService;
import com.tongji.bwm.solr.Client.Models.TaskInfo;
import com.tongji.bwm.solr.Client.Models.TaskTypeEnum;
import com.tongji.bwm.utils.FilterEntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

@Slf4j
@Component
public class SolrIndex {

    @Autowired
    private ItemService itemService;

    @Autowired
    private SolrConfig solrConfig;

    private TaskInfo taskInfo = null;

    public TaskInfo getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(TaskInfo taskInfo) {
        this.taskInfo = taskInfo;
    }

    public void initTaskInfo(){
        if(taskInfo == null) {
            taskInfo = new TaskInfo();
        }
        taskInfo.setTaskType(TaskTypeEnum.ADD);
        taskInfo.setStartTime(new Date());
        taskInfo.setPercent(0.0);
        taskInfo.setStatus(1);
    }



    @Async("solrTaskExecutor")
    public Future<String> startCreateIndex() throws CustomException {
        //返回结果

        if(taskInfo==null){
            log.warn("Solr索引创建失败，索引创建信息为空！");
            throw new CustomException("操作失败！","Solr创建索引失败");

//            return new AsyncResult<>("Solr索引创建失败，索引创建信息为空！");
        }
        if(taskInfo.getStatus() == 0){
            log.warn("Solr创建索引失败，索引正在建立");
            throw new CustomException("操作失败！","Solr创建索引失败");
//            return new AsyncResult<>("Solr创建索引失败，索引正在建立");
        }
        //设置初始值
        taskInfo.setTotalCount(itemService.Count(null));
        int num = 1;
        int rows = 500;
        int num2 = 0;
        int num3 = 0;
        int skipCount = 0;
        //循环两次测试
        while(taskInfo.getStatus() ==1){
            Pagination<Item> pageList = itemService.GetPageList(FilterEntityUtils.getPageRowCondition(num-1,rows));

            if(pageList.getList().size()==0){
                taskInfo.setCompleteCount(num2);
                taskInfo.setErrorCount(num3);
                taskInfo.setSkipCount(skipCount);
                taskInfo.setStatus(0);
                taskInfo.setEndTime(new Date());

            }else {
                try {

                    List<Item> list = new ArrayList<>();
                    for (Item item : pageList.getList()) {
                        //每25个插入一次
                        list.add(item);
                        if (list.size() == 25) {
                            itemService.InsertToSolr(solrConfig.getUrl(), list.toArray(new Item[list.size()]), true);
                            log.info("成功插入一次！");
                            list.clear();
                        }
                    }
                    if(list.size()>0){
                        itemService.InsertToSolr(solrConfig.getUrl(), list.toArray(new Item[list.size()]), true);
                        list.clear();
                    }
                }catch (Exception e){
                    log.warn("创建索引出错+\r\r"+e.getMessage()+"\r\n"+e.getCause());
                    num3 += pageList.getList().size();
                }

                num2+= pageList.getList().size();

                taskInfo.setCompleteCount(num2);
                taskInfo.setErrorCount(num3);
                taskInfo.setSkipCount(skipCount);
                taskInfo.setEndTime(new Date());
            }

            if(taskInfo.getTotalCount()==0L){
                taskInfo.setPercent(0.0);
            }else {
                taskInfo.setPercent(Double.parseDouble(
                        String.format("%.2f",
                                taskInfo.getCompleteCount().doubleValue()/taskInfo.getTotalCount().doubleValue()*100
                        ))
                );
            }
            log.info("成果创建500条索引！");
            num++;
        }
        //结束后
        //最好是存储此次log
        log.info("Solr索引创建成功结束！");
        return new AsyncResult<>("成功结束");
    }
}
