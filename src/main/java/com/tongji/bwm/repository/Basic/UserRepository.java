package com.tongji.bwm.repository.Basic;

import com.tongji.bwm.entity.Basic.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByLoginName(String loginName);


}
