package com.tongji.bwm.service.Log;

import com.tongji.bwm.pojo.AccessLog.CountByDay;
import com.tongji.bwm.pojo.AccessLog.IpCount;
import com.tongji.bwm.entity.Log.Access_Log;

import java.util.List;

public interface IAccessLogService<T> {

    T Insert(Access_Log accessLog);

    Access_Log GetById(T id);

    void Update(Access_Log accessLog);

    void Delete(Access_Log accessLog);

    void Delete(T id);

    List<Access_Log> GetAll();

    List<CountByDay> GetEachDayAccessByMonth(int year, int month);

    List<IpCount> GetIpCount(int year, int month);
}
