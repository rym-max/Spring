package com.tongji.bwm.filters.Converter.EntityConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tongji.bwm.entity.ERMS.MetadataFieldRegistry;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.Map;

public class MetadataFieldRegistryConverter extends AbstractHttpMessageConverter<MetadataFieldRegistry> {

    private static final FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected boolean supports(Class<?> aClass) {
        return MetadataFieldRegistry.class == aClass;
    }

    @Override
    protected MetadataFieldRegistry readInternal(Class<? extends MetadataFieldRegistry> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        Map<String,String> vals = formHttpMessageConverter.read(null,httpInputMessage).toSingleValueMap();
        return mapper.convertValue(vals,MetadataFieldRegistry.class) ;
    }

    @Override
    protected void writeInternal(MetadataFieldRegistry metadataFieldRegistry, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
