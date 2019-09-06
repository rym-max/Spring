package com.tongji.bwm.solr.Models;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class ClusterResult {
    @JSONField
    private String Name;
    @JSONField
    private int Count;

    public ClusterResult(String name, int count) {
        Name = name;
        Count = count;
    }

    public ClusterResult() {
    }
}
