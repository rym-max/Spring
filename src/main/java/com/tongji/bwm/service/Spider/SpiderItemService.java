package com.tongji.bwm.service.Spider;

import com.tongji.bwm.entity.Spider.Item;
import com.tongji.bwm.pojo.FilterCondition.FilterCondition;
import com.tongji.bwm.pojo.Pagination;
import com.tongji.bwm.repository.Spider.SpiderItemRepository;
import com.tongji.bwm.utils.FilterEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Filter;

@Service
public class SpiderItemService implements ISpiderItemService<Integer> {
    @Autowired
    private SpiderItemRepository spiderItemRepository;

    @Autowired
    private SpiderConfigService spiderConfigService;

    @Override
    public Integer Insert(Item item) {
        return spiderItemRepository.save(item).getId();
    }

    @Override
    public Item GetById(Integer id) {
        Optional<Item> optional = spiderItemRepository.findById(id);
        return optional.isPresent()?optional.get():null;
    }

    @Override
    public void Update(Item item) {
        spiderItemRepository.save(item);
    }

    @Override
    public void Delete(Item item) {
        spiderItemRepository.delete(item);
    }

    @Override
    public void Delete(Integer id) {
        spiderItemRepository.deleteById(id);
    }

    @Override
    public List<Item> GetAll() {
        return spiderItemRepository.findAll();
    }

    @Override
    public List<Item> findByConfigId(Integer configId) {
        return spiderItemRepository.findByConfigId(configId);
    }

    @Override
    public List<Item> GetList(Example<Item> example) {
        return spiderItemRepository.findAll(example);
    }

    @Override
    public List<Item> GetList(Example<Item> example, Sort sort) {
        return spiderItemRepository.findAll(example,sort);
    }

    @Override
    public Page<Item> GetList(Example<Item> example, Pageable pageable) {
        return spiderItemRepository.findAll(example,pageable);
    }


    public List<Item> GetList(FilterCondition filterCondition){
        Item item = FilterEntityUtils.getOneExample(new Item(),filterCondition);

        //这里做个简单判断
        if(item.getConfigId()!=null && item.getConfigId()>0){
            item.setOwnerConfig(spiderConfigService.GetById(item.getConfigId()));
        }

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase();

        Example<Item> example = Example.of(item,matcher);

        Sort sort = FilterEntityUtils.getSort(filterCondition);

        return GetList(example,sort);

    }

    @Override
    public List<Item> findAllIsOpen(Boolean isOpen) {
        return spiderItemRepository.findAllByIsOpen(isOpen);
    }

    public Pagination<Item> GetPageList(FilterCondition filterCondition){
        Pageable pageable = FilterEntityUtils.getPageable(filterCondition);
        Page<Item> page = spiderItemRepository.findAll(pageable);
        return FilterEntityUtils.getPagination(page);
    }

}
