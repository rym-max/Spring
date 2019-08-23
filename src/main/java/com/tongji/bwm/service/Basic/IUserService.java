package com.tongji.bwm.service.Basic;

import com.tongji.bwm.entity.Basic.User;

import java.util.List;

public interface IUserService<T> {

    T Insert(User user);

    User GetById(T id);

    User GetByLoginName(String loginName);

    void Update(User user);

    void Delete(User user);

    void Delete(T id);

    List<User> GetAll();

}
