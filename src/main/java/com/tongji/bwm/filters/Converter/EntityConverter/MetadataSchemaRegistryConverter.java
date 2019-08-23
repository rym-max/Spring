package com.tongji.bwm.filters.Converter.EntityConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tongji.bwm.entity.ERMS.MetadataSchemaRegistry;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.Map;

public class MetadataSchemaRegistryConverter extends AbstractHttpMessageConverter<MetadataSchemaRegistry> {

    private static final FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected boolean supports(Class<?> aClass) {
        return MetadataSchemaRegistry.class == aClass;
    }

    @Override
    protected MetadataSchemaRegistry readInternal(Class<? extends MetadataSchemaRegistry> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        Map<String,String> vals = formHttpMessageConverter.read(null,httpInputMessage).toSingleValueMap();
        return mapper.convertValue(vals,MetadataSchemaRegistry.class) ;
    }

    @Override
    protected void writeInternal(MetadataSchemaRegistry metadataSchemaRegistry, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
