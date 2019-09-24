package com.tongji.bwm.service.Spider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongji.bwm.entity.Spider.SpiderConfig;
import com.tongji.bwm.pojo.FilterCondition.FilterCondition;
import com.tongji.bwm.pojo.Pagination;
import com.tongji.bwm.repository.Spider.SpiderConfigRepository;
import com.tongji.bwm.utils.FilterEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpiderConfigService implements ISpiderConfigService<Integer> {
    @Autowired
    private SpiderConfigRepository spiderConfigRepository;

    @Override
    public Integer Insert(SpiderConfig spiderConfig) {
        return spiderConfigRepository.save(spiderConfig).getId();
    }

    @Override
    public SpiderConfig GetById(Integer id) {
        Optional<SpiderConfig> optional = spiderConfigRepository.findById(id);
        return optional.isPresent()?optional.get():null;
    }

    @Override
    public void Update(SpiderConfig spiderConfig) {
        spiderConfigRepository.save(spiderConfig);
    }

    @Override
    public void Delete(SpiderConfig spiderConfig) {
        spiderConfigRepository.delete(spiderConfig);
    }

    @Override
    public void Delete(Integer id) {
        spiderConfigRepository.deleteById(id);
    }

    @Override
    public List<SpiderConfig> GetAll() {
        return spiderConfigRepository.findAll();
    }

    @Override
    public SpiderConfig GetByName(String name) {
        return spiderConfigRepository.findByName(name);
    }

    @Override
    public Page<SpiderConfig> GetPageList(Example<SpiderConfig> example, Pageable pageable){
        return spiderConfigRepository.findAll(example,pageable);
    }


    public Pagination<SpiderConfig> GetPageList(FilterCondition filterCondition){
        Pageable pageable = FilterEntityUtils.getPageable(filterCondition);
        Page<SpiderConfig> page = spiderConfigRepository.findAll(pageable);
        return FilterEntityUtils.getPagination(page);
    }

    public Boolean IsJsonStr(String jsonStr){
        try{
            Object testjson = JSON.parse(jsonStr);
            return (testjson instanceof JSONObject);
        }catch (Exception e){
            return false;
        }
    }
}
