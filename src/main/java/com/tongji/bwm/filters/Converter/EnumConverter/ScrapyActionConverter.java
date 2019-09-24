package com.tongji.bwm.filters.Converter.EnumConverter;

import com.tongji.bwm.pojo.Enum.Scrapy.ScrapyEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ScrapyActionConverter implements Converter<String, ScrapyEnum.ActionEnum> {

    @Override
    public ScrapyEnum.ActionEnum convert(String s) {
        if(s==null || s.isEmpty())
            return null;
        try {
            return ScrapyEnum.ActionEnum.valueOf(s);
        }catch (Exception e){
            return null;
        }
    }
}
