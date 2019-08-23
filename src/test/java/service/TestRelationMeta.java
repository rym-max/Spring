package service;

import com.tongji.bwm.Application;
import com.tongji.bwm.entity.ERMS.Category;
import com.tongji.bwm.entity.ERMS.RelationMetadataField;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import com.tongji.bwm.service.ERMS.CategoryService;
import com.tongji.bwm.service.ERMS.RelationMetadataFieldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;


@Slf4j
@SpringBootTest(classes = {Application.class})
public class TestRelationMeta extends AbstractTestNGSpringContextTests {

    @Autowired
    private RelationMetadataFieldService relationMetadataFieldService;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void TestGet(){
        List<RelationMetadataField> list = new ArrayList<>();

        Integer CategoryId =4;

        if(CategoryId!=null){
            Category byId = categoryService.GetById(CategoryId);
            if(byId!=null){
                list = relationMetadataFieldService.GetList(CommonEnum.CustomMetadataFieldObject.values()[1],CategoryId,true);
                assertNotNull(list);
                log.info("列表大小--------"+list.size());
                log.warn("父级目录---------"+byId.getParentId());
//                if(list.size()==0 && byId.getParentId()>0){
//                    list = relationMetadataFieldService.GetList(CommonEnum.CustomMetadataFieldObject.values()[1],byId.getParentId(),true);
//                    assertNotNull(list);
//                }
            }
        }
        assertNotNull(list);
        assertNotEquals(list.size(),0);
    }
}
