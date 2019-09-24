package com.tongji.bwm.pojo;

import com.tongji.bwm.entity.ERMS.MetadataFieldRegistry;
import lombok.Data;

@Data
public class MetaFieldRegistryNameAndId{


    private String text;
    private String value;

    public MetaFieldRegistryNameAndId() {
    }

    public MetaFieldRegistryNameAndId(MetadataFieldRegistry metadataFieldRegistry) {
        text = metadataFieldRegistry.getName() + "[" + metadataFieldRegistry.GetMetadataFieldString() + "]";
        value = String.valueOf(metadataFieldRegistry.getId());

    }
}
