package com.tongji.bwm.service.Spider;

import com.tongji.bwm.entity.Spider.SpiderItem;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ISpiderItemService<T> {
    T Insert(SpiderItem spiderItem);

    SpiderItem GetById(T id);

    SpiderItem GetByName(String name);

    void Update(SpiderItem spiderItem);

    void Delete(SpiderItem spiderItem);

    void Delete(T id);

    List<SpiderItem> GetAll();

    List<SpiderItem> findByConfigId(Integer configId);

    List<SpiderItem> GetList(Example<SpiderItem> example);

    List<SpiderItem> GetList(Example<SpiderItem> example, Sort sort);

    Page<SpiderItem> GetList(Example<SpiderItem> example, Pageable pageable);

    List<SpiderItem> findAllIsOpen(CommonEnum.AvailableEnum isOpen);
}
