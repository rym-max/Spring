package com.tongji.bwm.service.Log;

import com.tongji.bwm.entity.Log.Spider_Log;

import java.util.List;

public interface ISpiderLogService<T> {

    T Insert(Spider_Log spider_log);

    Spider_Log GetById(T id);

    void Update(Spider_Log spider_log);

    void Delete(Spider_Log spider_log);

    void Delete(T id);

    List<Spider_Log> GetAll();


}
