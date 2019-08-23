package com.tongji.bwm.service.ERMS;

import com.tongji.bwm.entity.ERMS.RelationMetadataField;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import com.tongji.bwm.pojo.FilterCondition.Field;
import com.tongji.bwm.pojo.FilterCondition.FilterCondition;
import com.tongji.bwm.repository.ERMS.RelationMetadataFieldRepository;
import com.tongji.bwm.utils.FilterEntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RelationMetadataFieldService implements IRelationMetadataFieldService<Integer>{

    @Autowired
    private RelationMetadataFieldRepository relationMetadataFieldRepository;

    @Override
    public Integer Insert(RelationMetadataField relationMetadataField) {
        return relationMetadataFieldRepository.save(relationMetadataField).getId();
    }

    @Override
    public RelationMetadataField GetById(Integer id) {
        Optional<RelationMetadataField> optional = relationMetadataFieldRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    @Override
    public RelationMetadataField GetByFields(Example<RelationMetadataField> example) {
        Optional<RelationMetadataField> optional= relationMetadataFieldRepository.findOne(example);
        return optional.isPresent() ? optional.get() : null;
    }

    @Override
    public void Update(RelationMetadataField relationMetadataField) {
        relationMetadataFieldRepository.save(relationMetadataField);
    }

    @Override
    public void Delete(Integer id) {
        relationMetadataFieldRepository.deleteById(id);
    }

    @Override
    public void Delete(RelationMetadataField relationMetadataField) {
        relationMetadataFieldRepository.delete(relationMetadataField);
    }

    @Override
    public List<RelationMetadataField> GetList(Example<RelationMetadataField> example) {
        return relationMetadataFieldRepository.findAll(example);
    }

    @Override
    public List<RelationMetadataField> GetList(Example<RelationMetadataField> example, Sort sort) {
        return relationMetadataFieldRepository.findAll(example, sort);
    }

    public List<RelationMetadataField> GetList(FilterCondition filterCondition){
//        log.info("FilterCondition条件内容："+filterCondition.filter.toString());
//        if(filterCondition.filter!=null && filterCondition.filter.size()!=0){
//            for(Field f: filterCondition.filter){
//                log.info("filter："+f.name+"[------]"+f.value);
//            }
//        }
        RelationMetadataField metadataField = FilterEntityUtils.getOneExample(new RelationMetadataField(),filterCondition);
//        log.info("元数据查询条件："+metadataField.toString());

        //特殊情况
        Integer ObjectId = metadataField.getObjectTypeId();
        if(ObjectId != null){
            if(ObjectId < 0 || ObjectId >= CommonEnum.CustomMetadataFieldObject.values().length){
                metadataField.setObjectTypeId(null);
            }else{
                metadataField.setObjectType(CommonEnum.CustomMetadataFieldObject.values()[ObjectId]);
            }
        }


        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("relationObjectId",ExampleMatcher.GenericPropertyMatchers.exact())
                .withIgnorePaths("id");

        Example<RelationMetadataField> example = Example.of(metadataField,matcher);

        Sort sort = FilterEntityUtils.getSort(filterCondition);

        List<RelationMetadataField> list = relationMetadataFieldRepository.findAll(example,sort);

        return list;

    }

    public List<RelationMetadataField> GetList(CommonEnum.CustomMetadataFieldObject ObjectType, Integer RelationObjectId, boolean AscSort){
        if(AscSort)
            return relationMetadataFieldRepository.findAllByObjectTypeAndRelationObjectIdOrderBySortAsc(ObjectType,RelationObjectId);
        else
            return relationMetadataFieldRepository.findAllByObjectTypeAndRelationObjectIdOrderBySortDesc(ObjectType,RelationObjectId);
    }
}
