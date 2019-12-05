package com.tongji.bwm.web.Front;

import com.tongji.bwm.entity.ERMS.All;
import com.tongji.bwm.entity.ERMS.Region;
import com.tongji.bwm.filters.CustomException;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import com.tongji.bwm.pojo.Pagination;
import com.tongji.bwm.pojo.SimpleItem;
import com.tongji.bwm.service.ERMS.AllService;
import com.tongji.bwm.service.ERMS.RegionService;
import com.tongji.bwm.solr.Models.ClusterResult;
import com.tongji.bwm.web.Basic.BaseController;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author starcloud
 * @date 2019/11/09
 **/

@Slf4j
@RequestMapping("/Home/Region")
@Controller
public class AreaController extends BaseController {

    @Autowired
    private RegionService regionService;

    @Autowired
    private AllService allService;

    @RequestMapping({"/{id}/{ids}","/{id}","/"})
    public ModelAndView index(@PathVariable(required = false) Integer id,
                              @PathVariable(required = false) Integer ids){
        //1.先判断是否可显示：否则重定向至首页
        Region region;
        if(id==null){
            //重定向至首页
            log.info("无顶层区域，重定向至首页！");
            return RedirectToHome();
        }
        if(ids==null){
            region = regionService.GetById(id);
            if(region==null||region.getMap()== CommonEnum.AvailableEnum.Disable){
                log.info("顶层区域不存在或不前台显示，重定向至首页！");
                return RedirectToHome();
            }
        }else{
            List<Region> list = regionService.findByParentId(id);
            if(list==null||list.size()<ids||list.get(ids-1).getMap()== CommonEnum.AvailableEnum.Disable){
                log.info("该区域不存在或不前台显示，重定向至首页！");
                return RedirectToHome();
            }
            region = list.get(ids-1);
        }

        //2.开始构建相关参数
        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("region",region);
        modelMap.addAttribute("params",null);//主要在于上级的定位

        return new ModelAndView("/Front/region",modelMap);
    }

    @ResponseBody
    @RequestMapping({"/dataList/{id}/{ids}","/dataList/{id}"})
    public Map<String,Object> dataList(@PathVariable(value = "id")Integer id,
                                       @PathVariable(value = "ids",required = false) Integer ids,
                                       @RequestParam(value = "q")String q,
                                       @RequestParam(value = "page")Integer page,
                                       @RequestParam(value = "rows",defaultValue = "10")Integer rows,
                                       @RequestParam(value = "include",required = false,defaultValue = "1")Integer include){
        //先对ID进行简单的判断
        Region region;
        if(id==null){
            //重定向至首页
            log.info("无顶层区域，重定向至首页！");
            throw new CustomException("访问错误！","访问信息有误！");
        }
        if(ids==null){
            region = regionService.GetById(id);
            if(region==null||region.getMap()== CommonEnum.AvailableEnum.Disable){
                log.info("顶层区域不存在或不前台显示，重定向至首页！");
                throw new CustomException("访问错误！","访问信息有误！");
            }
        }else{
            List<Region> list = regionService.findByParentId(id);
            if(list==null||list.size()<ids||list.get(ids-1).getMap()== CommonEnum.AvailableEnum.Disable){
                log.info("该区域不存在或不前台显示，重定向至首页！");
                throw new CustomException("访问错误！","访问信息有误！");
            }
            region = list.get(ids-1);
        }

        //构建params pair
        //首先提取request里参数,注解搞定了
        log.info(q);

        if(q==null||q.isEmpty()){
            throw new CustomException("访问错误！","访问信息有误！");
        }
        q += include.equals(1)?"" : "AND (status:1)";

        if(!region.getSolrQueryString().isEmpty()){
            q+=" AND ("+region.getSolrQueryString()+")";
        }
//        q += " AND (region:"+region.getId();
//        if(region.getSolrQueryExpression()!=null&&!region.getSolrQueryExpression().isEmpty()){
//            q +="OR (issolr:1 AND "+
//                    region.getSolrQueryExpression() +" )";
//        }
//        q +=")";

        //构建完整的parameters

        List<Pair<String,String>> parameters = new ArrayList<>();
        parameters.add(new Pair<>("q",q));
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
