package com.tongji.bwm.service.Spider;

import com.tongji.bwm.entity.Spider.SpiderConfig;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISpiderConfigService<T> {

    T Insert(SpiderConfig spiderConfig);

    SpiderConfig GetById(T id);

    void Update(SpiderConfig spiderConfig);

    void Delete(SpiderConfig spiderConfig);

    void Delete(T id);

    List<SpiderConfig> GetAll();

    SpiderConfig GetByName(String name);

    Page<SpiderConfig> GetPageList(Example<SpiderConfig> example, Pageable pageable);
}
