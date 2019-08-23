package com.tongji.bwm.repository.ERMS;

import com.tongji.bwm.entity.ERMS.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Integer> {

    Channel findByCode(String code);


}
