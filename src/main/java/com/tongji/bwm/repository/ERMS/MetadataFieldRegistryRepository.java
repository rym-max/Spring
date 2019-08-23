package com.tongji.bwm.repository.ERMS;

import com.tongji.bwm.entity.ERMS.MetadataFieldRegistry;
import com.tongji.bwm.entity.ERMS.MetadataSchemaRegistry;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MetadataFieldRegistryRepository extends JpaRepository<MetadataFieldRegistry,Integer> {

    MetadataFieldRegistry findByOwnerMetadataSchemaRegistryAndElement(MetadataSchemaRegistry ownerMetadataSchemaRegistry, String element);

    MetadataFieldRegistry findByOwnerMetadataSchemaRegistryAndElementAndQualifier(MetadataSchemaRegistry ownerMetadataSchemaRegistry,String element,String qualifier);

    @Query(
            value = "SELECT * FROM ERMS_MetadataFieldRegistry WHERE MetadataSchemaId = :schema and Element = :element",
            nativeQuery = true
    )
    MetadataFieldRegistry findByMetadataSchemaIdAndElement(@Param("schema")Integer schema,
                                                 @Param("element")String element);


    @Query(
            value = "SELECT * FROM ERMS_MetadataFieldRegistry WHERE MetadataSchemaId = :schema and Element = :element and Qualifier = :qualifier",
            nativeQuery = true
    )
    MetadataFieldRegistry findByMetadataSchemaIdAndElementAndQualifier(@Param("schema")Integer schema,
                                                             @Param("element")String element,
                                                             @Param("qualifier")String qualifier);
}
