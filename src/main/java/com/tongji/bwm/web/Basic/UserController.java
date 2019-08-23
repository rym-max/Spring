package com.tongji.bwm.web.Basic;

import com.tongji.bwm.entity.Basic.User;
import com.tongji.bwm.filters.CustomException;
import com.tongji.bwm.filters.validation.CustomValidationException;
import com.tongji.bwm.service.Basic.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/User")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/","/index.html"})
    public ModelAndView index(){
        return new ModelAndView("/User/Index");
    }

    @RequestMapping("/Search")
    @ResponseBody
    public Map<String,Object> Search(){
        return Success("操作成功！",userService.GetAll());
    }

    @RequestMapping(value = {"/Edit/{id}","/Edit","/Edit/"})
    public ModelAndView Edit(@PathVariable(required = false) Integer id){
        log.info("打印一下id"+id);
        ModelMap modelMap = new ModelMap();
        if(id==null){
            modelMap.addAttribute("user",new User());
        }else {
            modelMap.addAttribute("user",userService.GetById(id));
        }
        //打印一下model
        log.info("打印一下model"+modelMap.get("user").toString());
        return new ModelAndView("/User/Edit",modelMap);
    }

    @RequestMapping(value = "/edit")
    @ResponseBody
    public Map<String,Object> edit(@RequestBody @Validated User u, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new CustomValidationException("操作失败！",bindingResult.getFieldErrors());
        }
        if(u.getId()==null){
            if(userService.GetByLoginName(u.getLoginName())!=null)
                throw new CustomException("操作失败！","登录名已经被占用，请重新输入！");
            userService.Insert(u);

        }else {
            User user = userService.GetByLoginName(u.getLoginName());
            if (user != null && !user.getId().equals(u.getId()))
                throw new CustomException("操作失败！", "登录名已经被占用，请重新输入！");
            user = userService.GetById(u.getId());
            if (user == null)
                throw new CustomException("操作失败！", "用户不存在或者已经被删除！");
            user.setLoginPassword(u.getLoginPassword());
            user.setSex(u.getSex());
            user.setBirthday(u.getBirthday());
            user.setAvatar(u.getAvatar());
            user.setEmail(u.getEmail());
            user.setMobilePhoneNumber(u.getMobilePhoneNumber());
            user.setPhoneNumber(u.getPhoneNumber());
            user.setSummary(u.getSummary());
            userService.Update(user);
        }
        return Success("操作成功！",null);
    }

    @RequestMapping("/Delete/{id}")
    @ResponseBody
    public Map<String,Object> Delete(@PathVariable Integer id){
        userService.Delete(id);//此处异常怎么办，exception
        return Success("操作成功！",null);
    }
}
