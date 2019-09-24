package com.tongji.bwm.web.ERMS;

import com.tongji.bwm.entity.ERMS.MetadataSchemaRegistry;
import com.tongji.bwm.filters.CustomException;
import com.tongji.bwm.filters.validation.CustomValidationException;
import com.tongji.bwm.service.ERMS.MetadataSchemaRegistryService;
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
@RequestMapping("/ERMS/MetadataSchemaRegistry")
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ARTICLE')")
public class MetadataSchemaRegistryController extends BaseController {

    
    @Autowired
    private MetadataSchemaRegistryService metadataSchemaRegistryService;
    
    
    @RequestMapping(value = {"/index.html","/"})
    public ModelAndView index(){
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("location","SCHEMA");
        return new ModelAndView("/ERMS/MetadataSchemaRegistry/Index",
                modelMap);
    }

    @RequestMapping("/Search")
    @ResponseBody
    public List<MetadataSchemaRegistry> Search(){

        return metadataSchemaRegistryService.GetAll();
    }

    @RequestMapping(value = {"/Edit/{id}","/Edit/","/Edit"})
    public ModelAndView Edit(@PathVariable(required = false) Integer id){
        if(id==null){
            return new ModelAndView("/ERMS/MetadataSchemaRegistry/Edit",
                    "mdsr",new MetadataSchemaRegistry());
        }
        return new ModelAndView("/ERMS/MetadataSchemaRegistry/Edit",
                "mdsr",metadataSchemaRegistryService.GetById(id));
    }

    @RequestMapping(value = "/edit",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> edit(@RequestBody @Validated MetadataSchemaRegistry meta, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new CustomValidationException("操作失败！",bindingResult.getFieldErrors());
        }
        if(meta.getId()==null) {
            if(metadataSchemaRegistryService.GetByCode(meta.getCode()) != null)
                throw new CustomException("操作失败！","元数据类型编码已经被占用，请重新输入！");
            metadataSchemaRegistryService.Insert(meta);
        }else {
            MetadataSchemaRegistry metadata = metadataSchemaRegistryService.GetByCode(meta.getCode());
            if(metadata !=null && metadata.getId().equals(meta.getId()))
                throw new CustomException("操作失败！","元数据类型编码已经被占用，请重新输入！");
            metadata = metadataSchemaRegistryService.GetById(meta.getId());
            if(metadata == null)
                throw new CustomException("操作失败！","元数据类型不存在或者已经被删除！");
            metadata.setName(meta.getName());
            metadataSchemaRegistryService.Update(metadata);
        }
        return Success("操作成功！",null);
    }

    @RequestMapping("/Delete/{id}")
    @ResponseBody
    public Map<String,Object> Delete(@PathVariable Integer id){
        metadataSchemaRegistryService.Delete(id);//此处异常怎么办，exception
        return Success("操作成功！",null);
    }
}
