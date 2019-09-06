package com.tongji.bwm.web.Log;

import com.tongji.bwm.pojo.AccessLog.CountByDay;
import com.tongji.bwm.pojo.AccessLog.IpCount;
import com.tongji.bwm.filters.CustomException;
import com.tongji.bwm.service.Log.AccessLogService;
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
@RequestMapping("/Log/AccessLog")
public class AccessLogController extends BaseController {

    @Autowired
    private AccessLogService accessLogService;

    @RequestMapping(value = {"/","index.html"})
    public ModelAndView Index(){
        log.info("接收到访问日志页面请求");
        Calendar date = Calendar.getInstance();
        Map<String,Object> map = new HashMap<>();
        map.put("Year",date.get(date.YEAR));
        map.put("Month",date.get(date.MONTH)+1);

        ModelMap model = new ModelMap();
        model.addAttribute("date",map);
        log.info("返回访问日志页面");
        return new ModelAndView("/Log/AccessLog/Index",model);
    }


    @RequestMapping("/Search")
    @ResponseBody
    public Map<String,Object> Search(@RequestParam(name = "month",defaultValue = "0") Integer month ){
        Map<String,Object> map = new HashMap<>();
        log.info("接收到accesslog查询请求,查询月份为：["+month+"]");
        if(month==null||month<=0||month>=12){
            throw new CustomException("查询失败！","时间输入错误！");
        }

        Calendar date = Calendar.getInstance();
        List<CountByDay> list1 = accessLogService.GetEachDayAccessByMonth(date.get(date.YEAR),month);
        List<IpCount> list2 = accessLogService.GetIpCount(date.get(date.YEAR),month);
        map.put("ipcount",list2);

        date.set(Calendar.MONTH,month-1);
        int lastday = date.getActualMaximum(Calendar.DATE);
        List<String> day = new ArrayList<>();

        for(int i=0;i<lastday;i++){
            day.add((i+1)+"日");
        }
        map.put("xdata",day);


        Integer[] daycount = new Integer[lastday];
        for(int i=0;i<daycount.length;i++){
            daycount[i]=0;
        }
        for(CountByDay oneday:list1){
            daycount[oneday.getDay()-1]=oneday.getCount();
        }

        map.put("data",new ArrayList<>(Arrays.asList(daycount)));
        log.info("accesslog查询请求成功");
        return Success("操作成功！",map);
    }

}
