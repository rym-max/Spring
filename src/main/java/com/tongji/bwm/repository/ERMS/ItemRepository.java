package com.tongji.bwm.repository.ERMS;

import com.tongji.bwm.entity.ERMS.Item;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import com.tongji.bwm.solr.Client.SolrConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<Item, String> {


     long countByStatus(CommonEnum.AuditStatusEnum Status);
    /*
     *没意义
     */
//    public static long ConvertDataTimeLong(Date dt){
//
//        return dt.getTime();
//    }

}
