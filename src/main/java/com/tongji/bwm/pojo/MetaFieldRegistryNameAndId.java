package com.tongji.bwm.pojo;

import com.tongji.bwm.entity.ERMS.MetadataFieldRegistry;

public class MetaFieldRegistryNameAndId{


    private String Text;
    private String Value;

    public MetaFieldRegistryNameAndId() {
    }

    public MetaFieldRegistryNameAndId(MetadataFieldRegistry metadataFieldRegistry) {
        Text = metadataFieldRegistry.getName() + "[" + metadataFieldRegistry.GetMetadataFieldString() + "]";
        Value = String.valueOf(metadataFieldRegistry.getId());

    }
}
