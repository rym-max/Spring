package com.tongji.bwm.web.ERMS;

import com.tongji.bwm.entity.ERMS.MetadataFieldRegistry;
import com.tongji.bwm.entity.ERMS.MetadataSchemaRegistry;
import com.tongji.bwm.filters.CustomException;
import com.tongji.bwm.filters.validation.CustomValidationException;
import com.tongji.bwm.pojo.FilterCondition.FilterCondition;
import com.tongji.bwm.service.ERMS.MetadataFieldRegistryService;
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

import java.beans.Transient;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ERMS/MetadataFieldRegistry")
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ARTICLE')")
public class MetadataFieldRegistryController extends BaseController {
    
    @Autowired
    private MetadataFieldRegistryService metadataFieldRegistryService;

    @Autowired
    private MetadataSchemaRegistryService metadataSchemaRegistryService;

    @RequestMapping(value = {"/","/index.html"})
    public ModelAndView index(){
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("location","FIELD");
        return new ModelAndView("/ERMS/MetadataFieldRegistry/Index",
                modelMap);
    }

    @RequestMapping("/Search")
    @ResponseBody
    public List<MetadataFieldRegistry> Search(FilterCondition filterCondition){

        List<MetadataFieldRegistry> list = metadataFieldRegistryService.GetList(filterCondition);
//        for(int i=0;i<list.size();i++){
//            list.get(i).refresh();
//        }
        return list;
    }

    @RequestMapping(value = {"/Edit/{id}","/Edit/","/Edit"})
    public ModelAndView Edit(@PathVariable(required = false) Integer id){
        if(id==null){
            return new ModelAndView("/ERMS/MetadataFieldRegistry/Edit",
                    "mdfr",new MetadataFieldRegistry());
        }
        return new ModelAndView("/ERMS/MetadataFieldRegistry/Edit",
                "mdfr",metadataFieldRegistryService.GetById(id));
    }

    @RequestMapping(value = "/edit",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> edit(@RequestBody @Validated MetadataFieldRegistry meta, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new CustomValidationException("操作失败！",bindingResult.getFieldErrors());
        }

        if(meta.getMetadataSchemaId()==null){
            throw new CustomException("操作失败！","请确认元数据！");
        }else {
            MetadataSchemaRegistry schema = metadataSchemaRegistryService.GetById(meta.getMetadataSchemaId());
            if(schema==null)
                throw new CustomException("操作失败！","该元数据不存在！");
            meta.setOwnerMetadataSchemaRegistry(schema);
        }
        //先判断修饰
        MetadataFieldRegistry byFields;
        if(meta.getQualifier().isEmpty()){
            byFields = metadataFieldRegistryService.GetByME(meta.getOwnerMetadataSchemaRegistry().getId(),meta.getElement());
        }else {
            byFields = metadataFieldRegistryService.GetByMEQ(meta.getOwnerMetadataSchemaRegistry().getId(),meta.getElement(),meta.getQualifier());
        }


        if(meta.getId()==null){

            if(byFields!=null)
                throw new CustomException("操作失败！", "元数据已经被占用，请重新输入！");

            metadataFieldRegistryService.Insert(meta);
        }else {

            if(byFields!=null && byFields.getId().equals(meta.getId()))
                throw new CustomException("操作失败！", "元数据已经被占用，请重新输入！");
            if(metadataFieldRegistryService.GetById(meta.getId()) == null)
                throw new CustomException("操作失败！", "元数据不存在或者已经删除！");

            metadataFieldRegistryService.Update(meta);
        }

        return Success("操作成功！",null);
    }

    @RequestMapping("/Delete/{id}")
    @ResponseBody
    public Map<String,Object> Delete(@PathVariable Integer id){
        metadataFieldRegistryService.Delete(id);//此处异常怎么办，exception
        return Success("操作成功！",null);
    }
}
