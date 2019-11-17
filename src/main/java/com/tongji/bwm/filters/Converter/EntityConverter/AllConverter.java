package com.tongji.bwm.filters.Converter.EntityConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tongji.bwm.entity.ERMS.All;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.Map;

/**
 * @author starcloud
 * @date 2019/11/12
 **/
public class AllConverter extends AbstractHttpMessageConverter<All> {

    private static final FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected boolean supports(Class<?> aClass) {
        return All.class == aClass;
    }

    @Override
    protected All readInternal(Class<? extends All> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        Map<String,String> vals = formHttpMessageConverter.read(null,httpInputMessage).toSingleValueMap();
        return mapper.convertValue(vals, All.class) ;
    }

    @Override
    protected void writeInternal(All administrator, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
