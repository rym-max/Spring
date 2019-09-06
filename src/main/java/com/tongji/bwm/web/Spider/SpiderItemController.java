package com.tongji.bwm.web.Spider;

import com.tongji.bwm.entity.Spider.Item;
import com.tongji.bwm.filters.CustomException;
import com.tongji.bwm.filters.validation.CustomValidationException;
import com.tongji.bwm.http.Scrapy.ScrapyService;
import com.tongji.bwm.pojo.Enum.Scrapy.ScrapyEnum;
import com.tongji.bwm.pojo.FilterCondition.FilterCondition;
import com.tongji.bwm.pojo.Pagination;
import com.tongji.bwm.service.Spider.SpiderConfigService;
import com.tongji.bwm.service.Spider.SpiderItemService;
import com.tongji.bwm.utils.FilterEntityUtils;
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
@RequestMapping("/Spider/Item")
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
        return new ModelAndView("/Spider/Item/Index");
    }

    @RequestMapping("/Search")
    @ResponseBody
    public List<Item> Search(@RequestBody FilterCondition filterCondition){
        if(filterCondition==null)
            return spiderItemService.GetAll();

        List<Item> list = spiderItemService.GetList(filterCondition);
        return list;
    }

    @RequestMapping("/SearchPage")
    @ResponseBody
    public Pagination<Item> SearchPage(HttpServletRequest httpServletRequest,
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
            modelMap.addAttribute("item",new Item());
        }else {
            modelMap.addAttribute("item",spiderItemService.GetById(id));
        }

        return new ModelAndView("/Spider/Item/Edit",
                modelMap);
    }

    @RequestMapping(value = "/edit",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> edit(@RequestBody @Validated Item item, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            throw new CustomValidationException("操作失败！",bindingResult.getFieldErrors());

        //修改配置信息
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

        Item item = spiderItemService.GetById(id);
        if(item==null)
            throw new CustomException("操作失败！","爬虫不存在！");
        //
        String ip = httpServletRequest.getRemoteAddr();
        Principal principal= httpServletRequest.getUserPrincipal();
        String name = principal.getName();
        if(scrapyService.Start(item,name,ip)) {
            item.setLastAction(ScrapyEnum.ActionEnum.USER_RUN);
            item.setLastResult(
                    "{'success':1}"
            );
            item.setLastActionUser(name);
            item.setLastActionTime(new Date());
            spiderItemService.Update(item);
        }else{
            throw new CustomException("操作失败！","爬虫手动启动失败，爬虫进入禁用状态，请排查问题后再启动！");

        }

        return Success("操作！",null);
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
        Pattern referPattern = Pattern.compile("http.*?//.*?/(\\w/)*?Spider/Item/(index\\.html)*?");
        Matcher matcher = referPattern.matcher(refer);
        return matcher.matches();
    }
}
