package com.tongji.bwm.service.ERMS;

import com.tongji.bwm.entity.ERMS.Category;
import com.tongji.bwm.pojo.FilterCondition.FilterCondition;
import com.tongji.bwm.repository.ERMS.CategoryRepository;
import com.tongji.bwm.utils.FilterEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService<Integer> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ChannelService channelService;

    @Override
    public Integer Insert(Category category) {
        return categoryRepository.save(category).getId();
    }

    @Override
    public Category GetById(Integer id) {
        Optional<Category> optional = categoryRepository.findById(id);
        return optional.isPresent()?optional.get():null;
    }

    @Override
    public Category GetByCode(String code) {
        return categoryRepository.findByCode(code);
    }

    @Override
    public void Update(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void Delete(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public void Delete(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> GetAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> GetList(Example<Category> example) {
        return categoryRepository.findAll(example);
    }

    @Override
    public List<Category> GetList(Example<Category> example, Sort sort) {
        return categoryRepository.findAll(example,sort);
    }

    public List<Category> GetList(FilterCondition filterCondition){
        Category category = FilterEntityUtils.getOneExample(new Category(),filterCondition);
        //这里做个简单的判断
        if(category.getParentId()!=null && category.getParentId()>0){
            category.setOwnerCategory(GetById(category.getParentId()));
        }
        if(category.getChannelId()!=null && category.getChannelId()>0){
            category.setOwnerChannel(channelService.GetById(category.getChannelId()));
        }

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("channelId",ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("id");

        Example<Category> example = Example.of(category,matcher);


        Sort sort = FilterEntityUtils.getSort(filterCondition);

        return GetList(example,sort);
    }

    public List<Category> findByChannelId(Integer channelId){
        return categoryRepository.findByChannelId(channelId);
    }
}
