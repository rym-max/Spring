package com.tongji.bwm.repository.ERMS;

import com.tongji.bwm.entity.ERMS.Item;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import com.tongji.bwm.pojo.Enum.Scrapy.ScrapyEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


//@Repository
public interface ItemRepository extends JpaRepository<Item, String> {


     long countByStatus(CommonEnum.AuditStatusEnum Status);

     @Query(
             value = "SELECT COUNT(*) FROM ERMS_Item",
             nativeQuery = true
     )
     long countAll();

    /*
     *没意义
     */
//    public static long ConvertDataTimeLong(Date dt){
//
//        return dt.getTime();
//    }

}
