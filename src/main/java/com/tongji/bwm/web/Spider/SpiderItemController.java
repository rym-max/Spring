package com.tongji.bwm.web.Spider;

import com.tongji.bwm.entity.Spider.SpiderItem;
import com.tongji.bwm.filters.CustomException;
import com.tongji.bwm.filters.validation.CustomValidationException;
import com.tongji.bwm.http.Scrapy.ScrapyService;
import com.tongji.bwm.pojo.Enum.Scrapy.ScrapyEnum;
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
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/Spider/SpiderItem")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class SpiderItemController extends BaseController {

    @Autowired
    private SpiderItemService spiderItemService;

    @Autowired
    private SpiderConfigService spiderConfigService;

    @Autowired
    private ScrapyService scrapyService;

    @RequestMapping(value = {"/","/index.html"})
    public ModelAndView index(){
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("location","SPIDERITEM");
        return new ModelAndView("/Spider/Item/Index",
                modelMap);
    }

    @RequestMapping("/Search")
    @ResponseBody
    public List<SpiderItem> Search(@RequestBody FilterCondition filterCondition){
        if(filterCondition==null)
            return spiderItemService.GetAll();

        List<SpiderItem> list = spiderItemService.GetList(filterCondition);
        return list;
    }

    @RequestMapping("/SearchPage")
    @ResponseBody
    public Pagination<SpiderItem> SearchPage(HttpServletRequest httpServletRequest,
                                             @RequestParam(value = "page",defaultValue = "1")Integer page,
                                             @RequestParam(value = "rows",defaultValue = "10")Integer rows){
        FilterCondition filterCondition = new FilterCondition();
        filterCondition.page = page-1;
        filterCondition.rows = rows;

        return spiderItemService.GetPageList(filterCondition);
    }

    @RequestMapping(value = {"/Edit/{id}","/Edit/","/Edit"})
    public ModelAndView Edit(@PathVariable(required = false) Integer id){
        ModelMap modelMap = new ModelMap();

        if(id == null){
            modelMap.addAttribute("spiderItem",new SpiderItem());
        }else {
            modelMap.addAttribute("spiderItem",spiderItemService.GetById(id));
        }

        return new ModelAndView("/Spider/Item/Edit",
                modelMap);
    }

    @RequestMapping(value = "/edit",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> edit(@RequestBody @Validated SpiderItem spiderItem, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            throw new CustomValidationException("操作失败！",bindingResult.getFieldErrors());

        //修改配置信息
        if(spiderItem.getId()==null){
            //如果为空
            if(spiderItemService.GetByName(spiderItem.getName())!=null){
                throw new CustomException("操作失败！","该爬虫名已被占用，请重新输入！");
            }
            Integer configId = spiderItem.getConfigId();
            if(configId==null|| spiderConfigService.GetById(configId)==null){
                throw new CustomException("操作失败！","请选择所属爬虫配置！");
            }
            spiderItemService.Insert(spiderItem);
        }else {
            //如果有id

            SpiderItem item = spiderItemService.GetById(spiderItem.getId());
            if(item==null)
                throw new CustomException("操作失败！","该爬虫不存在！");
            Integer configId = spiderItem.getConfigId();
            if(configId==null|| spiderConfigService.GetById(configId)==null){
                throw new CustomException("操作失败！","请选择所属爬虫配置！");
            }
            //开关状态可修改
            if(spiderItem.getIsOpen()==null)
                throw new CustomException("操作失败！","请确定爬虫启用状态！");
            item.setIsOpen(spiderItem.getIsOpen());

            item.setConfigId(spiderItem.getConfigId());//
            if(item.getCreator().equals("ADMIN")){
                //部分有权限修改
                item.setCustomSettings(spiderItem.getCustomSettings());
                item.setType(spiderItem.getType());
                item.setInterval(spiderItem.getInterval());
                item.setTimes(spiderItem.getTimes());
            }

            spiderItemService.Update(item);


        }


        return Success("操作成功！",null);
    }

    //启动
    @RequestMapping(value = "/start/{id}")
    @ResponseBody
    public Map<String,Object> Start(@PathVariable Integer id,
                                    HttpServletRequest httpServletRequest){
        //必须是跳转过来的
        String referer = httpServletRequest.getHeader("Referer");
        if(!IsRefer(referer)){
            throw new CustomException("操作失败！","请遵照界面操作启停爬虫！");
        }

        SpiderItem spiderItem = spiderItemService.GetById(id);
        if(spiderItem ==null)
            throw new CustomException("操作失败！","爬虫不存在！");
        //
        String ip = httpServletRequest.getRemoteAddr();
        Principal principal= httpServletRequest.getUserPrincipal();
        String name = principal.getName();
        if(scrapyService.Start(spiderItem,name,ip)) {
            spiderItem.setLastAction(ScrapyEnum.ActionEnum.USER_RUN);
            spiderItem.setLastResult(
                    "{'success':1}"
            );
            spiderItem.setLastActionUser(name);
            spiderItem.setLastActionTime(new Date());
            spiderItemService.Update(spiderItem);
        }else{
            throw new CustomException("操作失败！","爬虫手动启动失败，爬虫进入禁用状态，请排查问题后再启动！");

        }

        return Success("操作成功！",null);
    }


    //停止
    @RequestMapping(value = "/stop/{id}")
    @ResponseBody
    public Map<String,Object> Stop(@PathVariable Integer id,
                                   HttpServletRequest httpServletRequest){
        //必须是跳转过来的
        String referer = httpServletRequest.getHeader("Referer");
        if(!IsRefer(referer)){
            throw new CustomException("操作失败！","请遵照界面操作启停爬虫！");
        }



        return Success("操作成功！",null);

    }

    private Boolean IsRefer(String refer){
        Pattern referPattern = Pattern.compile("http.*?//.*?/(\\w/)*?Spider/SpiderItem/(index\\.html)*?");
        Matcher matcher = referPattern.matcher(refer);
        return matcher.matches();
    }
}
