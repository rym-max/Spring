package com.tongji.bwm.web.Solr;

import com.tongji.bwm.filters.CustomException;
import com.tongji.bwm.service.ERMS.AllService;
import com.tongji.bwm.solr.Models.TaskInfo;
import com.tongji.bwm.solr.Client.SolrIndex;
import com.tongji.bwm.solr.Models.TaskTypeEnum;
import com.tongji.bwm.web.Basic.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/Solr")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class SolrController extends BaseController {

    @Autowired
    private AllService allService;

    @Autowired
    private SolrIndex solrIndex;

//    private static String CACHE_CREATE_INDEX_KEY = "CACHE_CREATE_INDEX_PROGRESS_INFO";

    @RequestMapping(value = {"/","/index.html"})
    public ModelAndView SolrIndex(){
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("location","SOLR");
        return new ModelAndView("/ERMS/Solr/Index",
                modelMap);
    }

    @RequestMapping("/Progress")
    @ResponseBody
    public TaskInfo Progress(){
        TaskInfo taskInfo = solrIndex.getTaskInfo();
        if(taskInfo==null){
            return new TaskInfo();
        }
        return taskInfo;
    }

    @RequestMapping("/CreateIndex")
    @ResponseBody
    public Map<String,Object> CreateIndex(){
        TaskInfo taskInfo = solrIndex.getTaskInfo();
        if(taskInfo == null || taskInfo.getStatus()==0){
            solrIndex.initTaskInfo(TaskTypeEnum.ADD);
            solrIndex.startCreateIndex();
            return Success("操作成功！",taskInfo);
        }
        throw new CustomException("操作失败！","上一个任务还未执行完，请稍后重试！");
    }
}
