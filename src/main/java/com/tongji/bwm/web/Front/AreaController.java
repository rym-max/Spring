package com.tongji.bwm.web.Front;

import com.tongji.bwm.entity.ERMS.Region;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import com.tongji.bwm.service.ERMS.RegionService;
import com.tongji.bwm.web.Basic.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author starcloud
 * @date 2019/11/09
 **/

@Slf4j
@RequestMapping("/Region")
@Controller
public class AreaController extends BaseController {

    @Autowired
    private RegionService regionService;

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
            region = regionService.GetById(ids);
            if(region==null||region.getMap()== CommonEnum.AvailableEnum.Disable){
                log.info("该区域不存在或不前台显示，重定向至首页！");
                return RedirectToHome();
            }
        }

        //2.开始构建相关参数
        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("region",region);
        modelMap.addAttribute("params",null);

        return new ModelAndView("/Front/region",modelMap);
    }

}
