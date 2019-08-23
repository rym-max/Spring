package com.tongji.bwm.solr.Client.Models;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class SearchResult<T> {

    @JSONField
    private int status;
    @JSONField
    private int QTime;
    @JSONField
    private int numFound;
    @JSONField
    private int start;
    @JSONField
    private int rows;
    @JSONField
    private List<Document<T>> docs;
}
