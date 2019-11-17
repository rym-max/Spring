package com.tongji.bwm.web.Front;

import com.tongji.bwm.entity.ERMS.All;
import com.tongji.bwm.filters.CustomException;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import com.tongji.bwm.service.ERMS.AllService;
import com.tongji.bwm.web.Basic.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author starcloud
 * @date 2019/11/16
 **/
@Slf4j
@RequestMapping("/Detail")
@Controller
public class DetailController extends BaseController {

    @Autowired
    private AllService allService;

    @RequestMapping("/page")
    public ModelAndView page(@RequestParam(value = "id")String id){
        //这里用前台做把，方便重定向

        All all = allService.GetById(id);
        String fromRegion = "";
        CommonEnum.DetailPageType page = CommonEnum.DetailPageType.Error;
        if(all!=null) {
            fromRegion = all.GetValue("dc.region", " ", "");
            page = CommonEnum.DetailPageType.values()[all.getChannelId()];
        }

        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("fromRegion",fromRegion);
        modelMap.addAttribute("pageType",page);
        return new ModelAndView("/Front/detail",modelMap);
    }


    @RequestMapping("/Item")
    public Map<String,Object> detailItem(@RequestParam(value="id")String id){

        All all = allService.GetById(id);
        if(all==null){
            log.info("访问详情页id不存在！");
            throw new CustomException("访问错误！","id不存在");
        }
        Map<String,Object> map = new HashMap<>();
        //最基本部分
        map.put("title",all.GetValue("dc.title"));
        //期刊部分
        map.put("creator",all.GetValue("dc.creator"," ",""));
        map.put("subject",all.GetValue("dc.subject"," ",""));
        map.put("organ",all.GetValue("dc.organ"));
        map.put("publishDate",all.GetValue("dc.date.issued"));
        map.put("source",all.GetValue("dc.source"));

        String description = all.GetValue("dc.description");
        String xmldesc = StringEscapeUtils.unescapeXml(description);
        map.put("description",xmldesc);//这个需要转码

        //其余部分
        map.put("date",all.GetValue("dc.date.issued"));
        map.put("url",all.GetValue("dc.url"));

        return Success("访问成功！",map);
    }
}
