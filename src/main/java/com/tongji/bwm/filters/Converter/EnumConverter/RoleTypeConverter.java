package com.tongji.bwm.filters.Converter.EnumConverter;

import com.tongji.bwm.pojo.Enum.CommonEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleTypeConverter implements Converter<Integer, CommonEnum.RoleType> {

    @Override
    public CommonEnum.RoleType convert(Integer integer) {
        if(integer==null)
            return null;
        if(integer<0 || integer>=CommonEnum.RoleType.values().length)
            return null;
        return CommonEnum.RoleType.values()[integer];
    }
}
