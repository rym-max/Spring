package com.tongji.bwm.repository.Spider;

import com.tongji.bwm.entity.Spider.SpiderConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpiderConfigRepository extends JpaRepository<SpiderConfig,Integer> {

    SpiderConfig findByName(String name);
}
