package com.tongji.bwm.solr.Models;

import lombok.Data;

import java.util.Date;

@Data
public class TaskInfo {

    private TaskTypeEnum taskType;

    private Date StartTime;

    private Date EndTime;

    private Integer CompleteCount;

    private Integer SkipCount;

    private Integer ErrorCount;

    private Long TotalCount;

    private Double Percent;

    private Integer Status;

    private String error;

    private String DescInfo;
}
