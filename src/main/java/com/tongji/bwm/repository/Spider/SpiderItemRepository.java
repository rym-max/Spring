package com.tongji.bwm.repository.Spider;

import com.tongji.bwm.entity.Spider.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpiderItemRepository extends JpaRepository<Item,Integer> {

    @Query(
            value = "SELECT * FROM SPIDER_Item WHERE ConfigId = :configId",
            nativeQuery = true
    )
    List<Item> findByConfigId(Integer configId);

    List<Item> findAllByIsOpen(Boolean isOpen);

}
