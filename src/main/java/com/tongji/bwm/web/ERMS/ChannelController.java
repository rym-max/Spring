package com.tongji.bwm.web.ERMS;

import com.tongji.bwm.entity.ERMS.Channel;
import com.tongji.bwm.filters.CustomException;
import com.tongji.bwm.filters.validation.CustomValidationException;
import com.tongji.bwm.service.ERMS.CategoryService;
import com.tongji.bwm.service.ERMS.ChannelService;
import com.tongji.bwm.web.Basic.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ERMS/Channel")
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ARTICLE')")
public class ChannelController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ChannelService channelService;

    @RequestMapping(value = {"/","/index.html"})
    public ModelAndView index(){
        return new ModelAndView("/ERMS/Channel/Index");
    }

    @RequestMapping("/Search")
    @ResponseBody
    public List<Channel> Search(){
        return channelService.GetAll();
    }

    @RequestMapping(value = {"/Edit/{id}","/Edit/","/Edit"})
    public ModelAndView Edit(@PathVariable(required = false) Integer id){
        ModelMap modelMap = new ModelMap();

        if(id==null){
           modelMap.addAttribute("ch",new Channel());
        }else {
            modelMap.addAttribute("ch",channelService.GetById(id));
        }
        return new ModelAndView("/ERMS/Channel/Edit",
                modelMap);
    }

    @RequestMapping(value = "/edit",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> edit(@RequestBody @Validated Channel channel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new CustomValidationException("操作失败！",bindingResult.getFieldErrors());
        }
        if(channel.getId()==null){
            if(channelService.GetByCode(channel.getCode())!=null)
                throw new CustomException("操作失败！","编码已经被占用，请重新输入！");
            channelService.Insert(channel);

        }else {
            Channel ch = channelService.GetByCode(channel.getCode());
            if (ch != null && !ch.getId().equals(channel.getId()))
                throw new CustomException("操作失败！", "编码已经被占用，请重新输入！");
            ch = channelService.GetById(channel.getId());
            if (ch == null)
                throw new CustomException("操作失败！", "栏目不存在或者已经被删除！");
            //要保证一些不可被修改???那有啥意义
            ch.setName(channel.getName());
            ch.setCode(channel.getCode());
            ch.setSort(channel.getSort());
            ch.setQueryExpression(channel.getQueryExpression());
            ch.setDescription(channel.getDescription());
            channelService.Update(ch);
        }
        return Success("操作成功！",null);
    }

    @RequestMapping("/Delete/{id}")
    @ResponseBody
    public Map<String,Object> Delete(@PathVariable Integer id){
        if(categoryService.findByChannelId(id).size()>0)
            throw new CustomException("操作失败","该栏目下有子分类，不能被删除");
        channelService.Delete(id);//此处异常怎么办，exception
        return Success("操作成功！",null);
    }

    @RequestMapping(value = {"/Metadata","/metadata.html"})
    public ModelAndView Metadata(){
        return new ModelAndView("/ERMS/Channel/Metadata");
    }
}
