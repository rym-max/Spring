package com.tongji.bwm.filters.Converter.EnumConverter;

import com.tongji.bwm.pojo.Enum.Scrapy.ScrapyEnum;
import org.springframework.core.convert.converter.Converter;

public class ScrapyStatusConverter implements Converter<Integer, ScrapyEnum.StatusEnum> {

    @Override
    public ScrapyEnum.StatusEnum convert(Integer integer) {
        if(integer==null || integer<0 || integer>= ScrapyEnum.StatusEnum.values().length)
            return null;
        return ScrapyEnum.StatusEnum.values()[integer];
    }
}
