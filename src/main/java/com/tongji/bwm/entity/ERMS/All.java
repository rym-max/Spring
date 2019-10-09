package com.tongji.bwm.entity.ERMS;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tongji.bwm.entity.Basic.PKGUIDEntity;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import lombok.Data;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "ERMS_All")
@JsonIgnoreProperties(ignoreUnknown = true)
public class All extends PKGUIDEntity {

    private Boolean isGermany=false;

    private Boolean isSolr =true;

    private Boolean isAudit = false;

    @Transient
    private HashMap<String,String[]> _field = new HashMap<String, String[]>();

    private String metadataValue;

    private Integer click=0;

    private Integer sort=0;

    @Enumerated(EnumType.ORDINAL)
    private CommonEnum.AuditStatusEnum status;//审核状态

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ChannelId",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT),updatable = false,insertable = false)
    @NotFound(action= NotFoundAction.IGNORE)
    private Channel ownerChannel;//指定外键咋办

    @Column(name="ChannelId",nullable = false)
    private Integer channelId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CategoryId",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT),updatable = false,insertable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Category ownerCategory;

    @Column(name = "CategoryId",nullable = false)
    private Integer categoryId;//可能为空？

    public HashMap<String, String[]> get_field() {
        if(this.metadataValue!=null && this.metadataValue.length()!=0 && this._field.isEmpty()){
            //解析XML
//            SAXReader reader = new SAXReader();
//            Document document = reader
            try {
                Document document = DocumentHelper.parseText(this.metadataValue);
                Element rootElement = document.getRootElement();

                Iterator<Element> iterator = rootElement.elementIterator("field");

                while(iterator.hasNext()){
                    Element recordEle = iterator.next();
                    //读取attribute name
                    String name = recordEle.attributeValue("name");
                    List<String> values = new ArrayList<String>();
                    if(this._field.containsKey(name)){
                        values = new ArrayList<>(Arrays.asList(this._field.get(name)));
                        values.add(recordEle.getText());
                        this._field.remove(name);
                    }else{
                        values.add(recordEle.getText());
                    }
                    this._field.put(name,values.toArray(new String[values.size()]));
                }
            }catch (DocumentException e){
                //无法解析
                this.metadataValue = null;
            }
        }

        return _field;
    }

    public String GetValue(String key){
        return this.GetValue(key,0,"");
    }

    public String GetValue(String key, Integer index){
        return this.GetValue(key,index,"");
    }

    public String GetValue(String key, Integer index, String defaultValue){
        //初始化_field
        get_field();

        if(this._field.containsKey(key)){
            String[] values = this._field.get(key);
            if(values != null && values.length > index){
                return values[index];
            }
        }
        return defaultValue;
    }

    public String GetValue(String key, String split, String defaultValue){
        //初始化_field
        get_field();

        String returnValue = "";
        if(this._field.containsKey(key)){
            String[] values = this._field.get(key);
            if(values != null && values.length !=0){
                returnValue = values[0];
                for(Integer i =0;i<values.length;i++){
                    returnValue = returnValue + split + values[i];
                }
            }
        }
        if(returnValue.equals("")){
            return returnValue;
        }
        return defaultValue;
    }

    public String GetValue(String key, String split, Integer length, String defaultValue){
        //初始化_field
        get_field();

        String returnValue = "";
        if(this._field.containsKey(key)){
            String[] values = this._field.get(key);
            if(values != null && values.length !=0){
                returnValue = values[0];
                for(Integer i =0;i<values.length && i<length;i++){
                    returnValue = returnValue + split + values[i];
                }
            }
        }
        if(returnValue.equals("")){
            return returnValue;
        }
        return defaultValue;
    }


//    public Integer getChannelId() {
//        if(ownerChannel!=null) {
//            channelId = ownerChannel.getId();
//        }
//        return channelId;
//    }


}
