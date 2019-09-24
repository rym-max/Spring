package com.tongji.bwm.repository.ERMS;

import com.tongji.bwm.entity.ERMS.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByCode(String code);

    @Query(
            value = "SELECT * FROM ERMS_Category WHERE ChannelId = :ChannelId",
            nativeQuery = true
    )
    List<Category> findByChannelId(@Param(value = "ChannelId")Integer ChannelId);
}
