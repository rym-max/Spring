package com.tongji.bwm.repository.Log;

import com.tongji.bwm.entity.Log.Spider_Log;
import com.tongji.bwm.pojo.SpiderLog.UserCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpiderLogRepository extends JpaRepository<Spider_Log,Integer> {

    @Query(
            value = "SELECT ActionUser,Count(*) \r\n" +
                    "FROM Log_SpiderLog \r\n" +
                    "WHERE ([Action]= :action) AND (Spider =:spider) \r\n" +
                    "GROUP BY ActionUser",
            nativeQuery = true
    )
    List<Object[]> GetUserCountBySpider(@Param(value = "spider") Integer spiderId,@Param(value = "action") String action);

    @Query(
            value = "SELECT ActionUser,Count(*) \r\n" +
                    "FROM Log_SpiderLog \r\n" +
                    "WHERE Spider =:spider \r\n" +
                    "GROUP BY ActionUser",
            nativeQuery = true
    )
    List<Object[]> GetUserCountBySpider(@Param(value = "spider") Integer spiderId);

    @Query(
            value = "SELECT TOP (:times) \r\n" +
                    "[Action] ,Result,DATEPART(mm,[CreateTime]) as Month,DATEPART(dd,[CreateTime]) as Day \r\n" +
                    "FROM Log_SpiderLog \r\n" +
                    "WHERE (Spider= :spider) AND ([Action]='CRAWL_OVER') \r\n"+
                    "ORDER BY CreateTime DESC",
            nativeQuery = true
    )
    List<Object[]>  GetBySpider(@Param(value = "spider") Integer Spider ,@Param(value="times")Integer Times);

}
