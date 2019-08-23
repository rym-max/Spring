package com.tongji.bwm.service.ERMS;

import com.tongji.bwm.entity.ERMS.MetadataSchemaRegistry;

import java.util.List;

public interface IMetadataSchemaRegistryService<T> {

    T Insert(MetadataSchemaRegistry metadataSchemaRegistry);

    MetadataSchemaRegistry GetById(T id);

    MetadataSchemaRegistry GetByCode(String code);

    void Update(MetadataSchemaRegistry metadataSchemaRegistry);

    void Delete(MetadataSchemaRegistry metadataSchemaRegistry);

    void Delete(T id);

    List<MetadataSchemaRegistry> GetAll();
}
