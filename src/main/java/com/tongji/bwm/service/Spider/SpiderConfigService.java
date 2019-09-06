package com.tongji.bwm.service.Spider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongji.bwm.entity.Spider.Config;
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
    public Integer Insert(Config config) {
        return spiderConfigRepository.save(config).getId();
    }

    @Override
    public Config GetById(Integer id) {
        Optional<Config> optional = spiderConfigRepository.findById(id);
        return optional.isPresent()?optional.get():null;
    }

    @Override
    public void Update(Config config) {
        spiderConfigRepository.save(config);
    }

    @Override
    public void Delete(Config config) {
        spiderConfigRepository.delete(config);
    }

    @Override
    public void Delete(Integer id) {
        spiderConfigRepository.deleteById(id);
    }

    @Override
    public List<Config> GetAll() {
        return spiderConfigRepository.findAll();
    }

    @Override
    public Config GetByName(String name) {
        return spiderConfigRepository.findByName(name);
    }

    @Override
    public Page<Config> GetPageList(Example<Config> example, Pageable pageable){
        return spiderConfigRepository.findAll(example,pageable);
    }


    public Pagination<Config> GetPageList(FilterCondition filterCondition){
        Pageable pageable = FilterEntityUtils.getPageable(filterCondition);
        Page<Config> page = spiderConfigRepository.findAll(pageable);
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
