package com.tongji.bwm.security;

import com.tongji.bwm.entity.Basic.Administrator;
import com.tongji.bwm.pojo.FrontAdmin;
import com.tongji.bwm.service.Basic.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdministratorService administratorService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        //从数据库中取出用户信息
        Administrator admin = administratorService.GetByLoginName(username);

        //判断用户是否存在
        if(admin == null){
            throw new UsernameNotFoundException("用户名不存在");
        }


        //添加权限
        authorities.add(new SimpleGrantedAuthority(admin.getRole().getRole()));


        //自定义用户
        UserEntity userEntity = new UserEntity(admin.getLoginName(),admin.getLoginPassword(),authorities);
        userEntity.setLoginName(admin.getName());
        return userEntity;
    }
}
