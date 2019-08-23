package com.tongji.bwm.pojo;

import com.tongji.bwm.entity.ERMS.RelationMetadataField;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import lombok.Data;

@Data
public class FrontMetadataField {

    private String schema;

    private String element;

    private String qualifier;

    private String fieldName;

    private String name;

    private CommonEnum.ControlType controlType;

    private Boolean isMultiple;

    private String[] values;

    private String[] options;

    private String defaultValue;

    public FrontMetadataField() {
    }

    public FrontMetadataField(RelationMetadataField relationMetadataField) {
        this.schema = relationMetadataField.getOwnerMetaFieldRegistry().getOwnerMetadataSchemaRegistry().getCode();
        this.element = relationMetadataField.getOwnerMetaFieldRegistry().getElement();//有意义？
        this.qualifier = relationMetadataField.getOwnerMetaFieldRegistry().getQualifier();
        this.fieldName = relationMetadataField.getOwnerMetaFieldRegistry().GetMetadataFieldString();

        this.name = relationMetadataField.getName();

        this.controlType = relationMetadataField.getControlType();

        this.isMultiple = relationMetadataField.getIsMultiple();

        this.defaultValue = relationMetadataField.getDefaultValue();

        String optionString = relationMetadataField.getOwnerMetaFieldRegistry().getOptions();
        if(optionString==null || optionString.isEmpty()){
            this.options = new String[]{};
        }else {
            this.options = optionString.split("\n");
        }

    }
}
