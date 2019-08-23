package com.tongji.bwm.filters;

import com.tongji.bwm.filters.validation.CustomValidationException;
import com.tongji.bwm.pojo.FrontAdmin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice(basePackages = {"com.tongji.bwm.web","com.tongji.bwm.solr.Client"})
public class CustomExceptionHandler {

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
//    }

//    @ModelAttribute
//    public void addAttribute(Model model) {
//        model.addAttribute("admin", frontAdmin);
//    }

    @ResponseBody
    @ExceptionHandler({CustomException.class})
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> customExceptionHandler(CustomException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", false);
        map.put("message", e.getCustomMessage());
        map.put("error", e.getCustomError());
        log.error("发生自定义错误："+e.getCustomError());
        return map;
    }

    @ResponseBody
    @ExceptionHandler({CustomValidationException.class})
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> customValidationExceptionHandler(CustomValidationException e){
        log.error("发生实体对象验证错误");
        return customExceptionHandler(e);
    }



    @ResponseBody
    @ExceptionHandler({EmptyResultDataAccessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> customEntityExceptionHandler(EmptyResultDataAccessException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", false);
        map.put("message", "操作失败！");
        map.put("error", "删除对象不存在！");
        log.error("删除对象不存在："+e.getMessage());
        return map;
    }
}