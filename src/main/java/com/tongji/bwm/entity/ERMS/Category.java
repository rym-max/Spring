package com.tongji.bwm.entity.ERMS;

import com.tongji.bwm.entity.Basic.PKINTEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "ERMS_Category")
@DynamicInsert
@DynamicUpdate
public class Category extends PKINTEntity {

//    private Integer ChannelId;
    @Column(length = 50)
    @NotBlank(message = "分类名称不能为空！")
    @Size(min=1,max=50,message = "分类名称长度不能超过50个字符！")
    private String name;

    private String nameEn;

//    private Integer ParentId;//上级栏目
    @Column(length = 50)
    @Size(max = 50, message = "分类编码长度不能超过50个字符！")
    private String code;//栏目编码
    @Column(length = 512)
    private String icon;//图标
    @Column(length = 1024,columnDefinition = "nvarchar(1024) null")
    @Size(max = 1024,message = "查询表达式长度不能超过1024个字符！")
    private String queryExpression;

    private Integer sort;//显示顺序

    private Integer status=0;//显示状态
    @Column(columnDefinition = "ntext null")
    private String description;//

    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "ParentId",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT),updatable = false,insertable = false)
    @NotFound(action= NotFoundAction.IGNORE)
    private Category ownerCategory;

    @Column(name = "ParentId",nullable = false)
    private Integer parentId;

    @JoinColumn(name = "ChannelId",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT),referencedColumnName = "Id",updatable = false,insertable = false)
    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @NotFound(action= NotFoundAction.IGNORE)
    private Channel ownerChannel;

    @Column(name="ChannelId",nullable = false)
    private Integer channelId;


}
