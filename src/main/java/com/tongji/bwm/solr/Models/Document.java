package com.tongji.bwm.solr.Models;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class Document<T> {

    @JSONField
    private T id;
}
