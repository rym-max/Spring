package com.tongji.bwm.repository.Basic;

import com.tongji.bwm.entity.Basic.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

    Administrator findByLoginName(String loginName);
}
