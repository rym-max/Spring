package com.tongji.bwm.filters.Converter.EnumConverter;

import com.tongji.bwm.pojo.Enum.CommonEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuditStatusEnumConverter implements Converter<Integer, CommonEnum.AuditStatusEnum> {

    @Override
    public CommonEnum.AuditStatusEnum convert(Integer integer) {
        if(integer==null || integer<0 || integer>= CommonEnum.AuditStatusEnum.values().length)
            return null;
        return CommonEnum.AuditStatusEnum.values()[integer];
    }
}
