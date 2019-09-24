package com.tongji.bwm.web.Log;

import com.tongji.bwm.entity.Spider.SpiderItem;
import com.tongji.bwm.filters.CustomException;
import com.tongji.bwm.pojo.Enum.Scrapy.ScrapyEnum;
import com.tongji.bwm.pojo.SpiderLog.SpiderResult;
import com.tongji.bwm.pojo.SpiderLog.UserCount;
import com.tongji.bwm.service.Log.SpiderLogService;
import com.tongji.bwm.service.Spider.SpiderItemService;
import com.tongji.bwm.web.Basic.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Slf4j
@Controller
@RequestMapping("/Log/SpiderLog")
public class SpiderLogController extends BaseController {

    @Autowired
    private SpiderLogService spiderLogService;

    @Autowired
    private SpiderItemService spiderItemService;

    @RequestMapping(value = {"/","index.html"})
    public ModelAndView Index(){
        log.info("接到爬虫日志访问请求");

        Map<String,Object> map = new HashMap<>();

        Calendar date = Calendar.getInstance();
        map.put("Year",date.get(date.YEAR));
        map.put("Month",date.get(date.MONTH)+1);

        ScrapyEnum.ActionEnum[] actionEnums = ScrapyEnum.ActionEnum.values();

        ModelMap model = new ModelMap();
        model.addAttribute("date",map);
        model.addAttribute("actionEnums",actionEnums);
        log.info("返回爬虫访问日志页面");
        model.addAttribute("lcoation","SPIDERLOG");
        return new ModelAndView("/Log/SpiderLog/Index",model);
    }

    @ResponseBody
    @RequestMapping("/SearchUserCount")
    public Map<String,Object> SearchUserCount(@RequestParam(name = "spider",defaultValue = "0")  Integer spiderId,
                                              @RequestParam(name = "action",defaultValue = "CRAWL_OVER") String action){
        log.info("spiderId-------"+spiderId+"   action--------"+action);
        Map<String,Object> map = new HashMap<>();
        if(spiderId==null||spiderId==0)
            throw new CustomException("查询失败！","请输入要查询的爬虫日志ID！");

        SpiderItem oneSpider = spiderItemService.GetById(spiderId);
        if(oneSpider==null)
            throw new CustomException("查询失败！","查询的爬虫不存在！");

        List<UserCount> list2 = spiderLogService.GetUserCountBySpider(spiderId,action);
        map.put("usercount",list2);

        return Success("查询成功！",map);
    }



    @ResponseBody
    @RequestMapping("/Search")
    public Map<String,Object> Search(@RequestParam(name = "spider",defaultValue = "0")  Integer spiderId,
                                     @RequestParam(name = "times",defaultValue = "30") Integer times){
        log.info("spiderId------"+spiderId+"    times------"+times);
        Map<String,Object> map = new HashMap<>();
        if(spiderId==null||spiderId==0)
            throw new CustomException("查询失败！","请输入要查询的爬虫日志ID！");

        SpiderItem oneSpider = spiderItemService.GetById(spiderId);
        if(oneSpider==null)
            throw new CustomException("查询失败！","查询的爬虫不存在！");

        List<SpiderResult> list1 = spiderLogService.GetSpiderResults(spiderId,times);

        List<Integer> pageCount = new ArrayList<>();
        List<Integer> itemCount = new ArrayList<>();
        List<String> day = new ArrayList<>();
        List<String> actionList = new ArrayList<>();
        for(SpiderResult s:list1){
            pageCount.add(s.getPageCount());
            itemCount.add(s.getItemCount());
            day.add(s.getDay());
            actionList.add(s.getAction());
        }

        map.put("pagecount",pageCount);
        map.put("itemcount",itemCount);
        map.put("day",day);
        map.put("actionList",actionList);

        return Success("查询成功！",map);
    }
}
