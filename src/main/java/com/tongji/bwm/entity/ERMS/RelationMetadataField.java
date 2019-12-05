package com.tongji.bwm.entity.ERMS;

import com.tongji.bwm.entity.Basic.PKINTEntity;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "ERMS_RelationMetadataField")
@DynamicInsert
@DynamicUpdate
public class RelationMetadataField extends PKINTEntity {

    private Integer relationObjectId;

    @Enumerated(EnumType.ORDINAL)
    private CommonEnum.CustomMetadataFieldObject objectType;
//    @Transient
//    private String ObjectTypeS;

    @Transient
    private Integer objectTypeId;

    public Integer getObjectTypeId() {
        if(objectType!=null){
            objectTypeId = objectType.ordinal();
        }
        return objectTypeId;
    }

    @Column(length = 50)
    @Size(max=50,message = "字段中文名称不能超过50个字符")
    private String name;

    private Integer sort;

    @Enumerated(EnumType.ORDINAL)
    private CommonEnum.ControlType controlType;
//    @Transient
//    private String ControlTypeCN;

    @Enumerated(EnumType.ORDINAL)
    private CommonEnum.DataType dataType;
//    @Transient
//    private String DataTypeCN;

    @Size(max = 255,message = "验证规则不能超过255个字符")
    private String validationRules;

    private Boolean isSearch;
    @Size(max = 50 ,message = "检索名称不能超过50个字符")
    private String searchName;

    private Boolean isFullSearch;

    private Boolean isCluster;

    private Boolean isMultiple;

    private Boolean isRequired;

    private Boolean isSort;

    private String defaultValue;
    @Size(max = 1024 ,message = "下拉框选项不能超过1024个字符")
    private String options;

    @JoinColumn(name="MetadataFieldId",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT),updatable = false,insertable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action= NotFoundAction.IGNORE)
    private MetadataFieldRegistry ownerMetaFieldRegistry;

    @Column(name="MetadataFieldId",nullable = false)
    private Integer metadataFieldId;



    //    public void refresh(){
//
//        ControlTypeCN = CommonEnum.ControlType.valueofCN(ControlType);
//        DataTypeCN = CommonEnum.DataType.valueofCN(DataType);
//        MetadataFieldId = OwnerMetaFieldRegistry.getId();
//    }

}
