package com.tongji.bwm.service.ERMS;

import com.tongji.bwm.entity.ERMS.MetadataSchemaRegistry;
import com.tongji.bwm.pojo.FilterCondition.FilterCondition;
import com.tongji.bwm.repository.ERMS.MetadataSchemaRegistryRepository;
import com.tongji.bwm.utils.FilterEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetadataSchemaRegistryService implements IMetadataSchemaRegistryService<Integer> {

    @Autowired
    private MetadataSchemaRegistryRepository metadataSchemaRegistryRepository;

    @Override
    public Integer Insert(MetadataSchemaRegistry metadataSchemaRegistry) {
        return metadataSchemaRegistryRepository.save(metadataSchemaRegistry).getId();
    }

    @Override
    public MetadataSchemaRegistry GetById(Integer id) {
        Optional<MetadataSchemaRegistry> optional = metadataSchemaRegistryRepository.findById(id);
        return optional.isPresent()?optional.get():null;
    }

    @Override
    public MetadataSchemaRegistry GetByCode(String code) {
        return metadataSchemaRegistryRepository.findByCode(code);
    }

    @Override
    public void Update(MetadataSchemaRegistry metadataSchemaRegistry) {
        metadataSchemaRegistryRepository.save(metadataSchemaRegistry);
    }

    @Override
    public void Delete(Integer id) {
        metadataSchemaRegistryRepository.deleteById(id);
    }

    @Override
    public void Delete(MetadataSchemaRegistry metadataSchemaRegistry) {
        metadataSchemaRegistryRepository.delete(metadataSchemaRegistry);
    }

    @Override
    public List<MetadataSchemaRegistry> GetAll() {
        return metadataSchemaRegistryRepository.findAll();
    }


    public List<MetadataSchemaRegistry> GetList(FilterCondition filterCondition){
        MetadataSchemaRegistry metadataSchemaRegistry = FilterEntityUtils.getOneExample(new MetadataSchemaRegistry(),filterCondition);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("metadataSchemaId",ExampleMatcher.GenericPropertyMatchers.exact())
                .withIgnorePaths("id");

        Example<MetadataSchemaRegistry> example = Example.of(metadataSchemaRegistry,matcher);

        return metadataSchemaRegistryRepository.findAll(example);
    }
}
