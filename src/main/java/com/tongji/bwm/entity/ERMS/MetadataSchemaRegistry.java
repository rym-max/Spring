package com.tongji.bwm.entity.ERMS;

import com.tongji.bwm.entity.Basic.PKINTEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "ERMS_MetadataSchemaRegistry")
public class MetadataSchemaRegistry extends PKINTEntity {

    @NotEmpty(message = "元数据类型名称不能为空！")
    @Size(max=50,message = "元数据类型名称长度不能超过50个字符！")
    private String name;
    @NotEmpty(message = "元数据类型编码不能为空！")
    @Size(max=50,message = "元数据类型编码长度不能超过50个字符！")
    private String code;

}
