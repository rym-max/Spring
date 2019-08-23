package com.tongji.bwm.entity.Log;

import com.tongji.bwm.entity.Basic.PKINTEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "Log_AccessLog")
public class Access_Log extends PKINTEntity {

    @Size(max=16,message = "IP地址长度不能超过16个字符！")
    private String IPAddress;

}
