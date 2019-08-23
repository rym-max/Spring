package service;

import com.tongji.bwm.Application;
import com.tongji.bwm.entity.ERMS.Category;
import com.tongji.bwm.entity.ERMS.Channel;
import com.tongji.bwm.pojo.FilterCondition.Field;
import com.tongji.bwm.pojo.FilterCondition.FilterCondition;
import com.tongji.bwm.pojo.FilterCondition.Sort;
import com.tongji.bwm.repository.ERMS.ChannelRepository;
import com.tongji.bwm.service.ERMS.CategoryService;
import com.tongji.bwm.service.ERMS.ChannelService;
import com.tongji.bwm.utils.FilterEntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Slf4j
@SpringBootTest(classes = {Application.class})
public class TestCategory extends AbstractTestNGSpringContextTests {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ChannelService channelService;

    @Test
    public void TestCategory(){
        FilterCondition filterCondition = new FilterCondition();

        List<Field> list1 = new ArrayList<>();
        list1.add(new Field("ChannelId","1"));

        List<Sort> list2 = new ArrayList<>();
        list2.add(new Sort("Id",true));
        list2.add(new Sort("Sort",true));

        filterCondition.filter = list1;
        filterCondition.sort = list2;

//        List<Category> list3 = categoryService.GetList(filterCondition);
        //这里要区分了
        Category category = FilterEntityUtils.getOneExample(new Category(),filterCondition);
        log.info(category.toString());

        //
        //这里做个简单的判断

        if(category.getChannelId()!=null && category.getChannelId()>0){
            category.setOwnerChannel(channelService.GetById(category.getChannelId()));
        }
        log.info(category.toString());
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("channelId", ExampleMatcher.GenericPropertyMatchers.exact());

        Example<Category> example = Example.of(category,matcher);
        //获取sort
        //直接获取
        List<Category> list3 = categoryService.GetList(example);
        assertNotNull(list3);
        assertNotEquals(list3.size(),0);
        for(Category c: list3){
            log.info("what-----");
            log.info(c.toString());
        }
    }

    @Autowired
    private ChannelRepository channelRepository;

    @Test
    public void TestChannelExample(){
        Channel channel = new Channel();
        channel.setDescription("");

        Example<Channel> example = Example.of(channel);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("description",ExampleMatcher.GenericPropertyMatchers.exact())
                .withIgnoreCase();

        List<Channel> result = channelRepository.findAll(example);

        assertNotNull(result);
        assertEquals(result.size(),0);
    }
}
