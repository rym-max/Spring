package com.tongji.bwm.service.ERMS;

import com.tongji.bwm.entity.ERMS.MetadataFieldRegistry;
import org.springframework.data.domain.Example;

import java.util.List;

public interface IMetadataFieldRegistryService<T> {

    T Insert(MetadataFieldRegistry metadataFieldRegistry);

    MetadataFieldRegistry GetById(T id);

    MetadataFieldRegistry GetByFields(Example<MetadataFieldRegistry> example);

    void Update(MetadataFieldRegistry metadataFieldRegistry);

    void Delete(MetadataFieldRegistry metadataFieldRegistry);

    void Delete(T id);

    List<MetadataFieldRegistry> GetAll();

    List<MetadataFieldRegistry> GetList(Example<MetadataFieldRegistry> example);
}
