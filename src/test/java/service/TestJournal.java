package service;


import com.tongji.bwm.Application;
import com.tongji.bwm.entity.Book.Journal;
import com.tongji.bwm.pojo.FilterCondition.Field;
import com.tongji.bwm.pojo.FilterCondition.FilterCondition;
import com.tongji.bwm.pojo.Pagination;
import com.tongji.bwm.repository.Book.JournalRepository;
import com.tongji.bwm.service.Book.JournalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
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
}
