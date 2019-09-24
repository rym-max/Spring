package com.tongji.bwm.web.ERMS;

import com.tongji.bwm.entity.ERMS.Category;
import com.tongji.bwm.filters.CustomException;
import com.tongji.bwm.filters.validation.CustomValidationException;
import com.tongji.bwm.pojo.FilterCondition.FilterCondition;
import com.tongji.bwm.service.ERMS.CategoryService;
import com.tongji.bwm.service.ERMS.ChannelService;
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

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/ERMS/Category")
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ARTICLE')")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ChannelService channelService;

    @RequestMapping(value = {"/","/index.html"})
    public ModelAndView index(){
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("location","CATEGORY");
        return new ModelAndView("/ERMS/Category/Index",
                modelMap);
    }

    @RequestMapping("/Search")
    @ResponseBody
    public List<Category> Search(@RequestBody FilterCondition filterCondition){

        if(filterCondition==null)
            return categoryService.GetAll();
        log.info("看一眼查询条件");
        filterCondition.filter.forEach(
                s->{
                    log.info(s.name+"----------"+s.value);
                }
        );
        List<Category> list = categoryService.GetList(filterCondition);
//        List<Category> list1 = new ArrayList<>();
//        //循环判断parentId==0
//        for(Category cate: list){
//            if(cate.getOwnerCategory().getId()!=0){
//                cate.setOwnerCategory(categoryService.GetById(cate.getOwnerCategory().getId()));
//            }
//            cate.setOwnerChannel(channelService.GetById(cate.getOwnerChannel().getId()));
//            list1.add(cate);
//        }
//        //直接返回json_string???待考证

        return list;
    }

    @RequestMapping(value = {"/Edit/{id}","/Edit/","/Edit"})
    public ModelAndView Edit(@PathVariable(required = false) Integer id){
        if(id==null){
            return new ModelAndView("/ERMS/Category/Edit",
                    "category",new Category());
        }
        return new ModelAndView("/ERMS/Category/Edit",
                "category",categoryService.GetById(id));
    }

    @RequestMapping(value = "/edit",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> edit(@RequestBody @Validated Category cate, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new CustomValidationException("操作失败！",bindingResult.getFieldErrors());
        }
        //设置category和channel
        cate.setOwnerCategory(categoryService.GetById(cate.getParentId()));
        cate.setOwnerChannel(channelService.GetById(cate.getChannelId()));
        if(cate.getId()==null){
            if(categoryService.GetByCode(cate.getCode())!=null)
                throw new CustomException("操作失败！","编码已经被占用，请重新输入！");

            categoryService.Insert(cate);

        }else {
            Category category = categoryService.GetByCode(cate.getCode());
            if (category != null && !category.getId().equals(cate.getId()))
                throw new CustomException("操作失败！", "编码已经被占用，请重新输入！");
            category = categoryService.GetById(cate.getId());
            if (category == null)
                throw new CustomException("操作失败！", "分类不存在或者已经被删除！");
            //要保证一些不可被修改???那有啥意义
            //前台传入的参数有限，需要单独设置
            category.setName(cate.getName());
            category.setOwnerChannel(channelService.GetById(cate.getChannelId()));
            category.setOwnerCategory(categoryService.GetById(cate.getParentId()));
            category.setCode(cate.getCode());
            category.setDescription(cate.getDescription());
            category.setQueryExpression(cate.getQueryExpression());
            category.setSort(cate.getSort());
            categoryService.Update(category);
        }
        return Success("操作成功！",null);
    }

    @RequestMapping("/Delete/{id}")
    @ResponseBody
    public Map<String,Object> Delete(@PathVariable Integer id){
        categoryService.Delete(id);//此处异常怎么办，exception
        return Success("操作成功！",null);
    }    

    @RequestMapping(value = {"/Metadata","/metadata.html"})
    public ModelAndView Metadata(){
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("location","CATEGORYMETA");
        return new ModelAndView("/ERMS/Category/Metadata",
                modelMap);
    }

}
