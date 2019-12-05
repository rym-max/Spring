package com.tongji.bwm.web.Basic;

import com.tongji.bwm.pojo.Enum.CommonEnum;
import com.tongji.bwm.service.Basic.SimpleCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class HomeController extends BaseController {

    @Autowired
    private SimpleCacheService simpleCacheService;

    @RequestMapping("/Login.html")
    public String Login(){

        return "/Home/login";
    }


    @RequestMapping({"/Back/index.html","/Back/"})
    public ModelAndView backHome(){
        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("location","HOME");
        return new ModelAndView("/Home/backIndex",modelMap);
    }

    @RequestMapping({"/Home/index.html","/"})
    public ModelAndView index(){
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("");
        return new ModelAndView("/Home/frontIndex",modelMap);
    }

    @RequestMapping({"/Home/SiteList"})
    public ModelAndView siteList(){
        return new ModelAndView("/Front/siteList");
    }

    @RequestMapping({"/Home/About"})
    public ModelAndView about(){
        return new ModelAndView("/Front/about");
    }

    @ResponseBody
    @RequestMapping({"/Home/homeList"})
    public Map<String,Object> homeList(){
        Map<String,Object> map = new HashMap<>();
        log.info("至少进来了！");
        map.put("pagelist1",simpleCacheService.getHomeItem(CommonEnum.RegionType.All));
        map.put("pagelist2",simpleCacheService.getHomeItem(CommonEnum.RegionType.EuUnion));
        map.put("pagelist3",simpleCacheService.getHomeItem(CommonEnum.RegionType.EuSubArea));
        map.put("pagelist4",simpleCacheService.getHomeItem(CommonEnum.RegionType.EuUnionMember));
        map.put("pagelist5",simpleCacheService.getHomeItem(CommonEnum.RegionType.EuCountries));

        return Success("访问成功!",map);
    }


}
