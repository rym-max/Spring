package com.tongji.bwm.repository.ERMS;

import com.tongji.bwm.entity.ERMS.MetadataSchemaRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetadataSchemaRegistryRepository extends JpaRepository<MetadataSchemaRegistry, Integer> {

    MetadataSchemaRegistry findByCode(String code);
}
