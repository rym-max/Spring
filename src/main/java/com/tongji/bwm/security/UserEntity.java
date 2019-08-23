package com.tongji.bwm.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class UserEntity implements UserDetails {

    /*
    必须信息
     */
    //用户名
    private String username;
    //用户密码
    private String password;
    //是否可用
    private Boolean enabled;
    //用户拥有的权限
    private Collection<? extends GrantedAuthority> authorities;
    //true 未过期未锁定
    //用户账号是否过期
    private Boolean accountNonExpired = true;
    //用户账号是否锁定
    private Boolean accountNonLocked = true;
    //用户凭据是否过期
    private Boolean credentialsNonExpired = true;

    /*
    自定义信息
     */
    private String loginName="";

    public UserEntity(String username, String password, Boolean enabled, Collection<? extends GrantedAuthority> authorities, Boolean accountNonExpired, Boolean accountNonLocked, Boolean credentialsNonExpired) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public UserEntity(String username, String password, Boolean enabled) {
        this(username,password,enabled,null,true,true,true);
    }

    public UserEntity(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this(username,password,true,authorities,true,true,true);
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
