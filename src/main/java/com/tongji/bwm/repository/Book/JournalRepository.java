package com.tongji.bwm.repository.Book;

import com.tongji.bwm.entity.Book.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal,Integer> {

    Journal findByGCH(String GCH);

    Journal findByName(String Name);
}
