package com.tongji.bwm.service.Book;

import com.tongji.bwm.entity.Book.Journal;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IJournalService<T> {

    T Insert(Journal journal);

    Journal GetById(T id);

    Journal GetByGCH(String gch);

    void Update(Journal journal);

    void Delete(Journal journal);

    void Delete(T id);

    List<Journal> GetList(Example<Journal> example);

    Page<Journal> GetAll(Example<Journal> example, Pageable pageable);
}
