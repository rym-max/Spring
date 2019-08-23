package com.tongji.bwm.entity.ERMS;

import com.tongji.bwm.entity.Basic.PKINTEntity;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "ERMS_MetadataFieldRegistry")
public class MetadataFieldRegistry extends PKINTEntity {

    @Column(length = 50)
    private String name;

//    private Integer MetadataSchemaId;

    @Column(nullable = false,length = 50)
    @Size(max=50,message = "元数据要素不能为空！")
    private String element;

    @Column(length = 50)
    @Size(max=50, message = "修饰不能超过50个字符")
    private String qualifier;

    @Column(length = 512)
    @Size(max=512,message = "范围说明不能超过512个字符")
    private String scopeNote;

    @Enumerated(EnumType.ORDINAL)
    private CommonEnum.ControlType controlType;
//    @Transient
//    private String ControlTypeCN;

    @Enumerated(EnumType.ORDINAL)
    private CommonEnum.DataType dataType;
//    @Transient
//    private String DataTypeCN;

    @Column(length = 255)
    @Size(max=255, message = "验证规则不能超过255个字符")
    private String validationRules;

    private Boolean isSearch;
    @Column(length = 50)
    private String searchName;

    private Boolean isFullSearch;

    private Boolean isCluster;

    private Boolean isSort;

    private Boolean isRequired;

    private Boolean isMultiple;

    private String defaultValue;
    @Column(length = 1024)
    @Size(max = 1024, message = "下拉框选项不能超过1024个字符")
    private String options;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MetadataSchemaId")
    private MetadataSchemaRegistry ownerMetadataSchemaRegistry;

    public String GetMetadataFieldString(){
        return ((this.ownerMetadataSchemaRegistry == null) ? "dc" : this.ownerMetadataSchemaRegistry.getCode()) + "." + this.element + ((this.qualifier == null || this.qualifier.length()==0) ? "" : ("." + this.qualifier));
    }

//    public void refresh(){
//
//        ControlTypeCN = CommonEnum.ControlType.valueofCN(ControlType);
//        DataTypeCN = CommonEnum.DataType.valueofCN(DataType);
//
//    }
}
