package com.tongji.bwm.entity.Log;

import com.tongji.bwm.entity.Basic.PKINTEntity;
import com.tongji.bwm.pojo.Enum.Scrapy.ScrapyEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Log_SpiderLog")
public class Spider_Log extends PKINTEntity {

    private String spider;

    private String actionUser;

    private String ip;

    @Enumerated(EnumType.STRING)
    private ScrapyEnum.ActionEnum action;

    private String result;

}
