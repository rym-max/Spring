package com.tongji.bwm.repository.ERMS;

import com.tongji.bwm.entity.ERMS.All;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllRepository extends JpaRepository<All,String> {
}
