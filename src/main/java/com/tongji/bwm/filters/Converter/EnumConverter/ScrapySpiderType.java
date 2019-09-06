package com.tongji.bwm.filters.Converter.EnumConverter;

import com.tongji.bwm.pojo.Enum.Scrapy.ScrapyEnum;
import org.springframework.core.convert.converter.Converter;

public class ScrapySpiderType implements Converter<Integer, ScrapyEnum.SpiderType> {

    @Override
    public ScrapyEnum.SpiderType convert(Integer integer) {
        if(integer == null || integer<0 || integer>= ScrapyEnum.SpiderType.values().length)
            return null;
        return ScrapyEnum.SpiderType.values()[integer];
    }
}
