package com.tongji.bwm.service.Spider;

import com.tongji.bwm.entity.Spider.Config;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISpiderConfigService<T> {

    T Insert(Config config);

    Config GetById(T id);

    void Update(Config config);

    void Delete(Config config);

    void Delete(T id);

    List<Config> GetAll();

    Config GetByName(String name);

    Page<Config> GetPageList(Example<Config> example, Pageable pageable);
}
