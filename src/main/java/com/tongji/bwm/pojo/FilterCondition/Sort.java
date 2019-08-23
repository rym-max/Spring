package com.tongji.bwm.pojo.FilterCondition;

import com.alibaba.fastjson.annotation.JSONField;

public class Sort {

    @JSONField
    public String name;
    @JSONField
    public Boolean asc;

    public Sort() {
    }

    public Sort(String name, Boolean asc) {
        this.name = name;
        this.asc = asc;
    }
}
