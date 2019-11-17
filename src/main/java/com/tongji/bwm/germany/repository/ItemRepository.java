package com.tongji.bwm.germany.repository;

import com.tongji.bwm.germany.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author starcloud
 * @date 2019/09/24
 **/
@Repository
public interface ItemRepository extends JpaRepository<Item,String> {

    //记得id不能事先设置，设为空

}
