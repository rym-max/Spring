package com.tongji.bwm.repository.ERMS;

import com.tongji.bwm.entity.ERMS.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, String> {


//    Achievement findByCode(String code); //暂时用不上
}
