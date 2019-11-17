package com.tongji.bwm.germany.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tongji.bwm.entity.Basic.PKGUIDEntity;
import com.tongji.bwm.entity.ERMS.All;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import lombok.Data;

import javax.persistence.*;

/**
 * @author starcloud
 * @date 2019/09/24
 **/

@Data
@Entity
@Table(name = "ERMS_Item")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item extends PKGUIDEntity {

    private String metadataValue;

    private Integer click=0;

    private Integer sort=0;

    @Enumerated(EnumType.ORDINAL)
    private CommonEnum.AuditStatusEnum status;

    @Column(name = "ChannelId",nullable = false)
    private Integer ChannelId;

    @Column(name="CategoryId",nullable = false)
    private Integer CategoryId;

    public static Item GetInstanceByAll(All all){

        if(all.getIsGermany().equals(false)){
            return null;
        }

        Item item = new Item();
        item.setCategoryId(all.getCategoryId());
        item.setChannelId(all.getChannelId());
        item.setMetadataValue(all.getMetadataValue());
        item.setClick(all.getClick());
        item.setSort(all.getSort());
        item.setStatus(all.getStatus());

        return item;
    }

    public String toString(){
        return "Item(" +
                "id=" + getId()+
                ",status=" + status+
                ",channel=" + getChannelId()+
                ",category=" + getCategoryId()+
                ")";
    }
}
