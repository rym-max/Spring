package com.tongji.bwm.filters.Converter.EnumConverter;

import com.tongji.bwm.pojo.Enum.CommonEnum;
import org.springframework.core.convert.converter.Converter;

public class AvailableEnumConverter implements Converter<Integer, CommonEnum.AvailableEnum> {

    @Override
    public CommonEnum.AvailableEnum convert(Integer integer) {
        if(integer==null || integer<0 || integer>= CommonEnum.AvailableEnum.values().length)
            return null;
        return CommonEnum.AvailableEnum.values()[integer];
    }
}
