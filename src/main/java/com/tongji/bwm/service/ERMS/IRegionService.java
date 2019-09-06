package com.tongji.bwm.service.ERMS;

import com.tongji.bwm.entity.ERMS.Region;

import java.util.List;

public interface IRegionService<T> {
    T Insert(Region region);

    Region GetById(T id);

    void Update(Region region);

    void Delete(Region region);

    void Delete(T id);

    List<Region> GetAll();

    Region GetByName(String name);

    List<Region> findByParentId(Integer parentId);
}
