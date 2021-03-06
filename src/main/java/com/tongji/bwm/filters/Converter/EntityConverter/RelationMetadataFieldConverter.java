package com.tongji.bwm.filters.Converter.EntityConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tongji.bwm.entity.ERMS.RelationMetadataField;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.Map;

public class RelationMetadataFieldConverter extends AbstractHttpMessageConverter<RelationMetadataField> {

    private static final FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected boolean supports(Class<?> aClass) {
        return RelationMetadataField.class == aClass;
    }

    @Override
    protected RelationMetadataField readInternal(Class<? extends RelationMetadataField> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        Map<String,String> vals = formHttpMessageConverter.read(null,httpInputMessage).toSingleValueMap();
        return mapper.convertValue(vals,RelationMetadataField.class) ;
    }

    @Override
    protected void writeInternal(RelationMetadataField relationMetadataField, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
