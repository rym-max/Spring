package com.tongji.bwm.service.Spider;

import com.tongji.bwm.entity.Spider.Item;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ISpiderItemService<T> {
    T Insert(Item item);

    Item GetById(T id);

    void Update(Item item);

    void Delete(Item item);

    void Delete(T id);

    List<Item> GetAll();

    List<Item> findByConfigId(Integer configId);

    List<Item> findAllIsOpen(Boolean isOpen);

    List<Item> GetList(Example<Item> example);

    List<Item> GetList(Example<Item> example, Sort sort);

    Page<Item> GetList(Example<Item> example, Pageable pageable);
}
