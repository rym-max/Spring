package com.tongji.bwm.entity.ERMS;

import com.tongji.bwm.entity.Basic.PKINTEntity;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "ERMS_MetadataFieldRegistry")
public class MetadataFieldRegistry extends PKINTEntity {

    @Column(length = 50)
    @NotBlank(message = "元数据中文名称不能为空！")
    private String name;

//    private Integer MetadataSchemaId;

    @Column(nullable = false,length = 50)
    @NotBlank(message = "元数据要素不能为空！")
    @Size(max=50,message = "元数据要素不能超过50个字符！")
    private String element;

    @Column(length = 50)
    @Size(max=50, message = "修饰不能超过50个字符！")
    private String qualifier;

    @Column(length = 512)
    @Size(max=512,message = "范围说明不能超过512个字符！")
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
    @Size(max=255, message = "验证规则不能超过255个字符！")
    private String validationRules;

    private Boolean isSearch;
    @Column(length = 50)
    @Size(max = 50,message = "搜索名称不能超过50个字符！")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MetadataSchemaId",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT),updatable = false,insertable = false)
    @NotFound(action= NotFoundAction.IGNORE)
    private MetadataSchemaRegistry ownerMetadataSchemaRegistry;

    @Column(name = "MetadataSchemaId",nullable = false)
    private Integer metadataSchemaId;


    public String GetMetadataFieldString(){
        return ((this.ownerMetadataSchemaRegistry == null) ? "dc" : this.ownerMetadataSchemaRegistry.getCode()) + "." + this.element + ((this.qualifier == null || this.qualifier.length()==0) ? "" : ("." + this.qualifier));
    }


}
