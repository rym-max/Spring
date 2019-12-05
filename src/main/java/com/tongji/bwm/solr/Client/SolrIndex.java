package com.tongji.bwm.solr.Client;

import com.tongji.bwm.pojo.Enum.CommonEnum;
import com.tongji.bwm.pojo.FilterCondition.FilterCondition;
import com.tongji.bwm.pojo.Pagination;
import com.tongji.bwm.entity.ERMS.All;
import com.tongji.bwm.filters.CustomException;
import com.tongji.bwm.service.ERMS.AllService;
import com.tongji.bwm.solr.Models.TaskInfo;
import com.tongji.bwm.solr.Models.TaskTypeEnum;
import com.tongji.bwm.utils.FilterEntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

/**
 *注释有中文
 * version 1.0
 * date
 * author hzxcloud
 * description:
 * 创建索引服务
 * 功能 -->实现 -->原理
 * 1.索引过程可查询 --> 私有成员taskInfo --> Spring Bean 单例模式
 * 2.索引可创建 --> 根据page 轮询数据库 --> jpa
 * 3.索引创建异步 --> 使用@Async --> @Async 开启线程
 * 4.索引创建条件 --> 使用example --> findAll 限制条件 jpa
 * 理论上一直是这样 为啥做不到
 *
**/
@Slf4j
@Component
@Scope(value = "singleton")
public class SolrIndex {

//    @Autowired
//    private ItemService itemService;

    @Autowired
    private AllService allService;
    
    @Value("${solrserver.core.tongjieu}")
    private String solrUrl;//用不上

    private TaskInfo taskInfo = null;

    public final static ReentrantLock lock = new ReentrantLock();

    public TaskInfo getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(TaskInfo taskInfo) {
        this.taskInfo = taskInfo;
    }

    public void initTaskInfo(TaskTypeEnum taskTypeEnum){

        taskInfo = new TaskInfo();

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
    public Future<String> startFreshIndex() throws CustomException{
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

//        All oneInstance = new All();
//        oneInstance.setStatus(CommonEnum.AuditStatusEnum.NotPass);

//        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withMatcher("status",ExampleMatcher.GenericPropertyMatchers.exact())
//                .withIgnorePaths("isAudit","isSolr","isGermany","click","sort")
//                .withIgnoreCase();
//
//        Example<All> example = Example.of(oneInstance,matcher);

        return startIndex(CommonEnum.AuditStatusEnum.NotPass);
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
        log.info("至少让我看到进来了！");
        return startIndex(null);

    }


    public Future<String> startIndex(CommonEnum.AuditStatusEnum status){
        log.info("进入正式创建索引！");
        //设置初始值
        taskInfo.setTotalCount(allService.Count(status));

        //count太慢了
        log.info("总文献数量："+taskInfo.getTotalCount());
        int num = 1;
        int rows = 500;
        int num2 = 0;
        int num3 = 0;
        int skipCount = 0;

        //为了调试能够阻塞
        Future<String> future = new AsyncResult<>("未进入结束");
        //循环开始
        while(taskInfo.getStatus() ==1){
            log.info("开始本次数据库查询，仔细查看查询时间！");
            Pagination<All> pageList = allService.GetPageList(status,num-1,rows,taskInfo.getTotalCount());
            log.info("该循环查询到Item数量:    "+pageList.getList().size());
            if(pageList.getList().size()==0){//循环两次测试
                log.info("Solr索引创建成功结束！");
                taskInfo.setTotalCount(0L);
                taskInfo.setPercent(0.0);
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
                            future = allService.InsertToSolr(list.toArray(new All[list.size()]), true);
//                            log.info("插入25条一次");
                            list.clear();
                        }
                    }
                    if(list.size()>0){
                        future = allService.InsertToSolr(list.toArray(new All[list.size()]), true);
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

        return future;
    }



    //删除索引
    public void DeleteAll(){

    }
}
