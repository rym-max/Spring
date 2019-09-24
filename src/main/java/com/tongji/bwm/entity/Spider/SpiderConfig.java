package com.tongji.bwm.entity.Spider;

import com.tongji.bwm.entity.Basic.PKINTEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "SPIDER_Config")
public class SpiderConfig extends PKINTEntity {

    private String name;

    private String configs;

    private String rules;

    private String creator;

    private String project;

    private String spider;
}
