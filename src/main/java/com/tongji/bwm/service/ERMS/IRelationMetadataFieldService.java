package com.tongji.bwm.service.ERMS;

import com.tongji.bwm.entity.ERMS.RelationMetadataField;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IRelationMetadataFieldService<T> {

    T Insert(RelationMetadataField relationMetadataField);

    RelationMetadataField GetById(T id);

    RelationMetadataField GetByFields(Example<RelationMetadataField> example);

    void Update(RelationMetadataField relationMetadataField);

    void Delete(RelationMetadataField relationMetadataField);

    void Delete(T id);

    List<RelationMetadataField> GetList(Example<RelationMetadataField> example);

    List<RelationMetadataField> GetList(Example<RelationMetadataField> example, Sort sort);

}
