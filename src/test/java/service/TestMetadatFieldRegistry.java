package service;


import com.tongji.bwm.Application;
import com.tongji.bwm.entity.ERMS.MetadataFieldRegistry;
import com.tongji.bwm.pojo.FilterCondition.FilterCondition;
import com.tongji.bwm.service.ERMS.MetadataFieldRegistryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;

@Slf4j
@SpringBootTest(classes = {Application.class})
public class TestMetadatFieldRegistry extends AbstractTestNGSpringContextTests {

    @Autowired
    private MetadataFieldRegistryService metadataFieldRegistryService;

    @Test
    public void TestGetLoist(){

        List<MetadataFieldRegistry> list1 = metadataFieldRegistryService.GetList(new FilterCondition());

        list1.forEach(
                s->{
                    log.info("每一个metadataField------------");
                    log.info(metadataFieldRegistryService.toString());
                }
        );
        log.info("总共数量----------"+list1.size());

        List<MetadataFieldRegistry> list2 = metadataFieldRegistryService.GetAll();

        log.info("总共数量----------"+list2.size());
    }
}
