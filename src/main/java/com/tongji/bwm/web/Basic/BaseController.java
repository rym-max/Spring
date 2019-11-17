package com.tongji.bwm.web.Basic;

import com.tongji.bwm.pojo.FrontAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

//    @Autowired
//    protected FrontAdmin frontAdmin;

    public Map<String,Object> Success(String message,Object data){
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        map.put("message",message);
        map.put("data",data==null?"":data);
        return map;
    }

    public ModelAndView RedirectToHome(){
        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("location","HOME");
        return new ModelAndView("redirect:/Home/frontIndex",modelMap);
    }

    public ModelAndView RedirectToBack(){
        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("location","HOME");
        return new ModelAndView("redirect:/Home/index",modelMap);
    }
}
