package com.tongji.bwm;

import com.tongji.bwm.filters.Converter.EntityConverter.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Configuration
public class HttpResponseConverterDTOConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(userConverter());
        converters.add(journalConverter());
        converters.add(categoryConverter());
        converters.add(channelConverter());
        converters.add(metadataFieldRegistryConverter());
        converters.add(metadataSchemaRegistryConverter());
        converters.add(relationMetadataFieldConverter());
        converters.add(administartorConverter());
        converters.add(itemConverter());
    }

    private UserConverter userConverter(){
        UserConverter converter = new UserConverter();
        MediaType mediaType = new MediaType(MediaType.APPLICATION_FORM_URLENCODED, Charset.forName("UTF-8"));
        converter.setSupportedMediaTypes(Arrays.asList(mediaType));
        return converter;
    }

    private JournalConverter journalConverter(){
        JournalConverter converter = new JournalConverter();
        MediaType mediaType = new MediaType(MediaType.APPLICATION_FORM_URLENCODED, Charset.forName("UTF-8"));
        converter.setSupportedMediaTypes(Arrays.asList(mediaType));
        return converter;
    }

    private CategoryConverter categoryConverter(){
        CategoryConverter converter = new CategoryConverter();
        MediaType mediaType = new MediaType(MediaType.APPLICATION_FORM_URLENCODED, Charset.forName("UTF-8"));
        converter.setSupportedMediaTypes(Arrays.asList(mediaType));
        return converter;
    }

    private ChannelConverter channelConverter(){
        ChannelConverter converter = new ChannelConverter();
        MediaType mediaType = new MediaType(MediaType.APPLICATION_FORM_URLENCODED, Charset.forName("UTF-8"));
        converter.setSupportedMediaTypes(Arrays.asList(mediaType));
        return converter;
    }

    private MetadataFieldRegistryConverter metadataFieldRegistryConverter(){
        MetadataFieldRegistryConverter converter = new MetadataFieldRegistryConverter();
        MediaType mediaType = new MediaType(MediaType.APPLICATION_FORM_URLENCODED, Charset.forName("UTF-8"));
        converter.setSupportedMediaTypes(Arrays.asList(mediaType));
        return converter;
    }

    private MetadataSchemaRegistryConverter metadataSchemaRegistryConverter(){
        MetadataSchemaRegistryConverter converter = new MetadataSchemaRegistryConverter();
        MediaType mediaType = new MediaType(MediaType.APPLICATION_FORM_URLENCODED, Charset.forName("UTF-8"));
        converter.setSupportedMediaTypes(Arrays.asList(mediaType));
        return converter;
    }

    private RelationMetadataFieldConverter relationMetadataFieldConverter(){
        RelationMetadataFieldConverter converter = new RelationMetadataFieldConverter();
        MediaType mediaType = new MediaType(MediaType.APPLICATION_FORM_URLENCODED, Charset.forName("UTF-8"));
        converter.setSupportedMediaTypes(Arrays.asList(mediaType));
        return converter;
    }

    private AdministartorConverter administartorConverter(){
        AdministartorConverter converter = new AdministartorConverter();
        MediaType mediaType = new MediaType(MediaType.APPLICATION_FORM_URLENCODED, Charset.forName("UTF-8"));
        converter.setSupportedMediaTypes(Arrays.asList(mediaType));
        return converter;
    }

    private ItemConverter itemConverter(){
        ItemConverter converter = new ItemConverter();
        MediaType mediaType = new MediaType(MediaType.APPLICATION_FORM_URLENCODED, Charset.forName("UTF-8"));
        converter.setSupportedMediaTypes(Arrays.asList(mediaType));
        return converter;
    }
}
