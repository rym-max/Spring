package com.tongji.bwm.service.Log;

import com.tongji.bwm.pojo.CountByDay;
import com.tongji.bwm.pojo.IpCount;
import com.tongji.bwm.entity.Log.Access_Log;
import com.tongji.bwm.repository.Log.AccessLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccessLogService implements IAccessLogService<Integer>{

    @Autowired
    private AccessLogRepository accessLogRepository;

    @Override
    public Integer Insert(Access_Log accessLog) {
        return accessLogRepository.save(accessLog).getId();
    }

    @Override
    public Access_Log GetById(Integer id) {
        Optional<Access_Log> optional = accessLogRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    @Override
    public void Update(Access_Log accessLog) {
        accessLogRepository.save(accessLog);
    }

    @Override
    public void Delete(Integer id) {
        accessLogRepository.deleteById(id);
    }

    @Override
    public void Delete(Access_Log accessLog) {
        accessLogRepository.delete(accessLog);
    }

    @Override
    public List<Access_Log> GetAll() {
        return accessLogRepository.findAll();
    }

    @Override
    public List<CountByDay> GetEachDayAccessByMonth(int year, int month) {
        List<Object[]> list1= accessLogRepository.GetEachDayAccessByMonth(year-1,month);//测试用
        List<CountByDay> list2 = new ArrayList<>();
        if(list1==null || list1.size()==0)
            return list2;

        for(Object[] li:list1){
            list2.add(new CountByDay((Integer)li[0],(Integer)li[1],(Integer)li[2],(Integer)li[3]));
        }
        return list2;
    }

    @Override
    public List<IpCount> GetIpCount(int year, int month) {
        List<Object[]> list1= accessLogRepository.GetIpCount(year-1,month);//测试用
        List<IpCount> list2 = new ArrayList<>();
        if(list1==null || list1.size()==0)
            return list2;

        for(Object[] li: list1){
            list2.add(new IpCount((String)li[0],(Integer)li[1]));
        }

        return list2;
    }
}
