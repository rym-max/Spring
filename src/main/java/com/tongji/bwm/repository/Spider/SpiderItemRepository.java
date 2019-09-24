package com.tongji.bwm.repository.Spider;

import com.tongji.bwm.entity.Spider.SpiderItem;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpiderItemRepository extends JpaRepository<SpiderItem,Integer> {

    @Query(
            value = "SELECT * FROM SPIDER_Item WHERE ConfigId = :configId",
            nativeQuery = true
    )
    List<SpiderItem> findByConfigId(@Param(value = "configId") Integer configId);

    List<SpiderItem> findAllByIsOpen(CommonEnum.AvailableEnum isOpen);

    SpiderItem findByName(String name);

}
