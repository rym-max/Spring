package com.tongji.bwm.entity.Spider;

import com.tongji.bwm.entity.Basic.PKINTEntity;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import com.tongji.bwm.pojo.Enum.Scrapy.ScrapyEnum;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name = "SPIDER_Item")
public class Item extends PKINTEntity {

    @NotBlank
    @Size(max = 50,message = "爬虫名字长度不能超过50个字符！")
    private String name;

    @ManyToOne
    @JoinColumn(name = "ConfigId")
    private Config ownerConfig;
    @Transient
    private Integer ConfigId;

    @Enumerated(EnumType.ORDINAL)
    private CommonEnum.AvailableEnum isOpen;

    @Enumerated(EnumType.ORDINAL)
    private ScrapyEnum.StatusEnum status;

    private String creator;

    private String lastJob;

    @Enumerated(EnumType.STRING)
    private ScrapyEnum.ActionEnum lastAction;

    private Date lastActionTime;

    private String lastActionUser;

    private String lastResult;

    @Enumerated(EnumType.ORDINAL)
    private ScrapyEnum.SpiderType type;

    private Integer interval;

    private Integer times;

    private String customSettings;
}
