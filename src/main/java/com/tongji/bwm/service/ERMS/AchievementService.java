package com.tongji.bwm.service.ERMS;

import com.tongji.bwm.entity.ERMS.Achievement;
import com.tongji.bwm.repository.ERMS.AchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AchievementService implements IAchievementService<String> {

    @Autowired
    private AchievementRepository achievementRepository;

    @Override
    public String Insert(Achievement achievement) {
        return achievementRepository.save(achievement).getId();
    }

    @Override
    public Achievement GetById(String id) {
        Optional<Achievement> optional = achievementRepository.findById(id);
        return optional.isPresent()?optional.get():null;
    }

//    @Override
//    public Achievement GetByCode(String code) {
//        return achievementRepository.findByCode(code);
//    }

    @Override
    public void Update(Achievement achievement) {
        achievementRepository.save(achievement);
    }

    @Override
    public void Delete(Achievement achievement) {
        achievementRepository.delete(achievement);
    }

    @Override
    public void Delete(String id) {
        achievementRepository.deleteById(id);
    }

    @Override
    public List<Achievement> GetAll() {
        return achievementRepository.findAll();
    }

    @Override
    public Page<Achievement> GetPageList(Example<Achievement> example, Pageable pageable) {
        return achievementRepository.findAll(example,pageable);
    }

    @Override
    public long Count(Example<Achievement> example) {
        if(example!=null)
            return achievementRepository.count(example);
        return  achievementRepository.count();
    }
}
