package com.tongji.bwm.service.ERMS;

import com.tongji.bwm.entity.ERMS.MetadataFieldRegistry;
import com.tongji.bwm.entity.ERMS.MetadataSchemaRegistry;
import com.tongji.bwm.pojo.FilterCondition.FilterCondition;
import com.tongji.bwm.pojo.MetaFieldRegistryNameAndId;
import com.tongji.bwm.repository.ERMS.MetadataFieldRegistryRepository;
import com.tongji.bwm.repository.ERMS.MetadataSchemaRegistryRepository;
import com.tongji.bwm.utils.FilterEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MetadataFieldRegistryService implements IMetadataFieldRegistryService<Integer> {

    @Autowired
    private MetadataFieldRegistryRepository metadataFieldRegistryRepository;

    @Autowired
    private MetadataSchemaRegistryService metadataSchemaRegistryService;

    @Override
    public Integer Insert(MetadataFieldRegistry metadataFieldRegistry) {
        return metadataFieldRegistryRepository.save(metadataFieldRegistry).getId();
    }

    @Override
    public MetadataFieldRegistry GetById(Integer id) {
        Optional<MetadataFieldRegistry> optional = metadataFieldRegistryRepository.findById(id);
        return optional.isPresent()?optional.get():null;
    }

    @Override
    public MetadataFieldRegistry GetByFields(Example<MetadataFieldRegistry> example) {
        Optional<MetadataFieldRegistry> optional = metadataFieldRegistryRepository.findOne(example);
        return optional.isPresent()?optional.get():null;
    }

    @Override
    public void Update(MetadataFieldRegistry metadataFieldRegistry) {
        metadataFieldRegistryRepository.save(metadataFieldRegistry);
    }

    @Override
    public void Delete(Integer id) {
        metadataFieldRegistryRepository.deleteById(id);
    }

    @Override
    public void Delete(MetadataFieldRegistry metadataFieldRegistry) {
        metadataFieldRegistryRepository.delete(metadataFieldRegistry);
    }

    @Override
    public List<MetadataFieldRegistry> GetAll(){
        return metadataFieldRegistryRepository.findAll();
    }

    @Override
    public List<MetadataFieldRegistry> GetList(Example<MetadataFieldRegistry> example) {
        return metadataFieldRegistryRepository.findAll(example);
    }


    public List<MetadataFieldRegistry> GetList(FilterCondition filterCondition){
        MetadataFieldRegistry metadataFieldRegistry = FilterEntityUtils.getOneExample(new MetadataFieldRegistry(),filterCondition);

        //
        if(metadataFieldRegistry.getMetadataSchemaId()!=null) {
            MetadataSchemaRegistry schema = metadataSchemaRegistryService.GetById(metadataFieldRegistry.getMetadataSchemaId());
            if(schema!=null)
                metadataFieldRegistry.setOwnerMetadataSchemaRegistry(schema);
        }

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("metadataSchemaId",ExampleMatcher.GenericPropertyMatchers.exact())
                .withIgnoreCase();

        Example<MetadataFieldRegistry> example = Example.of(metadataFieldRegistry,matcher);

        return GetList(example);
    }

    public MetadataFieldRegistry GetByME(Integer metadataSchemaId,String element){
        return metadataFieldRegistryRepository.findByMetadataSchemaIdAndElement(metadataSchemaId,element);
    }

    public MetadataFieldRegistry GetByMEQ(Integer metadataSchemaId,String element,String qualifier){
        return metadataFieldRegistryRepository.findByMetadataSchemaIdAndElementAndQualifier(metadataSchemaId,element,qualifier);
    }


    //为了读取所有的metaregistry 返回id和string
    public List<MetaFieldRegistryNameAndId> GetNameAndId(){
        List<MetadataFieldRegistry> list = metadataFieldRegistryRepository.findAll();
        List<MetaFieldRegistryNameAndId> list1 = new ArrayList<>();
        for(MetadataFieldRegistry meta: list){
            list1.add(new MetaFieldRegistryNameAndId(meta));
        }
        return list1;

    }
}
