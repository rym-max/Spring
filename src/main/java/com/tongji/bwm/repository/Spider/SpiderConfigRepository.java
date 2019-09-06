package com.tongji.bwm.repository.Spider;

import com.tongji.bwm.entity.Spider.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpiderConfigRepository extends JpaRepository<Config,Integer> {

    Config findByName(String name);
}
