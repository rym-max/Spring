package com.tongji.bwm.entity.ERMS;

import com.tongji.bwm.entity.Basic.PKINTEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "ERMS_Channel")
public class Channel extends PKINTEntity {

    @NotBlank(message = "频道名称不能为空！")
    @Size(min=1, max = 50, message = "频道名称长度不能超过50个字符！")
    private String name;

    private String nameEn;
    @Size(max = 50, message = "频道编码长度不能超过50个字符！")
    private String code;

    private String icon;
    @Size(max = 1024, message = "频道编码长度不能超过1024个字符！")
    @Column(name = "QueryExpression", columnDefinition = "nvarchar(1024) null")
    private String queryExpression;

    private Integer sort;

    private Integer status=0;
    @Column(name = "Description" ,columnDefinition = "ntext null")
    private String description;


}
