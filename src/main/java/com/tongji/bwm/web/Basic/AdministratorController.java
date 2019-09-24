package com.tongji.bwm.web.Basic;

import com.tongji.bwm.entity.Basic.Administrator;
import com.tongji.bwm.filters.CustomException;
import com.tongji.bwm.filters.validation.CustomValidationException;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import com.tongji.bwm.service.Basic.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/Administrator")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdministratorController extends BaseController{


    @Autowired
    AdministratorService administratorService;

    @RequestMapping(value = {"/index.html","/"})
    public ModelAndView index(){
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("location","ADMINISTRATOR");
        return new ModelAndView("/Administrator/Index",
                modelMap);
    }

    @RequestMapping(value = {"/Search","/Search/"})
    @ResponseBody
    public Map<String,Object> Search(){
        return Success("操作成功！",administratorService.GetAll());
    }

    @RequestMapping(value = {"/Edit/{id}","/Edit/","/Edit"})
    public ModelAndView Edit(@PathVariable(required = false) Integer id){
        ModelMap modelMap = new ModelMap();
        //先获得枚举列表
        CommonEnum.RoleType[] roles = CommonEnum.RoleType.values();
        modelMap.addAttribute("roles",roles);

        if(id==null){
            modelMap.addAttribute("administrator",new Administrator());
        }else {
            modelMap.addAttribute("administrator",administratorService.GetById(id));
        }
        return new ModelAndView("/Administrator/Edit",
                modelMap);
    }

    @RequestMapping(value = "/edit",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> edit(@RequestBody @Validated Administrator admin, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new CustomValidationException("操作失败！",bindingResult.getFieldErrors());
        }
        if(admin.getId()==null){
            if(administratorService.GetByLoginName(admin.getLoginName())!=null)
                throw new CustomException("操作失败！","登录名已经被占用，请重新输入！");
            administratorService.Insert(admin);

        }else {
            Administrator administrator = administratorService.GetByLoginName(admin.getLoginName());
            if (administrator != null && !administrator.getId().equals(admin.getId()))
                throw new CustomException("操作失败！", "登录名已经被占用，请重新输入！");
            administrator = administratorService.GetById(admin.getId());
            if (administrator == null)
                throw new CustomException("操作失败！", "管理员不存在或者已经被删除！");
            administrator.setLoginPassword(admin.getLoginPassword());
            administrator.setRole(admin.getRole());
            administratorService.Update(administrator);
        }
        return Success("操作成功！",null);
    }

    @RequestMapping("/Delete/{id}")
    @ResponseBody
    public Map<String,Object> Delete(@PathVariable Integer id){
        if(id==1)
            throw new CustomException("操作失败！","admin账号不可删除！");
        administratorService.Delete(id);//此处异常怎么办，exception
        return Success("操作成功！",null);
    }

}
