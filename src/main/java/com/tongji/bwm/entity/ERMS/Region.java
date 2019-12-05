package com.tongji.bwm.entity.ERMS;

import com.tongji.bwm.entity.Basic.PKINTEntity;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "ERMS_Region")
@DynamicInsert
@DynamicUpdate
public class Region extends PKINTEntity {

    @NotBlank(message = "区域代码不能为空！")
    @Size(min=6,max=50,message = "区域代码长度为6~50！")
    private String name;

    @ManyToOne
    @JoinColumn(name = "ParentId",columnDefinition = "int default 0",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
    @NotFound(action= NotFoundAction.IGNORE)
    private Region ownerRegion;

    @Transient
    private Integer parentId;

    @NotBlank(message = "区域英文名称不能为空！")
    @Size(max = 50,message = "区域英文名称长度不能超过50！")
    private String nameEN;
    @NotBlank(message = "区域中文名称不能为空！")
    @Size(max = 50,message = "区域中文名称长度不能超过50！")
    private String nameCN;

    private String solrQueryExpression;

    public String getSolrQueryString(){
        if(solrQueryExpression!=null && !solrQueryExpression.isEmpty()){
            String[] result_li = solrQueryExpression.split(";");
            StringBuffer stringBuffer = new StringBuffer();
            for(int i=0;i<result_li.length;i++){
                if(!result_li[i].isEmpty()) {
                    if (i != 0) stringBuffer.append(" OR ");
                    stringBuffer.append("(description: \"" + result_li[i] + "\" ");
                    stringBuffer.append(" OR subject:\"" + result_li[i] + "\" ");
                    stringBuffer.append(" OR title:\"" + result_li[i] + "\" )");
                }
            }
            return stringBuffer.toString();
        }else {
            return "";
        }
    }
    
    
    private Boolean isSolrIndex;

    @Enumerated(EnumType.ORDINAL)
    private CommonEnum.AvailableEnum status;

    private String description;

    private String iconPath;

    @Enumerated(EnumType.ORDINAL)
    private CommonEnum.AvailableEnum map;//是否前台显示

    public Integer getParentId() {
        if(ownerRegion!=null){
            parentId =ownerRegion.getId();
        }
        return parentId;
    }
}
