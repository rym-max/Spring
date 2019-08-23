package service;

import com.tongji.bwm.Application;
import com.tongji.bwm.entity.ERMS.RelationMetadataField;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import com.tongji.bwm.pojo.FilterCondition.Field;
import com.tongji.bwm.pojo.FilterCondition.FilterCondition;
import com.tongji.bwm.repository.ERMS.RelationMetadataFieldRepository;
import com.tongji.bwm.service.ERMS.RelationMetadataFieldService;
import com.tongji.bwm.utils.FilterEntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest(classes ={Application.class})
public class FilterConditionTest extends AbstractTestNGSpringContextTests {

    private FilterCondition filterCondition;

    @Autowired
    private RelationMetadataFieldService relationMetadataFieldService;

    @Test
    public void testFilterCondition(){
        filterCondition = new FilterCondition();
        List<Field> list1 = new ArrayList<>();
        list1.add(new Field("RelationObjectId","1"));
        list1.add(new Field("ObjectTypeId","1"));
        filterCondition.filter = list1;

        RelationMetadataField relationMetadataField1 = FilterEntityUtils.getOneExample(new RelationMetadataField(),filterCondition);

        RelationMetadataField relationMetadataField2 = new RelationMetadataField();
        relationMetadataField2.setObjectTypeId(1);
        relationMetadataField2.setRelationObjectId(1);

        assertEquals(relationMetadataField1,relationMetadataField2);
    }

    @Autowired
    private RelationMetadataFieldRepository relationMetadataFieldRepository;

    @Test
    public void testExample(){
        filterCondition = new FilterCondition();
        List<Field> list1 = new ArrayList<>();
        list1.add(new Field("RelationObjectId","2"));
        list1.add(new Field("ObjectTypeId","0"));
        filterCondition.filter = list1;

        RelationMetadataField relationMetadataField1 = FilterEntityUtils.getOneExample(new RelationMetadataField(),filterCondition);

        CommonEnum.CustomMetadataFieldObject a = CommonEnum.CustomMetadataFieldObject.values()[0];
        assertNotNull(a);
        List<RelationMetadataField> list2 = relationMetadataFieldRepository.findAllByObjectTypeAndRelationObjectIdOrderBySortDesc(a,2);
        List<RelationMetadataField> list3 = relationMetadataFieldService.GetList(filterCondition);
        assertNotNull(list2);
        assertNotNull(list3);
        assertEquals(list2,list3);
        log.info(String.valueOf(list2.size()));
        for(RelationMetadataField f: list2){
            //也不知道能打印吗
            log.info(f.toString());
        }

    }
}
