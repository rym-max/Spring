package com.tongji.bwm.pojo.FilterCondition;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FilterCondition implements Serializable
{


    @JSONField
    public int top;
    @JSONField
    public int page;
    @JSONField
    public int rows;
    @JSONField
    public String fields;
    @JSONField
    public List<Sort> sort;
    @JSONField
    public List<Field> filter;
    @JSONField
    public List<Field> filterGroupAnd;
    @JSONField
    public List<Field> filterGroupOr;



    public FilterCondition()
    {
        this.top = 0;
        this.page = 1;
        this.rows = 10;
        this.fields = "*";
        this.filter = new ArrayList<>();
        this.filterGroupAnd = new ArrayList<>();
        this.filterGroupOr = new ArrayList<>();
    }


}
