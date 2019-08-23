package com.tongji.bwm.service.ERMS;

import com.tongji.bwm.entity.ERMS.Achievement;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAchievementService<T> {

    String Insert(Achievement achievement);

    Achievement GetById(T id);

//    Achievement GetByCode(String code);

    void Update(Achievement achievement);

    void Delete(Achievement achievement);

    void Delete(T id);

    List<Achievement> GetAll();

    Page<Achievement> GetPageList(Example<Achievement> example, Pageable pageable);

    long Count(Example<Achievement> example);
}
