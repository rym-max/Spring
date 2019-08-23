package com.tongji.bwm.service.ERMS;

import com.tongji.bwm.entity.ERMS.Channel;

import java.util.List;

public interface IChannelService<T> {

    T Insert(Channel channel);

    Channel GetById(T id);

    Channel GetByCode(String code);

    void Update(Channel channel);

    void Delete(Channel channel);

    void Delete(T id);

    List<Channel> GetAll();
}
