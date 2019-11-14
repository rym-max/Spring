package com.tongji.bwm.entity.Basic;

import com.tongji.bwm.pojo.Enum.CommonEnum;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


@Data
@Entity
@Table(name = "Administrator")
public class Administrator extends PKINTEntity {


    @NotBlank(message = "登录名不能为空")
    @Size(min=4,max=20,message = "登录名长度范围为4~20个字符！")
    @Column(name = "LoginName")
    private String loginName;

    @NotBlank(message = "登录密码不能为空！")
    @Size(min=6,max=20,message = "登录密码长度范围为6~20个字符！")
    @Column(name = "LoginPassword")
    private String loginPassword;

    @Column(name = "Name")
    private String name;

    @NotNull(message = "必须选择角色")
    @Enumerated(EnumType.ORDINAL)
    @Column(name="Role")
    private CommonEnum.RoleType role;

}
