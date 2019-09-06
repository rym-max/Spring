package com.tongji.bwm.service.Log;

import com.alibaba.fastjson.JSONObject;
import com.tongji.bwm.entity.Log.Spider_Log;
import com.tongji.bwm.entity.Spider.Item;
import com.tongji.bwm.pojo.Enum.Scrapy.ScrapyEnum;
import com.tongji.bwm.pojo.SpiderLog.SpiderResult;
import com.tongji.bwm.pojo.SpiderLog.UserCount;
import com.tongji.bwm.repository.Log.SpiderLogRepository;
import com.tongji.bwm.service.Spider.SpiderItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SpiderLogService implements ISpiderLogService<Integer> {

    @Autowired
    private SpiderLogRepository spiderLogRepository;

    @Autowired
    private SpiderItemService spiderItemService;

    @Override
    public Integer Insert(Spider_Log spider_log) {
        return spiderLogRepository.save(spider_log).getId();
    }

    @Override
    public Spider_Log GetById(Integer id) {
        Optional<Spider_Log> optional = spiderLogRepository.findById(id);
        return optional.isPresent()?optional.get():null;
    }

    @Override
    public void Update(Spider_Log spider_log) {
        spiderLogRepository.save(spider_log);
    }

    @Override
    public void Delete(Spider_Log spider_log) {
        spiderLogRepository.delete(spider_log);
    }

    @Override
    public void Delete(Integer id) {
        spiderLogRepository.deleteById(id);
    }

    @Override
    public List<Spider_Log> GetAll() {
        return spiderLogRepository.findAll();
    }

    public List<UserCount> GetUserCountBySpider(Integer SpiderId,String action){
        List<Object[]> list1 = spiderLogRepository.GetUserCountBySpider(SpiderId,action);

        List<UserCount> list2 = new ArrayList<>();
        if(list1==null || list1.size()==0)
            return list2;

        Item spiderItem = spiderItemService.GetById(SpiderId);
        String spiderName ="未知";
        if(spiderItem!=null)
            spiderName = spiderItem.getName();

        for(Object[] li: list1){
            list2.add(new UserCount((String)li[0],(String)li[1],spiderName,(Integer)li[2]));
        }

        return list2;
    }

    public List<SpiderResult> GetSpiderResults(Integer SpiderId,Integer Times){
        List<Object[]> list1 = spiderLogRepository.GetBySpider(SpiderId,Times);

        List<SpiderResult> list2 = new ArrayList<>();
        list1.forEach(
                s->{
                    if(((String)s[0]).equals("CRAWL_OVER") ||
                            ((String)s[0]).equals("ABORT")){

                        Integer[] count = getCount((String)s[1]);
                        list2.add(
                                new SpiderResult((String)s[0],count[0],count[1],
                                        (String)s[2]+"月"+(String)s[3]+"日")
                        );
                    }else{
                        list2.add(
                                new SpiderResult((String)s[0],0,0,
                                        (String)s[2]+"月"+(String)s[3]+"日")
                        );
                    }
                }
        );

        return list2;
    }

    private Integer[] getCount(String result){
        try {
            JSONObject rr = JSONObject.parseObject(result);

            JSONObject cc = rr.getJSONObject("result");

            Integer pageCount = cc.getInteger("crawl_page");
            Integer itemCount = cc.getInteger("crawl_item");

            return new Integer[]{pageCount,itemCount};
        }catch (Exception e){
            log.warn("获取爬虫日志出错");
            return new Integer[]{0,0};
        }

    }
}
