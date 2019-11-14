package com.tongji.bwm;

import com.tongji.bwm.entity.ERMS.Region;
import com.tongji.bwm.filters.Converter.EntityConverter.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
        converters.add(regionConverter());
        converters.add(allConverter());
        converters.add(spiderItemConverter());
        converters.add(spiderConfigConverter());
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

    private RegionConverter regionConverter(){
        RegionConverter converter = new RegionConverter();
        MediaType mediaType = new MediaType(MediaType.APPLICATION_FORM_URLENCODED, Charset.forName("UTF-8"));
        converter.setSupportedMediaTypes(Arrays.asList(mediaType));
        return converter;
    }

    private AllConverter allConverter(){
        AllConverter converter = new AllConverter();
        MediaType mediaType = new MediaType(MediaType.APPLICATION_FORM_URLENCODED, Charset.forName("UTF-8"));
        converter.setSupportedMediaTypes(Arrays.asList(mediaType));
        return converter;
    }

    private SpiderConfigConverter spiderConfigConverter(){
        SpiderConfigConverter converter = new SpiderConfigConverter();
        MediaType mediaType = new MediaType(MediaType.APPLICATION_FORM_URLENCODED, Charset.forName("UTF-8"));
        converter.setSupportedMediaTypes(Arrays.asList(mediaType));
        return converter;
    }

    private SpiderItemConverter spiderItemConverter(){
        SpiderItemConverter converter = new SpiderItemConverter();
        MediaType mediaType = new MediaType(MediaType.APPLICATION_FORM_URLENCODED, Charset.forName("UTF-8"));
        converter.setSupportedMediaTypes(Arrays.asList(mediaType));
        return converter;
    }
}
