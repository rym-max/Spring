package com.tongji.bwm.web.Basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController extends BaseController {

    @RequestMapping("/Login.html")
    public String Login(){

        return "/Home/login";
    }


    @RequestMapping({"/Home/index.html","/","/Home","/Home/"})
    public ModelAndView home(){
        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("location","HOME");
        return new ModelAndView("/Home/index",modelMap);
    }


}
