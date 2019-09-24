package com.tongji.bwm.web.ERMS;

import com.tongji.bwm.entity.ERMS.Region;
import com.tongji.bwm.filters.CustomException;
import com.tongji.bwm.filters.validation.CustomValidationException;
import com.tongji.bwm.service.ERMS.RegionService;
import com.tongji.bwm.web.Basic.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/ERMS/Region")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class RegionController extends BaseController {

    @Autowired
    private RegionService regionService;

    @RequestMapping(value = {"/","/index.html"})
    public ModelAndView index(){
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("location","REGION");
        return new ModelAndView("/ERMS/Region/Index",
                modelMap);
    }

    @RequestMapping("/Search")
    @ResponseBody
    public List<Region> Search(){
        return regionService.GetAll();
    }

    @RequestMapping("/SearchParent")
    @ResponseBody
    public List<Region> SearchParent(){
        return  regionService.findByParentId(0);
    }


    @RequestMapping(value = {"/Edit/{id}","/Edit/","/Edit"})
    public ModelAndView Edit(@PathVariable(required = false) Integer id){
        ModelMap modelMap = new ModelMap();

        if(id == null){
            modelMap.addAttribute("region",new Region());
        }else{
            modelMap.addAttribute("region",regionService.GetById(id));
        }

        return new ModelAndView("/ERMS/Region/Edit",
                modelMap);
    }

    @RequestMapping(value = "/edit",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> edit(@RequestBody @Validated Region region, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new CustomValidationException("操作失败！",bindingResult.getFieldErrors());
        }
        Region reg;
        if(region.getId()==null) {
            if (regionService.GetByName(region.getName()) != null){
                throw new CustomException("操作失败！", "区域名已经被占用，请重新输入！");
            }
            reg = new Region();
        }else{
            reg = regionService.GetByName(region.getName());
            if(reg != null && !reg.getId().equals(region.getId()))
                throw new CustomException("操作失败！","区域名已经被占用，请重新输入！");
            reg = regionService.GetById(region.getId());
            if(reg == null)
                throw new CustomException("操作失败！","该区域不存在！");
        }

        //所属区域限制,先判断parentId
        if(region.getParentId()!=null) {

            Region parent = regionService.GetById(region.getParentId());
            if (parent == null || parent.getId().equals(0) || parent.getOwnerRegion() != null) {
                throw new CustomException("操作失败！", "该所属区域不可选择！");
            }

            reg.setOwnerRegion(parent);
        }

        reg.setName(region.getName());
        reg.setNameEN(region.getNameEN());
        reg.setNameCN(region.getNameCN());
        reg.setSolrQueryExpression(region.getSolrQueryExpression());
        reg.setDescription(region.getDescription());

        reg.setStatus(region.getStatus());
        reg.setMap(region.getMap());

        if(region.getId()==null){
            log.info(reg.toString());
            regionService.Insert(reg);
        }else{
            regionService.Update(reg);
        }
        return Success("操作成功！",null);

    }

    @RequestMapping("/Delete/{id}")
    @ResponseBody
    public Map<String,Object> Delete(@PathVariable Integer id){
        if(regionService.findByParentId(id).size()>0)
            throw new CustomException("操作失败！","该区域下有子区域！");
        regionService.Delete(id);
        return Success("操作成功！",null);
    }
}
