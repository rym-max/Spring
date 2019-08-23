package com.tongji.bwm.repository.Log;

import com.tongji.bwm.pojo.CountByDay;
import com.tongji.bwm.pojo.IpCount;
import com.tongji.bwm.entity.Log.Access_Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessLogRepository extends JpaRepository<Access_Log,Integer> {
    //理论上可行，等会先写个test

    @Query(
            value = "SELECT :year AS Year, :month AS Month, temp.Day AS Day, COUNT(*) AS Count \r\n " +
                    "FROM \r\n " +
                    "( SELECT DATEPART(Day, q.ModifyTime) AS Day, q.ModifyTime FROM Log_AccessLog AS q ) \r\n " +
                    "AS temp \r\n " +
                    "WHERE (DATEPART(yyyy, temp.ModifyTime) = :year) AND (DATEPART(m, temp.ModifyTime) = :month) \r\n " +
                    "GROUP BY temp.Day",
            nativeQuery = true
    )
    List<Object[]> GetEachDayAccessByMonth(@Param(value = "year") int year, @Param(value = "month") int month);

    @Query(
            value = "SELECT IPAddress, COUNT(IPAddress) \r\n " +
                    "FROM Log_AccessLog \r\n " +
                    "WHERE (DATEPART(yyyy, [ModifyTime]) = :year) AND (DATEPART(m, [ModifyTime]) = :month)\r\n " +
                    "GROUP BY IPAddress",
            nativeQuery = true
    )
    List<Object[]> GetIpCount(@Param(value = "year") int year, @Param(value = "month") int month);


}
