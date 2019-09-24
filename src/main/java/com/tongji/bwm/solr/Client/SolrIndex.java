package com.tongji.bwm.solr.Client;

import com.tongji.bwm.pojo.Pagination;
import com.tongji.bwm.entity.ERMS.All;
import com.tongji.bwm.filters.CustomException;
import com.tongji.bwm.service.ERMS.AllService;
import com.tongji.bwm.service.ERMS.ItemService;
import com.tongji.bwm.solr.Models.TaskInfo;
import com.tongji.bwm.solr.Models.TaskTypeEnum;
import com.tongji.bwm.utils.FilterEntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Component
public class SolrIndex {

//    @Autowired
//    private ItemService itemService;

    @Autowired
    private AllService allService;
    
    @Autowired
    private SolrConfig solrConfig;

    private TaskInfo taskInfo = null;

    public final static ReentrantLock lock = new ReentrantLock();

    public TaskInfo getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(TaskInfo taskInfo) {
        this.taskInfo = taskInfo;
    }

    public void initTaskInfo(TaskTypeEnum taskTypeEnum){
        if(taskInfo == null) {
            taskInfo = new TaskInfo();
        }
        taskInfo.setTaskType(taskTypeEnum);
        taskInfo.setStartTime(new Date());
        taskInfo.setPercent(0.0);
        taskInfo.setStatus(1);
    }

    public Future<String> startSolrTask(TaskTypeEnum taskTypeEnum) throws CustomException{
        if(taskTypeEnum==null)
            throw new CustomException("操作失败！","请确定索引操作！");

        log.info("Acquiring Lock!");
        if(!lock.tryLock()){
            throw new CustomException("操作失败！","当前有索引任务正在进行！");
        }

        log.info("Lock Acquired!");
        //初始化
        log.info("初始化索引任务信息!");
        initTaskInfo(taskTypeEnum);

        Future<String> future=null;
        switch (taskTypeEnum){
            case ADD:
                future = startCreateIndex();
                break;
            case DELETE:
                break;
        }

        if(future==null){
            throw new CustomException("操作失败！","未执行对应的索引任务！");
        }

        return future;
    }



    @Async("solrTaskExecutor")
    public Future<String> startCreateIndex() throws CustomException {
        //此处全是冗余的判断,没意义，前台与这些返回无关
        if(taskInfo==null){
            log.warn("Solr索引创建失败，索引创建信息为空！");
            throw new CustomException("操作失败！","Solr创建索引失败");

//            return new AsyncResult<>("Solr索引创建失败，索引创建信息为空！");
        }
        if(taskInfo.getStatus()==0){
            log.warn("Solr创建索引任务不存在！");
            throw new CustomException("操作失败！","初始化创建索引任务失败，请重试！");
        }


        //设置初始值
        taskInfo.setTotalCount(allService.Count(null));
        log.info("总文献数量："+taskInfo.getTotalCount());
        int num = 1;
        int rows = 500;
        int num2 = 0;
        int num3 = 0;
        int skipCount = 0;

        while(taskInfo.getStatus() ==1){
            Pagination<All> pageList = allService.GetPageList(FilterEntityUtils.getPageRowCondition(num-1,rows));
            log.info("该循环查询到Item数量:    "+pageList.getList().size());
            if(pageList.getList().size()==0){//循环两次测试
                taskInfo.setCompleteCount(num2);
                taskInfo.setErrorCount(num3);
                taskInfo.setSkipCount(skipCount);
                taskInfo.setStatus(0);
                taskInfo.setEndTime(new Date());
            }else {
                try {
                    Integer i=0;
                    log.info("开始本次500条创建");
                    List<All> list = new ArrayList<>();

                    for (All all : pageList.getList()) {
                        //每25个插入一次
//                        log.info("500中一次:"+ i++);
                        list.add(all);
                        if (list.size() == 25) {
//                            log.info("插入25条一次之前");
                            allService.InsertToSolr(solrConfig.getUrl(), list.toArray(new All[list.size()]), true);
//                            log.info("插入25条一次");
                            list.clear();
                        }
                    }
                    if(list.size()>0){
                        allService.InsertToSolr(solrConfig.getUrl(), list.toArray(new All[list.size()]), true);
                        log.info("该次循环最后一次成功插入！");
                        list.clear();
                    }
                }catch (DocumentException e){
                    log.warn("创建索引出错+\r\n"+e.toString());
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
            log.info("当前进度：--------------"+taskInfo.getPercent()+"%");

            num++;
        }
        //结束后
        //最好是存储此次log
        log.info("Solr索引创建成功结束！");
        return new AsyncResult<>("成功结束");
    }

    //删除索引
    public void DeleteAll(){

    }
}
