package com.tongji.bwm.web.Front;

import com.tongji.bwm.filters.CustomException;
import com.tongji.bwm.service.Book.JournalService;
import com.tongji.bwm.service.ERMS.AllService;
import com.tongji.bwm.solr.Client.SolrConnection;
import com.tongji.bwm.solr.Client.SolrStatistic;
import com.tongji.bwm.solr.Models.ClusterResult;
import com.tongji.bwm.utils.DateFormatterUtils;
import com.tongji.bwm.web.Basic.BaseController;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author starcloud
 * @date 2019/11/09
 **/
@Slf4j
@Controller
@RequestMapping(value = "/Home/Visual")
public class VisualController extends BaseController {

    @Autowired
    private SolrStatistic solrStatistic;

    @Autowired
    private JournalService journalService;

    @RequestMapping("/")
    public ModelAndView index(){
        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("hotword",solrStatistic.hotWord());

        return new ModelAndView("/Front/visual",modelMap);
    }



    @RequestMapping("/data")
    @ResponseBody
    public Map<String,Object> data(@RequestParam String query,
                                   @RequestParam Integer last_date,
                                   @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")Date start_date,
                                   @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")Date end_date,
                                   @RequestParam(required = false,defaultValue = "source_filter") String facet,
                                   @RequestParam(required = false,defaultValue = "false") Boolean journal){
        //先判断是否有language QTM

        //先判断last date 是否存在
        if(last_date!=null){
            Date current_date = new Date();
            Date past_date = DateFormatterUtils.getPastDate(last_date);

            start_date = past_date;
            end_date = current_date;
        }else{
            last_date = DateFormatterUtils.getPeriod(start_date,end_date);
        }
        String q = "";
        if(journal){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
            String s_date = simpleDateFormat.format(start_date);
            String e_date = simpleDateFormat.format(end_date);
            q += "dateissued_filter:[" + s_date +" TO " + e_date +"] ";
        }else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String s_date = simpleDateFormat.format(start_date);
            String e_date = simpleDateFormat.format(end_date);
            q += "dateissued_ss:[" + s_date +" TO " + e_date +"] ";
        }
        log.info("查询日期：<<<<<"+q);

        //因为全是AND连接 不会出问题
        String[] q_li = query.split("\\s+");
        for(int i =0;i<q_li.length;i++){
            q += " AND (description: \""+q_li[i]+"\" ";
            q += " OR subject:\""+q_li[i]+"\" ";
            q += " OR title:\""+q_li[i]+"\" )";
        }
        String sort = "index";
        Integer limit = 10;


        Map<String,Object> map = new HashMap<>();
        if(facet.equals("dateissued_date")){
            q = facetForDate(q,facet);
            limit = last_date;
        }
        if(facet.equals("source_filter")) {
            sort="count";
            q = facetForSource(q,facet,journal);
            limit = journalService.journalCountAll();

        }


        try {
            map = solrStatistic.oneFacet(q,facet,1,limit,sort);
        }catch (Exception e){
            log.error(e.getMessage());
            log.error("查询获取facet错误！");
            throw new CustomException("访问错误！","Solr请求facet错误！");
        }


        return Success("访问成功！",map);
    }

    private String facetForSource(String query,String facet,boolean isJournal){
        //
        if(isJournal) {
            query += " AND (channel:2)";
        }else {
            query += " AND (channel:1)";
        }
        return query;
    }


    private String facetForDate(String query,String facet){
        //查询参数
        return query;
    }

}
