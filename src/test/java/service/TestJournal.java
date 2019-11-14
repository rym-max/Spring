package service;


import com.tongji.bwm.Application;
import com.tongji.bwm.entity.Book.Journal;
import com.tongji.bwm.entity.ERMS.All;
import com.tongji.bwm.filters.CustomException;
import com.tongji.bwm.pojo.FilterCondition.Field;
import com.tongji.bwm.pojo.FilterCondition.FilterCondition;
import com.tongji.bwm.pojo.Pagination;
import com.tongji.bwm.repository.Book.JournalRepository;
import com.tongji.bwm.service.Book.JournalService;
import com.tongji.bwm.service.ERMS.AllService;
import com.tongji.bwm.solr.Models.ClusterResult;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

@Slf4j
@SpringBootTest(classes = {Application.class})
public class TestJournal extends AbstractTestNGSpringContextTests {

    @Autowired
    private JournalService journalService;

    @Autowired
    private JournalRepository journalRepository;

    @Test
    public void TestExample(){
        Journal j = new Journal();
        j.setName("");
        log.info(j.toString());

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name",ExampleMatcher.GenericPropertyMatchers.contains());


        Example<Journal> e = Example.of(j,matcher);

        List<Journal> li = journalRepository.findAll(e);
        assertNotNull(li);
        assertNotEquals(li.size(),0);
        for(Journal jj : li){
            log.info(jj.getName());
        }
    }

    @Test
    public void TestQUERY(){
        //新建一个filter
        FilterCondition filterCondition = new FilterCondition();

        List<Field> li = new ArrayList<>();
        li.add(new Field("Name",""));

        filterCondition.filter = li;
        filterCondition.page=1;
        filterCondition.rows=10;
        //查询
        Pagination<Journal> li2 = journalService.GetPageList(filterCondition);

        //看一眼结果
        for(Journal j : li2.getList()){
            log.info("期刊名字------"+j.getName());
            log.info("期刊ISSN-----"+j.getISSN());
        }
        log.info("页码"+li2.getPage());
        log.info("单页数量"+li2.getRows());
        log.info("总数"+li2.getTotal());
        log.info("页数"+li2.getPageCount());
    }

    @Autowired
    private AllService allService;


    @Value("${solrserver.core.tongji}")
    private String solrUrl;

    @Test
    public void TestStatisticCount(){




        //先来个参数
        Map<String,Object> map = new HashMap<>();
        List<Pair<String,String>> parameters = new ArrayList<>();

        parameters.add(new Pair<>("q", "*:*"));
        parameters.add(new Pair<>("start", "0"));
        parameters.add(new Pair<>("rows", "1"));
        parameters.add(new Pair<>("sort", "ctime desc"));
        parameters.add(new Pair<>("wt", "json"));
        parameters.add(new Pair<>("facet", "on"));
        parameters.add(new Pair<>("facet.field", "source_filter"));
        parameters.add(new Pair<>("facet.mincount", "1"));

        Map<String,List<ClusterResult>> dictionary = new HashMap<>();

        log.info("初步完成参数构建");

        try {
            Pagination<All> pageList = allService.GetPageList(solrUrl,parameters,dictionary);
        }catch (Exception e){
            log.error("出错了！");
            log.error(e.toString());
            log.error(e.getMessage());
        }
        if(dictionary.containsKey("source_filter")){
            for(ClusterResult result : dictionary.get("source_filter")){
                Journal journal = journalService.GetByName(result.getName());
                if (journal!=null){
                    log.info("NAME--------"+journal.getName());
                    log.info("ZPCOUNT-----"+journal.getZpCount());
                    journal.setZpCount(result.getCount());
                    journalService.Update(journal);
                }
            }
        }


    }

}
