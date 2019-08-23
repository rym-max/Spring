package com.tongji.bwm.entity.Basic;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Data
@Entity
@Table(name = "[User]")
public class User extends PKINTEntity{

    //@Column(name = "LoginName",length = 50)
    @NotBlank(message = "登录名不能为空")
    @Size(min=4,max=20,message = "登录名长度范围为4~20个字符！")
    private String loginName;


    //@Column(name="LoginPassword",length = 50)
    @NotBlank(message = "登录密码不能为空")
    @Size(min=6,max=20,message = "登录密码长度范围为4~20个字符！")
    private String loginPassword;


    //@Column(length = 50)
    private String name;
    //@Column(length = 10)
    private String sex;
    //@Column(length = 10)
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}",message = "生日日期格式为：yyyy-MM-dd")
    private String birthday;
    //@Column(length = 50)
    private String email;
    //@Column(length = 50)
    @Size(min = 11,max = 11,message = "手机号为十一位数字！")
    private String mobilePhoneNumber;
    //@Column(length = 50)
    private String phoneNumber;

    private String summary;
    //@Column(length = 512)
    private String avatar;


}
