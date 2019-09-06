package com.tongji.bwm.pojo.SpiderLog;

import lombok.Data;

@Data
public class UserCount {

    private String user;
    private String action;
    private String spider;
    private Integer count;

    public UserCount() {
    }

    public UserCount(String user, String action, String spider, Integer count) {
        this.user = user;
        this.action = action;
        this.spider = spider;
        this.count = count;
    }
}
