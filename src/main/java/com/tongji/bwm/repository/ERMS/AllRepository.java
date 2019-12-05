package com.tongji.bwm.repository.ERMS;

import com.tongji.bwm.entity.ERMS.All;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AllRepository extends JpaRepository<All,String> {

//    @Query(
//            value = "SELECT COUNT(1) FROM ERMS_All",
//            nativeQuery = true
//    )
//    Long Count();
//
//    @Query(
//            value = "SELECT COUNT(1) FROM ERMS_All",
//            nativeQuery = true
//    )
//    Long Count();

    @EntityGraph(value = "All.Graph",type = EntityGraph.EntityGraphType.FETCH)
    Slice<All> findByIdIsNotNull(Pageable pageable);

    @EntityGraph(value = "All.Graph",type = EntityGraph.EntityGraphType.FETCH)
    Slice<All> findByStatus(CommonEnum.AuditStatusEnum Status, Pageable pageable);

    Long countAllByStatus(CommonEnum.AuditStatusEnum Status);
}
