package com.tongji.bwm.entity.Spider;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tongji.bwm.entity.Basic.PKINTEntity;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import com.tongji.bwm.pojo.Enum.Scrapy.ScrapyEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name = "SPIDER_Item")
@JsonIgnoreProperties(value = {"lastAction","status","creator","lastJob","lastActionTime","lastActionUser","lastResult"},
        ignoreUnknown = true,
        allowGetters = true)
@DynamicUpdate
@DynamicInsert
public class SpiderItem extends PKINTEntity {

    @NotBlank
    @Size(max = 50,message = "爬虫名字长度不能超过50个字符！")
    private String name;

    @ManyToOne
    @JoinColumn(name = "ConfigId",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT),updatable = false,insertable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private SpiderConfig ownerSpiderConfig;

    @Column(name = "ConfigId",nullable = false)
    private Integer configId;

    @Enumerated(EnumType.ORDINAL)
    private CommonEnum.AvailableEnum isOpen;

    @Enumerated(EnumType.ORDINAL)
    private ScrapyEnum.StatusEnum status;

    private String creator;

    private String lastJob;

    @Enumerated(EnumType.STRING)
    private ScrapyEnum.ActionEnum lastAction;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastActionTime;

    @Column(name = "LastCrawlDate",insertable = false,updatable = false)
    private Date lastCrawlDate;

    private String lastActionUser;

    private String lastResult;

    @Enumerated(EnumType.ORDINAL)
    private ScrapyEnum.SpiderType type;

    private Integer interval;

    private Integer times;

    private String customSettings;
}
