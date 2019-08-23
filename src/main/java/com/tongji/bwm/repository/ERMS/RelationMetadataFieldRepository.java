package com.tongji.bwm.repository.ERMS;

import com.tongji.bwm.entity.ERMS.RelationMetadataField;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationMetadataFieldRepository extends JpaRepository<RelationMetadataField,Integer> {

    List<RelationMetadataField> findAllByObjectTypeAndRelationObjectIdOrderBySortDesc(CommonEnum.CustomMetadataFieldObject ObjectType, Integer RelationObjectId);

    List<RelationMetadataField> findAllByObjectTypeAndRelationObjectIdOrderBySortAsc(CommonEnum.CustomMetadataFieldObject ObjectType, Integer RelationObjectId);
}
