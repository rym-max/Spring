package com.tongji.bwm.web.Front;

import com.tongji.bwm.entity.ERMS.All;
import com.tongji.bwm.filters.CustomException;
import com.tongji.bwm.pojo.Pagination;
import com.tongji.bwm.pojo.SimpleItem;
import com.tongji.bwm.service.ERMS.AllService;
import com.tongji.bwm.solr.Models.ClusterResult;
import com.tongji.bwm.web.Basic.BaseController;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @author starcloud
 * @date 2019/12/13
 **/
@Slf4j
@Controller
@RequestMapping("/Home/Search")
public class SearchController extends BaseController {

    @Autowired
    private AllService allService;


    /**
     *注释用中文
     * date: 19/12/13 18:57
     * description:
     * 返回页面 q为查询query s为高亮关键词
     * @params
     [query, s]
     * @return org.springframework.web.servlet.ModelAndView
    **/
    @RequestMapping(value = {"/Index"})
    public ModelAndView index(@RequestParam String q,
                              @RequestParam String s){
        //q就是query s要用来对红字进行筛选
        ModelMap modelMap = new ModelMap();
        return new ModelAndView("/Front/search",modelMap);
    }

    @RequestMapping("/data")
    @ResponseBody
    public Map<String,Object> data(@RequestParam(name = "q") String query,
                                   @RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "10") Integer rows){

        if(query==null||query.isEmpty()){
            throw new CustomException("访问错误！","访问信息有误！");
        }
        //构建完整的parameters

        List<Pair<String,String>> parameters = new ArrayList<>();
        parameters.add(new Pair<>("q",query));
        parameters.add(new Pair<>("start",""+(page-1)*rows));
        parameters.add(new Pair<>("rows",""+rows));
        parameters.add(new Pair<>("sort","dateissued_sort desc"));
        parameters.add(new Pair<>("wt","json"));
        parameters.add(new Pair<>("facet","on"));
        parameters.add(new Pair<>("facet.field","source_filter,dateissued_filter,category"));
        parameters.add(new Pair<>("facet.mincount","1"));

        Pagination<All> pagination;
        Map<String,List<ClusterResult>> cluster = new HashMap<>();
        try {
            pagination = allService.GetPageList(parameters,cluster);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomException("访问出错！","内部服务器错误！");
        }
        //需要对pagination简单的置换
        Pagination<SimpleItem> pagination1 = SimpleItem.getPagination(pagination);

        //需要对category的list进行一个简单的排序，升序排列
        Collections.sort(cluster.get("category"), new Comparator<ClusterResult>() {
            @Override
            public int compare(ClusterResult o1, ClusterResult o2) {
                int y1 = Integer.valueOf(o1.getName());
                int y2 = Integer.valueOf(o2.getName());

                return Integer.compare(y1,y2);
            }
        });


        //需要对year 的list进行一个简单的排序 ，降序排列
//        List<ClusterResult> list_year= cluster.get("dateissued_filter");
        Collections.sort(cluster.get("dateissued_filter"), new Comparator<ClusterResult>() {
            @Override
            public int compare(ClusterResult o1, ClusterResult o2) {
                int y1 = Integer.valueOf(o1.getName());
                int y2 = Integer.valueOf(o2.getName());

                return Integer.compare(y2,y1);
            }
        });

        //输出
        Map<String,Object> map = new HashMap<>();
        map.put("items",pagination1);
        map.put("cluster",cluster);


        return Success("访问成功！",map);
    }
}
