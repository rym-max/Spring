package com.tongji.bwm.pojo.SpiderLog;

import lombok.Data;

@Data
public class SpiderResult {

    private String action;
    private Integer pageCount;
    private Integer itemCount;
    private String day;

    public SpiderResult(String action, Integer pageCount, Integer itemCount, String day) {
        this.action = action;
        this.pageCount = pageCount;
        this.itemCount = itemCount;
        this.day = day;
    }

    public SpiderResult() {
    }
}
