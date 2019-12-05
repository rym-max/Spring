package com.tongji.bwm.web.Front;

import com.tongji.bwm.entity.Book.Journal;
import com.tongji.bwm.pojo.FilterCondition.FilterCondition;
import com.tongji.bwm.pojo.Pagination;
import com.tongji.bwm.service.Book.JournalService;
import com.tongji.bwm.web.Basic.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author starcloud
 * @date 2019/11/09
 **/
@RequestMapping({"/Home/JournalList"})
@Controller
public class JournalListController extends BaseController {

    @Autowired
    private JournalService journalService;

    @RequestMapping("/")
    public ModelAndView index(){
        return new ModelAndView("/Front/journalList1");
    }


    @ResponseBody
    @RequestMapping("/Search")
    public Map<String,Object> search(@RequestBody FilterCondition filterCondition){
        Pagination<Journal> pagination = journalService.GetPageList(filterCondition);
        return Success("访问成功",pagination);
    }
}
