package com.tongji.bwm.service.Spider;

import com.tongji.bwm.entity.Spider.SpiderItem;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import com.tongji.bwm.pojo.FilterCondition.FilterCondition;
import com.tongji.bwm.pojo.Pagination;
import com.tongji.bwm.repository.Spider.SpiderItemRepository;
import com.tongji.bwm.utils.FilterEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpiderItemService implements ISpiderItemService<Integer> {
    @Autowired
    private SpiderItemRepository spiderItemRepository;

    @Autowired
    private SpiderConfigService spiderConfigService;

    @Override
    public Integer Insert(SpiderItem spiderItem) {
        return spiderItemRepository.save(spiderItem).getId();
    }

    @Override
    public SpiderItem GetById(Integer id) {
        Optional<SpiderItem> optional = spiderItemRepository.findById(id);
        return optional.isPresent()?optional.get():null;
    }

    @Override
    public SpiderItem GetByName(String name){
        return spiderItemRepository.findByName(name);
    }

    @Override
    public void Update(SpiderItem spiderItem) {
        spiderItemRepository.save(spiderItem);
    }

    @Override
    public void Delete(SpiderItem spiderItem) {
        spiderItemRepository.delete(spiderItem);
    }

    @Override
    public void Delete(Integer id) {
        spiderItemRepository.deleteById(id);
    }

    @Override
    public List<SpiderItem> GetAll() {
        return spiderItemRepository.findAll();
    }

    @Override
    public List<SpiderItem> findByConfigId(Integer configId) {
        return spiderItemRepository.findByConfigId(configId);
    }

    @Override
    public List<SpiderItem> GetList(Example<SpiderItem> example) {
        return spiderItemRepository.findAll(example);
    }

    @Override
    public List<SpiderItem> GetList(Example<SpiderItem> example, Sort sort) {
        return spiderItemRepository.findAll(example,sort);
    }

    @Override
    public Page<SpiderItem> GetList(Example<SpiderItem> example, Pageable pageable) {
        return spiderItemRepository.findAll(example,pageable);
    }


    public List<SpiderItem> GetList(FilterCondition filterCondition){
        SpiderItem spiderItem = FilterEntityUtils.getOneExample(new SpiderItem(),filterCondition);

        //这里做个简单判断
        if(spiderItem.getConfigId()!=null && spiderItem.getConfigId()>0){
            spiderItem.setOwnerSpiderConfig(spiderConfigService.GetById(spiderItem.getConfigId()));
        }

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase();

        Example<SpiderItem> example = Example.of(spiderItem,matcher);

        Sort sort = FilterEntityUtils.getSort(filterCondition);

        return GetList(example,sort);

    }

    @Override
    public List<SpiderItem> findAllIsOpen(CommonEnum.AvailableEnum isOpen) {
        return spiderItemRepository.findAllByIsOpen(isOpen);
    }

    public Pagination<SpiderItem> GetPageList(FilterCondition filterCondition){
        Pageable pageable = FilterEntityUtils.getPageable(filterCondition);
        Page<SpiderItem> page = spiderItemRepository.findAll(pageable);
        return FilterEntityUtils.getPagination(page);
    }

    public List<SpiderItem> findOneTest(){
        List<SpiderItem> list1 = new ArrayList<>();
        list1.add(GetById(1));
        return list1;
    }
}
