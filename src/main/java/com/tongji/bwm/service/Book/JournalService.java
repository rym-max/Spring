package com.tongji.bwm.service.Book;

import com.tongji.bwm.entity.Book.Journal;
import com.tongji.bwm.pojo.FilterCondition.FilterCondition;
import com.tongji.bwm.pojo.Pagination;
import com.tongji.bwm.repository.Book.JournalRepository;
import com.tongji.bwm.utils.FilterEntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class JournalService implements IJournalService<Integer> {

    @Autowired
    private JournalRepository journalRepository;

    @Override
    public Integer Insert(Journal journal) {
        return journalRepository.save(journal).getId();

    }

    @Override
    public Journal GetById(Integer id) {
        Optional<Journal> optional = journalRepository.findById(id);
        return optional.isPresent()?optional.get():null;
    }

    public Journal GetByName(String Name){
        return journalRepository.findByName(Name);
    }

    @Override
    public Journal GetByGCH(String gch) {
        return journalRepository.findByGCH(gch);
    }

    @Override
    public void Update(Journal journal) {
        journalRepository.save(journal);
    }

    @Override
    public void Delete(Journal journal) {
        journalRepository.delete(journal);
    }

    @Override
    public void Delete(Integer id) {
        journalRepository.deleteById(id);
    }

    @Override
    public List<Journal> GetList(Example<Journal> example) {
        //example构建方法
        return journalRepository.findAll(example);
    }

    @Override
    public Page<Journal> GetAll(Example<Journal> example, Pageable pageable) {
        return journalRepository.findAll(example,pageable);
    }

    public List<Journal> GetAll(){
        return journalRepository.findAll();
    }

    public List<Journal> GetList(FilterCondition filterCondition){
        Example<Journal> example = getExample(filterCondition);
        return GetList(example);
    }

    public Pagination<Journal> GetPageList(FilterCondition filterCondition){
        Pageable pageable = FilterEntityUtils.getPageable(filterCondition);
        Example<Journal> example = getExample(filterCondition);

        Page<Journal> page = GetAll(example,pageable);
        return FilterEntityUtils.getPagination(page);
    }

    private Example<Journal> getExample(FilterCondition filterCondition){
        Journal journal = FilterEntityUtils.getOneExample(new Journal(),filterCondition);
        log.info("Journal Example=="+journal.toString());
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name",ExampleMatcher.GenericPropertyMatchers.contains());
        //.withIgnorePaths("Subject");

        Example<Journal> example = Example.of(journal,matcher);
        return example;
    }

    @Cacheable(value = "visual",key = "#root.methodName")
    public Integer journalCountAll(){
        return Math.toIntExact(journalRepository.count());
    }
}
