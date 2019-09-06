package com.tongji.bwm.repository.ERMS;

import com.tongji.bwm.entity.ERMS.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region,Integer> {

    Region findByName(String name);

    @Query(
            value = "SELECT * FROM ERMS_Region WHERE ParentId = :parentId ",
            nativeQuery = true
    )
    List<Region> findByParentId(@Param("parentId") Integer parentId);
}
