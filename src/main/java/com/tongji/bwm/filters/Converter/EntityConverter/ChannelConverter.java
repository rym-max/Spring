package com.tongji.bwm.filters.Converter.EntityConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tongji.bwm.entity.ERMS.Channel;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.Map;

public class ChannelConverter extends AbstractHttpMessageConverter<Channel> {

    private static final FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected boolean supports(Class<?> aClass) {
        return Channel.class == aClass;
    }

    @Override
    protected Channel readInternal(Class<? extends Channel> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        Map<String,String> vals = formHttpMessageConverter.read(null,httpInputMessage).toSingleValueMap();
        return mapper.convertValue(vals,Channel.class) ;
    }

    @Override
    protected void writeInternal(Channel channel, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
