package com.tongji.bwm.service.Basic;

import com.tongji.bwm.entity.Basic.Administrator;

import java.util.List;

public interface IAdministratorService<T> {

    T Insert(Administrator administrator);

    Administrator GetById(T id);

    Administrator GetByLoginName(String loginName);

    void Update(Administrator administrator);

    void Delete(Administrator administrator);

    void Delete(T id);

    List<Administrator> GetAll();

}
