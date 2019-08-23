package com.tongji.bwm.service.ERMS;

import com.tongji.bwm.entity.ERMS.Category;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ICategoryService<T> {

    T Insert(Category category);

    Category GetById(T id);

    Category GetByCode(String code);

    void Update(Category category);

    void Delete(Category category);

    void Delete(T id);

    List<Category> GetAll();

    List<Category> GetList(Example<Category> example);

    List<Category> GetList(Example<Category> example, Sort sort);

}
