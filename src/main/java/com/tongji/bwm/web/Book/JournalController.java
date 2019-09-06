package com.tongji.bwm.web.Book;

import com.tongji.bwm.pojo.Pagination;
import com.tongji.bwm.entity.Book.Journal;
import com.tongji.bwm.entity.ERMS.Item;
import com.tongji.bwm.filters.CustomException;
import com.tongji.bwm.filters.validation.CustomValidationException;
import com.tongji.bwm.pojo.FilterCondition.FilterCondition;
import com.tongji.bwm.service.Book.JournalService;
import com.tongji.bwm.service.ERMS.ItemService;
import com.tongji.bwm.solr.Models.ClusterResult;
import com.tongji.bwm.solr.Client.SolrConfig;
import com.tongji.bwm.web.Basic.BaseController;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Book/Journal")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class JournalController extends BaseController {
 
    @Autowired
    private JournalService journalService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private SolrConfig solrConfig;

    @RequestMapping(value = {"/","/index.html"})
    public ModelAndView index(){
        return new ModelAndView("/Book/Journal/Index");
    }

    @RequestMapping("/Search")
    @ResponseBody
    public Map<String,Object> Search(@RequestBody FilterCondition filterCondition){
        return Success("操作成功！",journalService.GetPageList(filterCondition));
    }

    @RequestMapping(value = {"/Edit/{id}","/Edit/","/Edit"})
    public ModelAndView Edit(@PathVariable(required = false) Integer id){
        if(id==null){
            return new ModelAndView("/Book/Journal/Edit",
                    "journal",new Journal());
        }
        return new ModelAndView("/Book/Journal/Edit",
                "journal",journalService.GetById(id));
    }

    @RequestMapping(value = "/edit",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> edit(@RequestBody @Validated Journal j, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new CustomValidationException("操作失败！",bindingResult.getFieldErrors());
        }
        if(j.getId()==null){
            journalService.Insert(j);
        }else {
            Journal journal = journalService.GetById(j.getId());
            if (journal == null)
                throw new CustomException("操作失败！", "期刊不存在或者已经被删除！");
            j.setId(journal.getId());
            journalService.Update(j);
        }
        return Success("操作成功！",null);
    }

    @RequestMapping("/Delete/{id}")
    @ResponseBody
    public Map<String,Object> Delete(@PathVariable Integer id){
        journalService.Delete(id);//此处异常怎么办，exception
        return Success("操作成功！",null);
    }

    @RequestMapping("/StatisticsFwCount")
    @ResponseBody
    public Map<String,Object> StatisticsFwCount(){
        Map<String,Object> map = new HashMap<>();
        List<Pair<String,String>> parameters = new ArrayList<>();

        parameters.add(new Pair<String, String>("q", "*:*"));
        parameters.add(new Pair<String, String>("start", "0"));
        parameters.add(new Pair<String, String>("rows", "1"));
        parameters.add(new Pair<String, String>("sort", "ctime desc"));
        parameters.add(new Pair<String, String>("wt", "json"));
        parameters.add(new Pair<String, String>("facet", "on"));
        parameters.add(new Pair<String, String>("facet.field", "source_filter"));
        parameters.add(new Pair<String, String>("facet.mincount", "1"));
        Map<String,List<ClusterResult>> dictionary = new HashMap<>();
        try {
            Pagination<Item> pageList = itemService.GetPageList(solrConfig.getUrl(), parameters,dictionary);
        }catch (Exception e){
            throw new CustomException("操作失败！","Solr查询失败");
        }
        if(dictionary.containsKey("source_filter")){
            for(ClusterResult result : dictionary.get("source_filter")){
                Journal journal = journalService.GetByName(result.getName());
                if (journal!=null){
                    journal.setZpCount(result.getCount());
                    journalService.Update(journal);
                }
            }
        }
        return Success("统计成功",null);

    }
}
