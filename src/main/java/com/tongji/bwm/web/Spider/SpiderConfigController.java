package com.tongji.bwm.web.Spider;

import com.tongji.bwm.entity.Spider.Config;
import com.tongji.bwm.filters.CustomException;
import com.tongji.bwm.pojo.FilterCondition.FilterCondition;
import com.tongji.bwm.pojo.Pagination;
import com.tongji.bwm.service.Spider.SpiderConfigService;
import com.tongji.bwm.service.Spider.SpiderItemService;
import com.tongji.bwm.web.Basic.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Spider/Config")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class SpiderConfigController extends BaseController {

    @Autowired
    private SpiderConfigService spiderConfigService;

    @Autowired
    private SpiderItemService spiderItemService;

    @RequestMapping(value = {"/","/index.html"})
    public ModelAndView index(){
        return new ModelAndView("/Spider/Config/Index");
    }

    @RequestMapping("/Search")
    @ResponseBody
    public List<Config> Search(){
        return spiderConfigService.GetAll();
    }

    @RequestMapping("/SearchPage")
    @ResponseBody
    public Pagination<Config> SearchPage(HttpServletRequest httpServletRequest,
                                         @RequestParam(value = "page",defaultValue = "1")Integer page,
                                         @RequestParam(value = "rows",defaultValue = "10") Integer rows){
        FilterCondition filterCondition = new FilterCondition();
        filterCondition.page=page-1;
        filterCondition.rows=rows;

        return spiderConfigService.GetPageList(filterCondition);
    }

    @RequestMapping(value = {"/Edit/{id}","/Edit/","/Edit"})
    public ModelAndView Edit(@PathVariable(required = false) Integer id){
        ModelMap modelMap = new ModelMap();
        if(id==null){
            modelMap.addAttribute("config",new Config());
        }else{
            modelMap.addAttribute("config",spiderConfigService.GetById(id));
        }

        return new ModelAndView("/Spider/Config/Edit",
                modelMap);
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Map<String,Object> edit(@RequestBody Config config){
        //先判断是否可修改
        //再判断是否json字符串

        if(config.getId()==null){
            //新建
            if(spiderConfigService.GetByName(config.getName())!=null)
                throw new CustomException("操作失败！","该爬虫配置名被占用，请重新输入！");
            ValidateJsonConfig(config);
            config.setCreator("ADMIN");
            spiderConfigService.Insert(config);
        }else {
            //更新
            Config con = spiderConfigService.GetById(config.getId());
            if(con == null)
                throw new CustomException("操作失败！","该爬虫配置不存在！");
            if(!con.getCreator().equals("ADMIN"))
                throw new CustomException("操作失败！","该爬虫配置不可修改");
            ValidateJsonConfig(config);
            con.setName(config.getName());
            con.setConfigs(config.getConfigs());
            con.setRules(config.getRules());
            spiderConfigService.Update(con);
        }

        return Success("操作成功！",null);
    }

    @RequestMapping("/Delete/{id}")
    @ResponseBody
    public Map<String,Object> Delete(@PathVariable Integer id){
        if(spiderItemService.findByConfigId(id).size()>0)
            throw new CustomException("操作失败！","该配置下有实际爬虫运行!");
        if(!spiderConfigService.GetById(id).getCreator().equals("ADMIN"))
            throw new CustomException("操作失败！","该爬虫配置不可删除！");
        spiderConfigService.Delete(id);
        return Success("操作成功！",null);
    }


    private void ValidateJsonConfig(Config config) {
        if (config.getConfigs() == null || config.getConfigs().isEmpty())
            throw new CustomException("操作失败！", "爬虫配置信息不可为空！");
        if (!spiderConfigService.IsJsonStr(config.getConfigs()))
            throw new CustomException("操作失败！", "爬虫配置信息必须为JSON字符串！");
        if (config.getRules() != null && !config.getRules().isEmpty()
                && !spiderConfigService.IsJsonStr(config.getRules()))
            throw new CustomException("操作失败！", "爬虫规则信息必须为JSON字符串！");
    }
}
