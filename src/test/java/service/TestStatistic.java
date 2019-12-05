package service;

import com.tongji.bwm.Application;
import com.tongji.bwm.solr.Client.SolrStatistic;
import com.tongji.bwm.utils.DateFormatterUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author starcloud
 * @date 2019/12/04
 **/

@Slf4j
@SpringBootTest(classes = {Application.class})
public class TestStatistic extends AbstractTestNGSpringContextTests {

    @Autowired
    private SolrStatistic solrStatistic;

    @Test
    public void TestSolrStatistic(){
        log.info("开始Test");
        log.info("初始化参数");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String query = "china";

        Integer last_date = null;
        Date start_date = null;
        Date end_date = null;
        try {
            start_date = simpleDateFormat.parse("2017-11-28");
            end_date = simpleDateFormat.parse("2017-12-04");
        }catch (Exception e){
            log.info("日期出错");
            last_date = 7;
        }

        log.info("初始化查询参数");
        //先判断last date 是否存在
        if(last_date!=null){
            Date current_date = new Date();
            Date past_date = DateFormatterUtils.getPastDate(last_date);

            start_date = past_date;
            end_date = current_date;
        }else{
            last_date = DateFormatterUtils.getPeriod(start_date,end_date);
        }

        String s_date = simpleDateFormat.format(start_date);
        String e_date = simpleDateFormat.format(end_date);

        //查询参数
        String q = "dateissued_ss:[" + s_date +" TO " + e_date +"] ";
        log.info("查询日期：<<<<<"+q);

        String[] q_li = query.split("\\s+");
        for(int i =0;i<q_li.length;i++){
            q += " AND (description: \""+q_li[i]+"\" ";
            q += " OR subject:\""+q_li[i]+"\" ";
            q += " OR title:\""+q_li[i]+"\" )";
        }

        log.info("开始测试");

        Map<String, Object> map = new HashMap<>();
        try {
            map = solrStatistic.oneFacet(q,"dateissued_date",1,last_date,"index");
        }catch (Exception e){
            log.error(e.getMessage());
            log.error("查询获取facet错误！");
        }

        log.info("成功了");
        log.info(map.toString());
    }
}
