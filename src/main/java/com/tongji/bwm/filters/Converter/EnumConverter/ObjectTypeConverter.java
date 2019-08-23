package com.tongji.bwm.filters.Converter.EnumConverter;

import com.tongji.bwm.pojo.Enum.CommonEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ObjectTypeConverter implements Converter<Integer, CommonEnum.CustomMetadataFieldObject> {

    @Override
    public CommonEnum.CustomMetadataFieldObject convert(Integer integer) {
        if(integer ==null)
            return null;
        if(integer<0 || integer>CommonEnum.CustomMetadataFieldObject.values().length)
            return null;
        return CommonEnum.CustomMetadataFieldObject.values()[integer];

    }
}
