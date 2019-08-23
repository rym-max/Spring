package com.tongji.bwm.pojo.FilterCondition;

import com.alibaba.fastjson.annotation.JSONField;

public class Field {
    @JSONField
    public String name;
    @JSONField
    public String value;
    @JSONField
    public String exp;
    @JSONField
    public String oper;
    @JSONField
    public Boolean range;
    @JSONField
    public String type;
    @JSONField
    public String start;
    @JSONField
    public String end;


    public Field()
    {
        this.exp = "=";
        this.range = false;
        this.type = "string";
        this.oper = "and";
    }

    public Field(String name, String value) {
        this.name = name;
        this.value = value;
        this.exp = "=";
        this.range = false;
        this.type = "string";
        this.oper = "and";
    }
}
