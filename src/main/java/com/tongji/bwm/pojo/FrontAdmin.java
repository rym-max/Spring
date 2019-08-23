package com.tongji.bwm.pojo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Scope("session")
public class FrontAdmin implements Serializable {

    private String loginName;
    private String name;

    public FrontAdmin(String loginName, String name) {
        this.loginName = loginName;
        this.name = name;
    }

    public FrontAdmin() {
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
